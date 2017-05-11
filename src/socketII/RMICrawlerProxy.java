package socketII;

import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;

public class RMICrawlerProxy implements CrawlerProxyable {
	private Crawler crawler;

	@Override
	public void postCancel() {
		crawler.postCancel();
		
	}

	@Override
	public boolean add(RemovedInterface e) {
		return crawler.add(e);
	}

	@Override
	public boolean add(NotModifiedInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(AddedInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(IterInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(ExtractInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(AgeInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(MarkInterface e) {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean remove(AddedInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(RemovedInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(NotModifiedInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(IterInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(ExtractInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(AgeInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(MarkInterface arg0) {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public int getMaxIter() {
		// TODO Auto-generated method stub
		return crawler.getMaxIter();
	}

	@Override
	public void setMaxIter(int maxIter) {
		// TODO Auto-generated method stub
		crawler.setMaxIter(maxIter);
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return crawler.getPath();
	}

	@Override
	public void setPath(String path) {
		// TODO Auto-generated method stub
		crawler.setPath(path);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		crawler.start();
	}

}
