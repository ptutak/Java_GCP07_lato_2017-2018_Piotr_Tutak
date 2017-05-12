package socketII;

import java.rmi.RemoteException;

import kolekcje_i_algorytmy.AddedInterface;
import kolekcje_i_algorytmy.AgeInterface;
import kolekcje_i_algorytmy.ExtractInterface;
import kolekcje_i_algorytmy.IterInterface;
import kolekcje_i_algorytmy.MarkInterface;
import kolekcje_i_algorytmy.NotModifiedInterface;
import kolekcje_i_algorytmy.RemovedInterface;

public interface CrawlerProxyable extends java.rmi.Remote {
	public void postCancel() throws java.rmi.RemoteException;
	
	public void run()throws RemoteException;

	public boolean add(RemovedInterface e) throws java.rmi.RemoteException;
	public boolean add(NotModifiedInterface e) throws java.rmi.RemoteException;
	public boolean add(AddedInterface e) throws java.rmi.RemoteException;
	public boolean add(IterInterface arg0) throws java.rmi.RemoteException;
	public boolean add(ExtractInterface arg0) throws java.rmi.RemoteException;
	public boolean add(AgeInterface arg0) throws java.rmi.RemoteException;
	public boolean add(MarkInterface e) throws java.rmi.RemoteException;
	
	public boolean remove(AddedInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(RemovedInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(NotModifiedInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(IterInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(ExtractInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(AgeInterface arg0) throws java.rmi.RemoteException;
	public boolean remove(MarkInterface arg0) throws java.rmi.RemoteException;
	
	public int getMaxIter() throws java.rmi.RemoteException;
	public void setMaxIter(int maxIter) throws java.rmi.RemoteException;
	public String getPath() throws java.rmi.RemoteException;
	public void setPath(String path) throws java.rmi.RemoteException;
	
	public void start() throws java.rmi.RemoteException;
}
