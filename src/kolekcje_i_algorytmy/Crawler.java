
package kolekcje_i_algorytmy;
import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Crawler {
	List<Logger> added;
	List<Logger> removed;
	List<Logger> notModified;
	
	HashSet<Student> studentsPrev;
	List<Student> studentsList;
	String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	Crawler(String path){
		this.path=path;
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
		
	}
	void deleted(Student x){
		
	}
	void added(Student x){
		
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
				deleted(x);
			}
		}
		studentsPrev=null;
		for (Student x:studentsList)
			studentsPrev.add(x);
		
	}
	
	public static void main(String[] args){
		Console cons=System.console();
		System.out.println("Podaj sciezke do pliku");
		String tmp=cons.readLine().trim();
		Crawler crawl=new Crawler(tmp);
		try{
			crawl.run();
		} catch (CrawlerException e){e.printStackTrace();}
		
	}

}
