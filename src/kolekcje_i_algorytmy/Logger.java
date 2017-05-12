package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Logger extends Remote{
	public void log(String status, Student student) throws RemoteException;
}
