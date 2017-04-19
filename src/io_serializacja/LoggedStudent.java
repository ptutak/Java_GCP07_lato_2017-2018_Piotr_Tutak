package io_serializacja;

import java.io.Serializable;

import kolekcje_i_algorytmy.Student;

public class LoggedStudent extends Student implements Serializable {
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
	
	

}
