package com.cisdijob.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class FileDownload {

	public static void downLoadFile(HttpServletResponse response, String filePath,
			String fileName) throws IOException {
		//String fileName = file.getName();
		// 下载文件
		FileManageUtils.exportFile(response, filePath + fileName, fileName);
		// 删除单个文件
		FileManageUtils.deleteFile(filePath, fileName);
	}
}
