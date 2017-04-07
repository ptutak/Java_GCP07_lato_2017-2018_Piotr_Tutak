package wspolbieznosc;

import java.util.Iterator;
import java.util.LinkedList;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class ParallelLogger extends Thread implements Logger {

	private LinkedList<StatusStudent> statList=new LinkedList<StatusStudent>();

	private Logger[] loggers;

	public ParallelLogger(Logger[] loggers){
		this.loggers=loggers;
	}

	public void run(){
		Iterator p=statList.iterator();
		while (true)
			while(p.hasNext()){
				StatusStudent x=(StatusStudent)p.next();
				for (Logger y:loggers){
					y.log(x.status, x.student);
				}
				p.remove();
			}
	}

	@Override
	public synchronized void log(String status, Student student) {
		statList.add(new StatusStudent(status,student));
	}


}
