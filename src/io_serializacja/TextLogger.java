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
	
	private String filePath;
	private FileWriter fileWriter;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public TextLogger(String filePath, boolean appendState) throws IOException{
		this.filePath=filePath;
		fileWriter=new FileWriter(filePath,appendState);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log(String status, Student student) {
		try {
			fileWriter.write(dateFormat.format(new Date())+status+student);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
