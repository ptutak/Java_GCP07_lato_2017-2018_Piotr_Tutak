package kolekcje_i_algorytmy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConsoleLogger extends UnicastRemoteObject implements Logger{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8018749466565899282L;

	public ConsoleLogger() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void log(String status, Student student) throws RemoteException{
		System.out.println(status+" : "+student);
	}
}
