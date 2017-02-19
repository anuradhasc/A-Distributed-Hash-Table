import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class MainClient{
	public static void main(String[] args) throws IOException{
		
	String serverHostname = new String ("mandar-VirtualBox");
	System.out.println ("Attemping to connect to host " + serverHostname + " on Server.");

	Socket indexSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	System.out.println("Enter the operation you want to perform according to syntax as:");
	System.out.println("For Put: Put Key Value");
	System.out.println("For Get: Get Key");
	System.out.println("For Delete: Del Key");

	/*String strLine;
	FileInputStream fstream = new FileInputStream ("config.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        while((strLine = br.readLine()) != null)
        {
        	System.out.println(strLine);
        }*/

	callingHashFunc hf = new callingHashFunc();
	int hashvalue;
	int Serverloc;
	int noOfServers = 8;
	String userInput;
	int[] Portarray = {5050, 5051, 5052, 5053, 5054, 5055, 5056, 5057};
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	
	while((userInput = stdIn.readLine()) != null)
	{
	 String[] tokenc = userInput.split(" ");
	 hashvalue = hf.HashFunc(tokenc[1]);
	 System.out.println("Hashvalue is " + hashvalue);
	 Serverloc = hashvalue % noOfServers;
	 System.out.println("At Server number:" + Serverloc);
	 try 
	 {
	 indexSocket = new Socket(serverHostname, Portarray[Serverloc]);
	 out = new PrintWriter(indexSocket.getOutputStream(), true);
 in = new BufferedReader(new InputStreamReader(indexSocket.getInputStream()));
	 } catch (UnknownHostException e)
	 {
	 System.err.println("Don't know about host: " + serverHostname);
	 System.exit(1);
	 } catch (IOException e) 
	 {
	 System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
	 System.exit(1);
	 }
	if(tokenc[0].equalsIgnoreCase("Put"))
	{
	 out.println(tokenc[0]);
	 out.println(tokenc[0]);
	 out.println(tokenc[1]);
	 out.println(tokenc[2]);
	 System.out.println(in.readLine());
	 }
	if(tokenc[0].equalsIgnoreCase("Get"))
	{
	
	out.println(tokenc[0]);
	out.println(tokenc[0]);
	out.println(tokenc[1]);
	System.out.println(in.readLine());
	}
	if(tokenc[0].equalsIgnoreCase("Del"))
	{
	
	out.println(tokenc[0]);
	out.println(tokenc[0]);
	out.println(tokenc[1]);
	System.out.println(in.readLine());
	}
	}	
	out.close();
	in.close();
	stdIn.close();
	indexSocket.close();	
	}		
}
