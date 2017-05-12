package socketII;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.naming.NamingException;

public class ServerRMI {

	public static void main( String[] args ) throws NamingException, RemoteException, InterruptedException, AlreadyBoundException
	{
		int port = 5060;
		
		String name = "rmi://" + port + "/CrawlerProxy";
		Registry registry = LocateRegistry.createRegistry( port );
		
		try
		{
			String url=new String("http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt");
			
			RMICrawlerProxy crawlerProxy = new RMICrawlerProxy(url,0);
			registry.bind( name, crawlerProxy );

			System.out.println( "Type 'exit' to exit server." );
			Scanner scanner = new Scanner( System.in );
			
			while ( true )
			{
				if ( scanner.hasNextLine() )
				{
					if ( "exit".equals( scanner.nextLine() ) )
						break;
				}
			}
			
			scanner.close();
		}
		finally
		{
			UnicastRemoteObject.unexportObject( registry, true );
			System.out.println( "Server stopped." );
		}
	}
}
