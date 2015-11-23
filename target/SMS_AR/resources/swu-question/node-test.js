var express = require('express');
var bodyParser = require('body-parser');
var logger = require('morgan');
var fs = require('fs');
var path = require("path");
var dir = process.argv[2] || path.join(process.cwd(), 'test')
var mongoose = require('mongoose');
var opener = require('opener');
var session = require('express-session');

var app = express();

// var geetest = require('geetest')('4f2ef8e36cedbbe4413c9c51eefa8887', '4b03af16c0eb6dfd15d10a33f64f1f1a');


//可修改文件列表
var data = fs.readFileSync('config.txt');

var modifyFiles = data.toString().split(/\r?\n/);


app.use(logger('dev'));
app.use(express.static('./'));
app.use(bodyParser.json());
app.use(session({secret: 'session cat'}));
app.use(bodyParser.urlencoded({ extended: false }));

app.set('views', __dirname);
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');

app.listen(process.env.PORT || 3100);
console.log('Node.js running at: http://0.0.0.0:3100');

var options = {
    user: 'ren',
    pass: '19940808RXL'
}

//连接数据库
mongoose.connect('mongodb://localhost/question', options);

var db = mongoose.connection;

db.on('error', function(msg) {
    console.log('Collection Error: %s', msg);
});

//建立 mongodb 模型
var codeSchema = mongoose.Schema({
    name: {type: String, require: true},
    code: String,
    version: {type: String, require: true}
});

var Mirror = mongoose.model('codeMirror', codeSchema);


//添加session，防止越过密码登入
app.get('/', function(req, res) {
    if(!req.session.user) {
       req.session.user = 'ren';
    }
    res.render('./main.html');
})

//表单提交，验证密码
app.get('/submit', function(req, res){

    if(req.query.pwd !== 'question') {
        res.send('密码错误');
        res.end();
    }else {
        res.redirect('/code');
    }
});




//更新数据
app.post('/modify', function(req, res){

    var originCode = {
        name: req.body.name,
        code: req.body.code,
        version: req.body.version
    };

    Mirror.findOneAndUpdate({name: req.body.name, version: req.body.version}, originCode, {upsert: true}, function(err, result) {
        if(err) {
            console.log(err);
        }else {
            console.log(result);
        }
    });

    res.send(req.body);
});

//删除数据
app.post('/del', function(req, res){

    Mirror.remove({name: req.body.name, version: req.body.version}, function(err, result) {
        if(err) {
            console.log(err);
        }else {
            res.send({errno: 0});
        }
    });

});

//查询版本，返回该版本代码
app.post('/query', function(req, res){

    Mirror.findOne({name: req.body.name, version: req.body.version}, function(err, result) {
        if(err) {
            console.log(err);
        }else {
            if(result) {
                res.send({code: result.code});
            }else {
                res.send({code: ''});
            }
        }
    });

});


app.post('/writeFile', geetest.bodyParser, function(req, res) {

    //图形验证码
    //geetest.register(function(err, challenge) {
    //    if (err) {
    //        //network error
    //        return;
    //    }
    //    if(challenge) {
    //        //deal with it
    //        res.json({challenge: challenge})
    //    }
    //});
    fs.writeFile(req.body.name, req.body.code, function (err) {
        if (err) throw err;
        console.log('It\'s saved!');
        opener('./main.html', function() {
            //console.log('this is a new window');
        });
        res.end();
    });
});


//主页路由，读取版本信息并呈现
app.get('/code', function(req, res){

    if(!req.session.user || req.session.user !== 'ren') {
        res.send('未验证，请通过主页访问');
        res.end();
    };
    if(req.query.name) {
        var currFile = req.query.name;
        var index = modifyFiles.indexOf(currFile);
        modifyFiles = modifyFiles.slice(index).concat(modifyFiles.slice(0, index));

    }else {
        var currFile = modifyFiles[0];
    }
    getFileByName(currFile, res);
});

function getFileByName(fileName, res) {
    Mirror.find({name: fileName}, function(err, result) {
        if(err) {
            console.log(err);
        }else {

            var versions = [];

            //第一次读入文件,保存为原始版本
            if(!result[0]) {

                //以追加模式打开文件
                fs.open('./js/' + fileName, 'a', 0666, function(err, result) {
                    if(err) {
                        console.log(err);
                    }

                    var text = fs.readFileSync('./js/' + fileName, "utf8");
                    versions.push('原始版本');

                    var newCode = new Mirror({
                        name: fileName,
                        code: text,
                        version: '原始版本'
                    });
                    newCode.save(function(err, result) {
                        if(err) {
                            console.log(err);
                        }else {
                            console.log(result);
                        }
                    })
                });

            }else {
                result.forEach(function(item, index, arr) {
                    versions.push(item.version);
                });
                var text = result[0].code;
            }

            res.render('codemirror.html', {versions: versions, files: modifyFiles, fileName: fileName, code: text});
        }
    });
}

