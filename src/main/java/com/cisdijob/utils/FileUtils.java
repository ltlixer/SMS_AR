package com.cisdijob.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtils {
	private static FileUtils fu = null;
	
	public static FileUtils getInstance(){
		if(fu==null){
			fu = new FileUtils();
		}
		return fu;
	}
	/**
	 * 存储文件
	 * 
	 * @param stream
	 * @param tagFileName
	 * @throws IOException
	 */
	public void SaveFileFromInputStream(InputStream stream, String tagFileName)
			throws IOException {
		File outfile = new File(tagFileName);
		OutputStream os = null;
		try {
			os = new FileOutputStream(outfile);
			byte buffer[] = new byte[4 * 1024];
			int len = 0;
			while ((len = stream.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 存储文件
	 * 
	 * @param srcFileName
	 * @param tagFileName
	 * @throws IOException
	 */
	public void SaveFileFromInputStream(String srcFileName, String tagFileName)
			throws IOException {
		File in = new File(srcFileName);
		File out = new File(tagFileName);
		FileInputStream inputStream = new FileInputStream(in);
		FileChannel inChannel = inputStream.getChannel();
		long inputSize = inChannel.size();
		MappedByteBuffer inBuffer = inChannel.map(
				FileChannel.MapMode.READ_ONLY, 0, inputSize);
		// 使用通道方式复制文件
		FileOutputStream outputStream = new FileOutputStream(out);
		FileChannel outChannel = outputStream.getChannel();
		outChannel.write(inBuffer);
		// 关闭相关对象
		inChannel.close();
		inputStream.close();
		outChannel.close();
		outputStream.close();
	}
}
