package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IterInterface extends Remote {
	void handled(int iteration)throws RemoteException;
}
