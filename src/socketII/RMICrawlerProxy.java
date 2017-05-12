package socketII;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.Crawler;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;

public class RMICrawlerProxy extends UnicastRemoteObject implements CrawlerProxyable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8768402710701491085L;
	private Crawler crawler;

	public RMICrawlerProxy(String ip, int port) throws java.rmi.RemoteException {
		crawler=new Crawler(ip,port);
	}
	
	@Override
	public void run(){
		crawler.run();
	}
	
	@Override
	public void postCancel()  throws java.rmi.RemoteException {
		crawler.postCancel();
		
	}

	@Override
	public boolean add(RemovedInterface e)  throws java.rmi.RemoteException {
		return crawler.add(e);
	}

	@Override
	public boolean add(NotModifiedInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(AddedInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(IterInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(ExtractInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(AgeInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean add(MarkInterface e) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.add(e);
	}

	@Override
	public boolean remove(AddedInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(RemovedInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(NotModifiedInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(IterInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(ExtractInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(AgeInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public boolean remove(MarkInterface arg0) throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.remove(arg0);
	}

	@Override
	public int getMaxIter()  throws java.rmi.RemoteException {
		// TODO Auto-generated method stub
		return crawler.getMaxIter();
	}

	@Override
	public void setMaxIter(int maxIter)  throws java.rmi.RemoteException {
		// TODO Auto-generated method stub
		crawler.setMaxIter(maxIter);
	}

	@Override
	public String getPath() throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		return crawler.getPath();
	}

	@Override
	public void setPath(String path)  throws java.rmi.RemoteException {
		// TODO Auto-generated method stub
		crawler.setPath(path);
	}

	@Override
	public void start() throws java.rmi.RemoteException  {
		// TODO Auto-generated method stub
		crawler.start();
	}

}
