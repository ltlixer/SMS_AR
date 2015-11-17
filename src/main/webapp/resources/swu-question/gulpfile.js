var gulp = require('gulp');
var browserSync = require('browser-sync').create();
var uglify = require('gulp-uglify');
var minify = require('gulp-minify');
var concat = require('gulp-concat');

gulp.watch('*.html').on('change', browserSync.reload);

gulp.task('default', function() {
	browserSync.init({
		server: {
			baseDir: './',
			index: 'index.html'
		}
	})
})


gulp.task('js', function() {
	gulp.src('addon/**/*.js')
		.pipe(concat('codemirror-config.js'))
		.pipe(minify())
		.pipe(gulp.dest('build'))
})

gulp.task('css', function() {
	gulp.src('addon/**/*.css')
		.pipe(concat('codemirror-config.css'))
		.pipe(minify())
		.pipe(gulp.dest('build'))
})

gulp.task('default', ['css', 'js']);