package wspolbieznosc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import gui1.GuiLogger;
import gui2.MainGUI2;
import javafx.application.Application;
import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ConsoleLogger;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;
import kolekcje_i_algorytmy.Student;

public class Monitor extends Thread{
	private LinkedList<String> pathList=new LinkedList<String>();
	private LinkedList<Crawler> crawlerList=new LinkedList<Crawler>();
	private int maxThreads=10;
	
	private List<AddedInterface> addedList=new LinkedList<AddedInterface>();
	private List<RemovedInterface> removedList=new LinkedList<RemovedInterface>();
	
	private boolean isRunning=false;
	
	private MainGUI2 gui=new MainGUI2();
	private Logger[] loggers=new Logger[]{
			new ConsoleLogger(),
//			new MailLogger("pttMailTest@mail.com","pttMailTest@mail.com","smtp.mail.com","ptt_Mail_Test"),
			new GuiLogger(gui)
	};
	private ParallelLogger logger=new ParallelLogger(loggers);
	
	public boolean add(RemovedInterface e) {
		return removedList.add(e);
	}
	public boolean add(AddedInterface e) {
		return addedList.add(e);
	}
	public boolean remove(AddedInterface arg0) {
		return addedList.remove(arg0);
	}
	public boolean remove(RemovedInterface arg0) {
		return removedList.remove(arg0);
	}
	
	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads=maxThreads;
		if (maxThreads<0)
			this.maxThreads = 0;
	}

	public void addPath(String path){
		pathList.add(path);
	}
	
	public synchronized void cancel(){
		isRunning=false;
		for (Crawler c:crawlerList){
			c.postCancel();
		}
		for (Crawler c:crawlerList){
			try {
				c.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run(){
		isRunning=true;
		Iterator addedIter=addedList.iterator();
		Iterator removedIter=removedList.iterator();
		while (isRunning){
			while(addedIter.hasNext()){
				StatusStudent x=(StatusStudent)addedIter.next();
				for (Logger y:loggers){
					y.log(x.status, x.student);
				}
				addedIter.remove();
			}
			while (removedIter.hasNext()){
				StatusStudent x=(StatusStudent)removedIter.next();
				for (Logger y:loggers){
					y.log(x.status, x.student);
				}
				removedIter.remove();
			}
		}
	}

	public void start_threads() throws MonitorException{
		if (maxThreads<pathList.size())
			throw new MonitorException("Za malo watkow\n");
		for (String path:pathList){
			Crawler crawl=new Crawler(path,0);
			AgeInterface aint=(min,max)->{System.out.println("Age: <"+min+","+max+">");};
			crawl.add(aint);
			MarkInterface mint=(min,max)->{System.out.println("Mark: <"+min+","+max+">");};
			crawl.add(mint);
			ExtractInterface eint=(list,mode)->{
				String m=new String();
				switch(mode){
				case FIRST_NAME:
					m="First Name";
					break;
				case LAST_NAME:
					m="Last Name";
					break;
				case AGE:
					m="Age";
					break;
				case MARK:
					m="Mark";
					break;
				}
				System.out.println("Ordered by "+m+":");
				for (Student x:list){
					System.out.println(x);
				}
			};
			crawl.add(eint);
			IterInterface iint=(iter)->{System.out.println("Iteracja numer: "+iter);};
			crawl.add(iint);
			AddedInterface addInner=(st)->{
				logger.log("ADDED", st);
			};
			AddedInterface addint=(s)->{
				addedList.add(addInner);
			};
			crawl.add(addint);
			RemovedInterface remInner=(st)->{
				logger.log("REMOVED", st);
			};
			RemovedInterface remint=(s)->{
				removedList.add(remInner);
			};
			NotModifiedInterface nonint=(s)->{
				logger.log("NOT MODIFIED", s);
			};
			crawl.add(nonint);
			crawl.add(remint);
			
			crawl.start();
			crawlerList.add(crawl);
		}
		this.start();
		Application.launch(gui.getClass());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp=new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
	}

}
