package wspolbieznosc;

import java.util.LinkedList;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class ParallelLogger implements Logger {
	
	
	
	private Logger[] loggers;
	
	public ParallelLogger(Logger[] loggers){
		this.loggers=loggers;
	}

	@Override
	public synchronized void log(String status, Student student) {
		for (Logger x:loggers){
				x.log(status, student);
			}
	}
	

}
