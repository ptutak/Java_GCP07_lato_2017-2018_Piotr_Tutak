package kolekcje_i_algorytmy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ExtractInterface extends Remote{
	public void handled(List<Student> list, OrderMode mode) throws RemoteException;
}
