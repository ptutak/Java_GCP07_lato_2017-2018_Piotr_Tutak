package io_serializacja;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class TextLogger implements Logger, Closeable {
	
	private String fileName;
	private FileWriter fileWriter;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public TextLogger(String fileName, boolean appendState) throws IOException{
		this.fileName=fileName;
		fileWriter=new FileWriter(fileName,appendState);
	}

	@Override
	public synchronized void close() throws IOException {
		// TODO Auto-generated method stub
		fileWriter.close();
	}

	@Override
	public synchronized void log(String status, Student student) {
		try {
			fileWriter.write(dateFormat.format(new Date())+" "+status+" "+student);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
