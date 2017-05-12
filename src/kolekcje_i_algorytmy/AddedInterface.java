package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddedInterface extends Remote{
	public void handled (Student x)throws RemoteException;
}
