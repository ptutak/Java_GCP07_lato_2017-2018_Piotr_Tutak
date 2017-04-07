package gui2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import gui1.GuiLogger;
import gui1.MainGUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ConsoleLogger;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.CrawlerException;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;
import kolekcje_i_algorytmy.Student;

public class MainGUI2 extends Application {
	
	private static BorderPaneController borderPaneController;
	private LoginWindowController loginWindowController;
	private NewUserWindowController newUserWindowController;
	private LinkedList<LogPass> logPassList=new LinkedList<LogPass>();
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loaderMain=new FXMLLoader(this.getClass().getResource("borderPane.fxml"));
		Parent mainNode=loaderMain.load();
		borderPaneController=loaderMain.getController();
		Scene mainScene=new Scene(mainNode);
		
		FXMLLoader loaderLogin=new FXMLLoader(this.getClass().getResource("loginWindow.fxml"));
		Parent loginNode=loaderLogin.load();
		loginWindowController=loaderLogin.getController();
		loginWindowController.setStage(stage);
		loginWindowController.setMainWindowScene(mainScene);
		loginWindowController.setLogPassList(logPassList);
		Scene loginScene=new Scene(loginNode);
		
		FXMLLoader loaderNew=new FXMLLoader(this.getClass().getResource("newUserWindow.fxml"));
		Parent newUserNode=loaderNew.load();
		newUserWindowController=loaderNew.getController();
		newUserWindowController.setStage(stage);
		newUserWindowController.setLoginScene(loginScene);
		newUserWindowController.setLogPassList(logPassList);
		Scene newUserScene=new Scene(newUserNode);
		
		loginWindowController.setNewUserScene(newUserScene);
		
		stage.setScene(loginScene);
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
			AddedInterface addint=(s)->{
				for (Logger log:loggers){
						log.log("ADDED",s);	
					
				}
			};
			crawl.add(addint);
			RemovedInterface remint=(s)->{
				for (Logger log:loggers){
						log.log("REMOVED",s);	
					
				}
			};
			crawl.add(remint);
			NotModifiedInterface nonint=(s)->{
				for (Logger log:loggers){
						log.log("NOT MODIFIED",s);	
					
				}
			};
			crawl.add(nonint);
			crawl.run();
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

