package wspolbieznosc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Monitor {
	private LinkedList<String> pathList=new LinkedList<String>();
	private LinkedList<Crawler> crawlerList=new LinkedList<Crawler>();
	private int maxThreads=10;
	
	private List<AddedInterface> addedList=new LinkedList<AddedInterface>();
	private List<RemovedInterface> removedList=new LinkedList<RemovedInterface>();
	
	private MainGUI2 gui=new MainGUI2();
	private Logger[] loggers=new Logger[]{
			new ConsoleLogger(),
//			new MailLogger("pttMailTest@mail.com","pttMailTest@mail.com","smtp.mail.com","ptt_Mail_Test"),
			new GuiLogger(gui)
	};
	private ParallelLogger logger=new ParallelLogger(loggers);
	
	public synchronized boolean add(RemovedInterface e) {
		return removedList.add(e);
	}
	public synchronized boolean add(AddedInterface e) {
		return addedList.add(e);
	}
	
	private synchronized void addedFire(Student student){
		for (AddedInterface x:addedList){
			x.handled(student);
		}
	}
	
	private synchronized void removedFire(Student student){
		for (RemovedInterface x:removedList){
			x.handled(student);
		}
	}
	
	public synchronized int getMaxThreads() {
		return maxThreads;
	}

	public synchronized void setMaxThreads(int maxThreads) {
		this.maxThreads=maxThreads;
		if (maxThreads<0)
			this.maxThreads = 0;
	}

	public synchronized void addPath(String path){
		pathList.add(path);
	}
	
	public synchronized void cancel(){

		for (Crawler c:crawlerList){
			c.postCancel();
		}
		for (Crawler c:crawlerList){
			try {
				c.join();
			} catch (InterruptedException e) {
			}
		}
		crawlerList.clear();
		logger.cancel();
	}

	public synchronized void start_threads() throws MonitorException{
		if (maxThreads<pathList.size())
			throw new MonitorException("Za malo watkow dla liczby sciezek\n");
		if (maxThreads<=crawlerList.size())
			throw new MonitorException("Za duzo dzialajacych watkow\n");
		AddedInterface addInner=(st)->{
			logger.log("ADDED", st);
		};
		addedList.add(addInner);
		RemovedInterface remInner=(st)->{
			logger.log("REMOVED", st);
		};
		removedList.add(remInner);
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

			crawl.add((AddedInterface)(s)->{
				addedFire(s);
			});

			NotModifiedInterface nonint=(s)->{
				logger.log("NOT MODIFIED", s);
			};
			crawl.add(nonint);

			crawl.add((RemovedInterface)(s)->{
				removedFire(s);
			});
			
			crawl.start();
			crawlerList.add(crawl);
		}
		Thread guiThread=new Thread(new Runnable(){public void run(){
			Application.launch(gui.getClass());
		}});
		guiThread.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.start();

	}

	public static void main(String[] args) {
		String tmp=new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
		Monitor monitor=new Monitor();
		for (int i=0;i<10;++i)
			monitor.addPath(tmp);
		
		try {
			monitor.start_threads();
			monitor.start_threads();
		} catch (MonitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.cancel();
	}

}
