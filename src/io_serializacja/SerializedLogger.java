package io_serializacja;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class SerializedLogger implements Logger, Closeable {
	
	private String fileName;
	private ObjectOutputStream objectOutputStream;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private ObjectInputStream objectInputStream;
		
	public LinkedList<LoggedStudent> listStudents() throws IOException{
		fileInputStream=new FileInputStream(fileName);
		objectInputStream=new ObjectInputStream(fileInputStream);
		LinkedList<LoggedStudent> studentsList=new LinkedList<LoggedStudent>();
		while (true){
			LoggedStudent tmpLogStud=null;
			try {
				tmpLogStud = (LoggedStudent) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				break;
			}
			studentsList.add(tmpLogStud);
		}
		fileInputStream.close();
		objectInputStream.close();
		fileInputStream=null;
		objectInputStream=null;
		return studentsList;
	}
	
	public SerializedLogger(String fileName, boolean append) throws IOException {
		this.fileName = fileName;
		fileOutputStream=new FileOutputStream(fileName,append);
		objectOutputStream=new ObjectOutputStream(fileOutputStream);
	}

	@Override
	public synchronized void close() throws IOException {
		if (objectOutputStream!=null)
			objectOutputStream.close();
		if (fileOutputStream!=null)
			fileOutputStream.close();
		if (fileInputStream!=null)
			fileInputStream.close();
		if (objectInputStream!=null)
			objectInputStream.close();
		objectOutputStream=null;
		fileOutputStream=null;
		fileInputStream=null;
		objectInputStream=null;
		
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
