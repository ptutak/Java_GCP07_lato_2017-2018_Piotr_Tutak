package gui2;

import java.util.concurrent.TimeUnit;

import gui1.GuiLogger;
import gui1.MainGUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.AInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ConsoleLogger;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.CrawlerException;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NInterface;
import kolekcje_i_algorytmy.RInterface;
import kolekcje_i_algorytmy.Student;

public class MainGUI2 extends Application {
	
	private static MBorderPane borderPaneController;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader=new FXMLLoader(this.getClass().getResource("borderPane.fxml"));
		Parent mainNode=loader.load();
		borderPaneController=loader.getController();
		Scene scene=new Scene(mainNode);
		stage.setScene(scene);
		stage.show();
	}
	
	public static synchronized void addStudent(Student student){
		borderPaneController.addStudent(student);
	}
	
	public static synchronized void removeStudent(Student student){
		borderPaneController.removeStudent(student);
	}
	
	public static synchronized void notModifiedStudent(Student student){
		borderPaneController.notModifiedStudent(student);
	}

	public static void main(String[] args) {
		MainGUI2 gui=new MainGUI2();
	    
		Thread crawlLogger=new Thread(new Runnable(){public void run(){
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final Logger[] loggers=new Logger[]{
					new ConsoleLogger(),
//					new MailLogger("pttMailTest@mail.com","pttMailTest@mail.com","smtp.mail.com","ptt_Mail_Test"),
					new GuiLogger(gui)
			};
			String tmp=new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
			Crawler crawl=new Crawler(tmp,0);
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
			try{
				crawl.run();
			} catch (CrawlerException e){e.printStackTrace();}
		}});
		crawlLogger.start();
		Application.launch(gui.getClass());
		try {
			crawlLogger.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

