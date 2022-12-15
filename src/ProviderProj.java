import java.io.*;
import java.net.*;
public class ProviderProj{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	ServerThreadProj s;
	userDatabase sharedResource;
	BugDatabase anotherSharedResource;
	ProviderProj(){}
	void run()
	{
		try{
			//1. creating a server socket
			providerSocket = new ServerSocket(2004, 10);
			sharedResource = new userDatabase(); 
			anotherSharedResource = new BugDatabase();
			//2. Wait for connection
			while(true)
			{
				System.out.println("Waiting for connection");
				connection = providerSocket.accept();
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());
				s = new ServerThreadProj(connection, sharedResource, anotherSharedResource);
				s.start();
			}
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		ProviderProj server = new ProviderProj();
		while(true){
			server.run();
		}
	}
}
