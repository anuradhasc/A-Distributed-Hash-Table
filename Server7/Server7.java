import java.net.*; 
import java.util.*;
import java.io.*; 
import java.util.concurrent.ConcurrentHashMap;

public class Server7 extends Thread {
	
	protected Socket clientSocket;
static Map<String, String> StoreTable = new ConcurrentHashMap<String, String>();
	public static void main(String[] args) throws IOException{

	String strLine;
	FileInputStream fstream = new FileInputStream ("config.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        while((strLine = br.readLine()) != null)
        {
        	System.out.println(strLine);
        }

	ServerSocket serversock = null;
	try{
	serversock = new ServerSocket(5057);
System.out.println("Connection Socket Created : Port number 5057: Server7");
	try{
	while(true){
	System.out.println("Waiting for Connection on port 5057");
	new Server7 (serversock.accept());
	}
	}
	catch (IOException e) 
        { 
         System.err.println("Accept failed."); 
         System.exit(1); 
        } 	
	}
	catch (IOException e) 
        { 
         System.err.println("Could not listen on port: 5057."); 
         System.exit(1); 
        } 
	finally
        {
         try {
              serversock.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Could not close port: 5057."); 
              System.exit(1); 
             } 
        }
	}
	private Server7 (Socket clientsock){
		
		clientSocket = clientsock;
		start();
	}
	
	public void run(){
		System.out.println ("New Communication Thread Started");
		try
		{
	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
			String inputLine;
			String Value;
			while((inputLine = in.readLine()) != null)
			{
				String function = in.readLine();
				System.out.println(function);
				
		        if(function.equalsIgnoreCase("Put"))
		        {
				String key = in.readLine();
				System.out.println(key);
				String HashValue = in.readLine();
				System.out.println(HashValue);
		        	StoreTable.put(key, HashValue);
		        	System.out.println("Entry added");
				out.println("Entry added at Server7");
		        }
		        if(function.equalsIgnoreCase("Get"))
		        {
				String key = in.readLine();
				System.out.println(key);
		        	Value = StoreTable.get(key);
				System.out.println("Value is:"+Value);
		        	out.println("Value is:"+Value +"Server7");
		        }
		        if(function.equalsIgnoreCase("Del"))
		        {
				String key = in.readLine();
				System.out.println(key);
		        	StoreTable.remove(key);
				System.out.println("Entry removed");
		        	out.println("Entry removed from Server7");
		        }
		        
			}	
			in.close();
			out.close();
		}
		catch(IOException e)
		{
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}	
	}
}

