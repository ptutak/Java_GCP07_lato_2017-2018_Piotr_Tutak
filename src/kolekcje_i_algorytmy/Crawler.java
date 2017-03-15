
package kolekcje_i_algorytmy;
import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Crawler {

	private int iteration;
	private String path;
	private List<AInterface> addedList;
	private List<IterInterface> iterList;
	private List<RInterface> removedList;
	private List<NInterface> notModifiedList;
	private List<ExtractInterface> extractList;
	private List<AgeInterface> ageList;
	private List<MarkInterface> markList;
	
	private Set<Student> studentsPrev;
	private List<Student> studentsList;

	public boolean add(RInterface e) {
		return removedList.add(e);
	}
	public boolean add(NInterface e) {
		return notModifiedList.add(e);
	}
	public boolean add(AInterface e) {
		return addedList.add(e);
	}
	public boolean add(IterInterface arg0) {
		return iterList.add(arg0);
	}
	public boolean add(ExtractInterface arg0) {
		return extractList.add(arg0);
	}
	public boolean add(AgeInterface arg0) {
		return ageList.add(arg0);
	}
	public boolean add(MarkInterface e) {
		return markList.add(e);
	}

	public boolean remove(AInterface arg0) {
		return addedList.remove(arg0);
	}
	public boolean remove(RInterface arg0) {
		return removedList.remove(arg0);
	}
	public boolean remove(NInterface arg0) {
		return notModifiedList.remove(arg0);
	}
	public boolean remove(IterInterface arg0) {
		return iterList.remove(arg0);
	}
	public boolean remove(ExtractInterface arg0) {
		return extractList.remove(arg0);
	}
	public boolean remove(AgeInterface arg0) {
		return ageList.remove(arg0);
	}
	public boolean remove(MarkInterface arg0) {
		return iterList.remove(arg0);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	Crawler(String path){
		this.path=path;
		iteration=0;
	}
	List<Student> extractStudents(OrderMode mode){
		switch(mode){
		case AGE:
			Collections.sort(studentsList,(left,right)-> Integer.compare(left.getAge(), right.getAge()));
			break;
		case MARK:
			Collections.sort(studentsList,(left,right)->Double.compare(left.getMark(), right.getMark()));
			break;
		case FIRST_NAME:
			Collections.sort(studentsList,(left,right)->{
				if (left.getFirstName()!=null){
					return left.getFirstName().compareTo(right.getFirstName());
				}
				return 0;
			});
			break;
		case LAST_NAME:
			Collections.sort(studentsList,(left,right)->{
				if (left.getLastName()!=null){
					return left.getLastName().compareTo(right.getLastName());
				}
				return 0;
			});
			break;
		}

		return studentsList;
	}
	double extractMark(ExtremumMode mode){
		double ret=studentsList.get(0).getMark();
		for (Student x:studentsList){
			switch (mode){
			case MIN:
				if (x.getMark()<ret)
					ret=x.getMark();
				break;
			case MAX:
				if (x.getMark()>ret)
					ret=x.getMark();
			}
		}
		return ret;
	}
	int extractAge(ExtremumMode mode){
		int ret=studentsList.get(0).getAge();
		for (Student x:studentsList){
			switch (mode){
			case MIN:
				if (x.getAge()<ret)
					ret=x.getAge();
				break;
			case MAX:
				if (x.getAge()>ret)
					ret=x.getAge();
			}
		}
		return ret;
	}
	
	void notModified(Student x){
		for (NInterface i:notModifiedList){
			i.handled(x);
		}
	}

	void added(Student x){
		for (AInterface i:addedList){
			i.handled(x);
		}		
	}
	void removed(Student x){
		for (RInterface i:removedList){
			i.handled(x);
		}		
	}	
	void run() throws CrawlerException{
		if (path==null)
			throw new CrawlerException("Nie zdefiniowana sciezka path");
		try{
			studentsList = StudentsParser.parse( new URL(path));
		} catch (IOException e) {e.printStackTrace();}
		for (Student x:studentsList){
			if (studentsPrev.contains(x)){
				notModified(x);
			} else {
				added(x);
			}
		}
		for (Student x:studentsPrev){
			if (!studentsList.contains(x)){
				removed(x);
			}
		}
		for (IterInterface x:iterList)
			x.handled(iteration);
		for (ExtractInterface x:extractList)
			x.handled(studentsList);
		for (AgeInterface x:ageList)
			x.handled(extractAge(ExtremumMode.MIN), extractAge(ExtremumMode.MAX));
		for (MarkInterface x:markList)
			x.handled(extractMark(ExtremumMode.MIN), extractMark(ExtremumMode.MAX));
		studentsPrev=null;
		for (Student x:studentsList)
			studentsPrev.add(x);
		try{
			Thread.sleep(10000);
		} catch (InterruptedException e){e.printStackTrace();}
	}
	
	public static void main(String[] args){
		
		final Logger[] loggers=new Logger[]{
				new ConsoleLogger(),
				new MailLogger("p.hicsic@gmail.com","p.hicsic@gmail.com","smtp.gmail.com")
		};
		
		Console cons=System.console();
		System.out.println("Podaj sciezke do pliku");
		String tmp=cons.readLine().trim();
		Crawler crawl=new Crawler(tmp);
		AgeInterface x=(s,t)->{};
		crawl.add(x);
		try{
			crawl.run();
		} catch (CrawlerException e){e.printStackTrace();}
		
		
	}

}
