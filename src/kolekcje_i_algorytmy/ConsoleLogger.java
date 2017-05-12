package kolekcje_i_algorytmy;

import java.io.Serializable;

public class ConsoleLogger implements Logger, java.io.Serializable {
	public void log(String status, Student student){
		System.out.println(status+" : "+student);
	}
}
