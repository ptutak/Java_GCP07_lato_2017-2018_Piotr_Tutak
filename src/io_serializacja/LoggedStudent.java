package io_serializacja;

import java.io.Serializable;

import kolekcje_i_algorytmy.Student;

public class LoggedStudent extends Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7429442625296900459L;
	private long time;
	private Status status;
	
	public LoggedStudent(){
		super();
	}
	

	public LoggedStudent(long time, Status status) {
		super();
		this.time = time;
		this.status = status;
	}


	public LoggedStudent(String firstName, String lastName, int age, double mark, long time, Status status) {
		super(firstName, lastName, age, mark);
		this.time=time;
		this.status=status;
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


	public synchronized void setStatus(Status status) {
		this.status = status;
	}
	
	

}
