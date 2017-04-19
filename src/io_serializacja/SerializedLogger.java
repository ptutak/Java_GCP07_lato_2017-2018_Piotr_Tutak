package io_serializacja;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class SerializedLogger implements Logger, Closeable {
	
	
	private String fileName;
	private ObjectOutputStream objectOutputStream;
	private FileOutputStream fileOutputStream;
	
	private LinkedList<LoggedStudent> studentsList=new LinkedList<LoggedStudent>();
	
	public SerializedLogger(String fileName, boolean append) throws IOException {
		this.fileName = fileName;
		fileOutputStream=new FileOutputStream(fileName,append);
		objectOutputStream=new ObjectOutputStream(fileOutputStream);
	}

	@Override
	public synchronized void close() throws IOException {
		if (objectOutputStream!=null)
			objectOutputStream.close();
	}

	@Override
	public synchronized void log(String status, Student student) {
		LoggedStudent tmpStudent=new LoggedStudent(student,status,new Date().getTime());
		try {
			objectOutputStream.writeObject(tmpStudent);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
