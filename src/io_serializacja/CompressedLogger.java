package io_serializacja;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Pipe;
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

	public CompressedLogger(String fileName,boolean appendMode)throws IOException{
		this.fileName=fileName;
		zipOutputStream=new ZipOutputStream(new FileOutputStream(fileName+".zip",appendMode));

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		zipOutputStream.close();
	}

	@Override
	public void log(String status, Student student) {
		String tempFileName=fileNameFormat.format(new Date())+".txt";
		FileInputStream fileInput;
		File file=null;
		try {
			textLogger=new TextLogger(tempFileName,false);
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
			zipOutputStream.finish();
			fileInput.close();


		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (file!=null)
				if (file.exists())
					file.delete();
		}


	}

}
