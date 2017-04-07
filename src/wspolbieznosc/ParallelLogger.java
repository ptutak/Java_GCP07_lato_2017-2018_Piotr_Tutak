package wspolbieznosc;

import java.util.Iterator;
import java.util.LinkedList;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class ParallelLogger extends Thread implements Logger {

	private LinkedList<StatusStudent> statList=new LinkedList<StatusStudent>();

	private Logger[] loggers;
	
	private boolean isRunning=false;

	public synchronized void cancel(){
		isRunning=false;
	}
	public ParallelLogger(Logger[] loggers){
		this.loggers=loggers;
	}

	public void run(){
		isRunning=true;
		while (isRunning){
			
			for (StatusStudent s: (LinkedList<StatusStudent>)statList.clone()){
				statList.remove(s);
				for (Logger x:loggers){
					x.log(s.status, s.student);
				}
			}
		}
			
	}

	@Override
	public synchronized void log(String status, Student student) {
		statList.add(new StatusStudent(status,student));
	}


}
