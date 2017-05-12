package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MarkInterface extends Remote{
	public void handled(double min, double max) throws RemoteException;
}
