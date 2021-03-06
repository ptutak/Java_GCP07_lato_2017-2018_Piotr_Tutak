package io_serializacja;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class CompressedLogger implements Logger, Closeable {

	private String fileName;
	private SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss.SSS");
	private TextLogger textLogger;
	private ZipOutputStream zipOutputStream;
	private FileOutputStream fileOutputStream;

	public CompressedLogger(String fileName,boolean appendMode)throws IOException{
		this.fileName=fileName;
		fileOutputStream=new FileOutputStream(this.fileName,appendMode);
		zipOutputStream=new ZipOutputStream(fileOutputStream);

	}

	@Override
	public synchronized void close() throws IOException {
		if (zipOutputStream!=null)
			zipOutputStream.close();
		if (fileOutputStream!=null)
			fileOutputStream.close();
		zipOutputStream=null;
		fileOutputStream=null;
	}

	@Override
	public synchronized void log(String status, Student student) {
		String tempFileName=fileNameFormat.format(new Date())+".txt";
		FileInputStream fileInput=null;
		File file=null;
		try {
			textLogger=new TextLogger(tempFileName,true);
			textLogger.log(status, student);
			textLogger.close();
			file=new File(tempFileName);
			fileInput=new FileInputStream(file);

			zipOutputStream.putNextEntry(new ZipEntry(tempFileName));
			int b;
			while (true){
				b=fileInput.read();
				if (b!=-1)
					zipOutputStream.write(b);
				else
					break;
			}
			zipOutputStream.closeEntry();
			zipOutputStream.flush();
			fileInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (file!=null)
				if (file.exists())
					file.delete();
			file=null;
			try{
				if (fileInput!=null)
					fileInput.close();
			} catch (IOException e){}
		}


	}

}
