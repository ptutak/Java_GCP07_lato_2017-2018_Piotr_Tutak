package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemovedInterface extends Remote{
	void handled (Student x)throws RemoteException;
}
