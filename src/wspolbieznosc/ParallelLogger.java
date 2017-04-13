package wspolbieznosc;

import java.util.Iterator;
import java.util.LinkedList;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class ParallelLogger implements Logger {
	
	private CustomThread thread=null;

	private LinkedList<StatusStudent> statList=new LinkedList<StatusStudent>();

	private Logger[] loggers;
	
	private boolean isRunning=false;

	public synchronized void cancel(){
		if (thread!=null){
			isRunning=false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			thread=null;
		}
		
	}
	public ParallelLogger(Logger[] loggers){
		this.loggers=loggers;
	}
	
	public synchronized void start(){
		if (thread==null){
			this.thread=new CustomThread();
			thread.start();	
		}
	}


	@Override
	public synchronized void log(String status, Student student) {
		statList.add(new StatusStudent(status,student));
	}
	
	private class CustomThread extends Thread{
		@Override
		public void run(){
			isRunning=true;
			while (isRunning){
				LinkedList<StatusStudent> clone = (LinkedList<StatusStudent>)statList.clone();
				for (StatusStudent s: clone){
					statList.remove(s);
					for (Logger x:loggers){
						x.log(s.status, s.student);
					}
				}
			}
		}
		
	}


}
