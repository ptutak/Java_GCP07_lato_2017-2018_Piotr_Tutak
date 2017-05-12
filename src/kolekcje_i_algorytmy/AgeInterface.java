package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgeInterface extends Remote {
	public void handled(int min, int max) throws RemoteException;
}
