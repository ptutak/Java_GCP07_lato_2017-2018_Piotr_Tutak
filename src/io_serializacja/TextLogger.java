package io_serializacja;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class TextLogger implements Logger, Closeable {
	
	private String fileName;
	private OutputStreamWriter outputStreamWriter;
	private FileOutputStream fileOutputStream;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public TextLogger(String fileName, boolean appendState) throws IOException{
		this.fileName=fileName;
		fileOutputStream=new FileOutputStream(fileName,appendState);
		outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
	}

	@Override
	public synchronized void close() throws IOException {
		if (outputStreamWriter!=null)
			outputStreamWriter.close();
		if (fileOutputStream!=null)
			fileOutputStream.close();
		outputStreamWriter=null;
		fileOutputStream=null;
	}

	@Override
	public synchronized void log(String status, Student student) {
		try {
			outputStreamWriter.write(dateFormat.format(new Date())+" "+status+" "+student+"\n");
			outputStreamWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
