package wspolbieznosc;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import gui1.GuiLogger;
import gui2.MainGUI2;
import javafx.application.Application;
import kolekcje_i_algorytmy.AInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ConsoleLogger;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NInterface;
import kolekcje_i_algorytmy.RInterface;
import kolekcje_i_algorytmy.Student;

public class Monitor {
	private LinkedList<String> pathList=new LinkedList<String>();
	private int maxThreads;
	private LinkedList<Crawler> crawlerList=new LinkedList<Crawler>();
	
	private boolean isRunning;
	
	private MainGUI2 gui=new MainGUI2();
	private Logger[] loggers=new Logger[]{
			new ConsoleLogger(),
//			new MailLogger("pttMailTest@mail.com","pttMailTest@mail.com","smtp.mail.com","ptt_Mail_Test"),
			new GuiLogger(gui)
	};
	
	
	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		if (maxThreads>=0)
			this.maxThreads = maxThreads;
		else
			this.maxThreads=0;
	}

	public void addPath(String path){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() throws MonitorException{
		if (maxThreads<pathList.size())
			throw new MonitorException("Za malo watkow\n");
		isRunning=true;
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
			AInterface addint=(s)->{
				for (Logger log:loggers){
					log.log("ADDED",s);	
				}
			};
			crawl.add(addint);
			RInterface remint=(s)->{
				for (Logger log:loggers){
					log.log("REMOVED",s);	
				}
			};
			crawl.add(remint);
			NInterface nonint=(s)->{
				for (Logger log:loggers){
					log.log("NOT MODIFIED",s);	
				}
			};
			crawl.add(nonint);
			crawl.start();
			crawlerList.add(crawl);

		}
		Application.launch(gui.getClass());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp=new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
	}

}
