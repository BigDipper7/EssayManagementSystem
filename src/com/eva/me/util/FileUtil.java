/**
 * 
 */
package com.eva.me.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.eva.me.lucene.LuceneIndex;

/**
 * @author violi
 *
 */
public class FileUtil {

	public static void delete(File file) throws IOException {
		if (!file.exists()) {
			System.err.println("[DEL FAIL] File:["+file.getAbsolutePath()+"] not exists...");
			return;
		}
		
		if (file.isDirectory()) {
			
			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

	public static void write(final String filePath, final String content, final String charset) {
		File file = new File(filePath);
		try {
			file.createNewFile();
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			try { 
			    out.write(content);
			} finally { 
			    out.close();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeUTF8(final String filePath, final String content) {
		FileUtil.write(filePath, content, "UTF-8");
	}
	
	private static FileOutputStream getOutputStreamWithFilenameTimestamp(final String suffix) throws IOException {
		//mk dir
		final String filePath = LuceneIndex.uploadDirectoryPath;
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		final String finalPath = filePath + File.separator + TimeUtil.getCurrTimStr() + suffix;
		Log.i("cur file path: "+finalPath);
		//mk file 
		f = new File(finalPath);
		if (!f.exists()) {
			f.createNewFile();//generate exception
		}
		
		FileOutputStream fos = new FileOutputStream(f);
		return fos;
	}
	
	public static FileOutputStream getXLSFOS() throws IOException {
		return getOutputStreamWithFilenameTimestamp(".xls");
	}
}
