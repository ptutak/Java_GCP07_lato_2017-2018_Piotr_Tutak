package io_serializacja;

import java.io.Serializable;
import kolekcje_i_algorytmy.Student;

public class LoggedStudent extends Student implements Serializable {
	
	private static final long serialVersionUID = 7429442625296900459L;
	private long time;
	private Status status;

	
	
	public LoggedStudent(){
		super();
		time=0;
		status=null;
	}
	

	public LoggedStudent(Student student,String status,long time) {
		super(student.getFirstName(),student.getLastName(),student.getAge(),student.getMark());
		this.time = time;
		if (status.equals("REMOVED"))
			this.status=Status.REMOVED;
		else if (status.equals("ADDED"))
			this.status=Status.ADDED;
		else if (status.equals("NOT MODIFIED"))
			this.status=Status.NOT_MODIFIED;
	}


	public LoggedStudent(String firstName, String lastName, int age, double mark, String status, long time) {
		super(firstName, lastName, age, mark);
		this.time=time;
		if (status.equals("REMOVED"))
			this.status=Status.REMOVED;
		else if (status.equals("ADDED"))
			this.status=Status.ADDED;
		else if (status.equals("NOT MODIFIED"))
			this.status=Status.NOT_MODIFIED;
		
	}


	public synchronized long getTime() {
		return time;
	}


	public synchronized Status getStatus() {
		return status;
	}


	public synchronized void setTime(long time) {
		this.time = time;
	}


	public synchronized void setStatus(String status) {
		if (status.equals("REMOVED"))
			this.status=Status.REMOVED;
		else if (status.equals("ADDED"))
			this.status=Status.ADDED;
		else if (status.equals("NOT MODIFIED"))
			this.status=Status.NOT_MODIFIED;
	}
	@Override
	public synchronized String toString(){
		return time+" "+status+" "+super.toString();
	}
	
	

}
