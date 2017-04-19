package io_serializacja;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class BinaryLogger implements Logger, Closeable {

	private String fileName;
	private DataOutputStream dataOutputStream;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
		
	public synchronized LinkedList<LoggedStudent> listStudents() throws IOException {
		fileInputStream=new FileInputStream(fileName);
		dataInputStream=new DataInputStream(fileInputStream);
		LinkedList<LoggedStudent> studentsList=new LinkedList<LoggedStudent>();
		while (true){
			LoggedStudent tmpLogStud=new LoggedStudent();
			try {
				tmpLogStud.setFirstName(dataInputStream.readUTF());
				tmpLogStud.setLastName(dataInputStream.readUTF());
				tmpLogStud.setAge(dataInputStream.readInt());
				tmpLogStud.setMark(dataInputStream.readDouble());
				tmpLogStud.setStatus(dataInputStream.readUTF());
				tmpLogStud.setTime(dataInputStream.readLong());
			} catch (IOException e) {
				break;
			}
			studentsList.add(tmpLogStud);
		}
		fileInputStream.close();
		dataInputStream.close();
		fileInputStream=null;
		dataInputStream=null;
		return studentsList;
	}
	
	public BinaryLogger(String fileName, boolean append) throws IOException {
		this.fileName = fileName;
		fileOutputStream=new FileOutputStream(fileName,append);
		dataOutputStream=new DataOutputStream(fileOutputStream);
	}

	@Override
	public synchronized void close() throws IOException {
		if (dataOutputStream!=null)
			dataOutputStream.close();
		if (fileOutputStream!=null)
			fileOutputStream.close();
		if (fileInputStream!=null)
			fileInputStream.close();
		if (dataInputStream!=null)
			dataInputStream.close();
		dataOutputStream=null;
		fileOutputStream=null;
		fileInputStream=null;
		dataInputStream=null;
		
	}

	@Override
	public synchronized void log(String status, Student student) {
		long time=new Date().getTime();
		try {
			dataOutputStream.writeUTF(student.getFirstName());
			dataOutputStream.writeUTF(student.getLastName());
			dataOutputStream.writeInt(student.getAge());
			dataOutputStream.writeDouble(student.getMark());
			dataOutputStream.writeUTF(status);
			dataOutputStream.writeLong(time);
			dataOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
