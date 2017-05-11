package socketII;

import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;

public interface CrawlerProxyable {
	public void postCancel();

	public boolean add(RemovedInterface e);
	public boolean add(NotModifiedInterface e);
	public boolean add(AddedInterface e);
	public boolean add(IterInterface arg0);
	public boolean add(ExtractInterface arg0);
	public boolean add(AgeInterface arg0);
	public boolean add(MarkInterface e);
	
	public boolean remove(AddedInterface arg0);
	public boolean remove(RemovedInterface arg0);
	public boolean remove(NotModifiedInterface arg0);
	public boolean remove(IterInterface arg0);
	public boolean remove(ExtractInterface arg0);
	public boolean remove(AgeInterface arg0);
	public boolean remove(MarkInterface arg0);
	
	public int getMaxIter();
	public void setMaxIter(int maxIter);
	public String getPath();
	public void setPath(String path);
	
	public void start();
	
}
