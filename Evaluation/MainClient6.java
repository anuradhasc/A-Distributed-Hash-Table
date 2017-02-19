import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class MainClient6{
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

	callingHashFunc hf = new callingHashFunc();
	int hashvalue;
	int Serverloc;
	int noOfServers = 8;
	String userInput;
	int[] Portarray = {5050, 5051, 5052, 5053, 5054, 5055, 5056, 5057};
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	String[] Testkeyarr = new String[100000];
	int j = 700000;
	int z = 0;
	for(int i=0; i<100000; i++)
	{
	 z = i+j;
	 Testkeyarr[i] = String.valueOf(z);	
	}
	String[] Testvaluearr = new String[100000];
	Randomstring rs = new Randomstring();
	for(int i=0; i<100000; i++)
	{
	Testvaluearr[i] = rs.randomString(1000);
	//System.out.println(Testvaluearr[i]);
	}
	//while((userInput = stdIn.readLine()) != null)
	//{
	//String[] tokenc = userInput.split(" ");
	//if(tokenc[0].equalsIgnoreCase("Put"))
	//{
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	System.out.println("Time before 100k put:"+ dateFormat.format(date));
	for(int i = 0; i<100000; i++)
	{
	 hashvalue = hf.HashFunc(Testkeyarr[i]);
	 Serverloc = hashvalue % noOfServers;
	 //System.out.println("At Server number:" + Serverloc);
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
	 out.println("Put");
	 out.println("Put");
	 out.println(Testkeyarr[i]);
	 out.println(Testvaluearr[i]);
	 in.readLine();
	 }
	Date date1 = new Date();
	System.out.println("Time after 100k put:"+ dateFormat.format(date1));
	//}
	//if(tokenc[0].equalsIgnoreCase("Get"))
	//{
	// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 Date date2 = new Date();
	 System.out.println("Time before 100k get:"+ dateFormat.format(date2));
	 for(int i = 0; i<100000; i++)
	 {
	 hashvalue = hf.HashFunc(Testkeyarr[i]);
	 //System.out.print(hashvalue);
	 Serverloc = hashvalue % noOfServers;
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
	out.println("Get");
	out.println("Get");
	out.println(Testkeyarr[i]);
	in.readLine();
	}
	Date date3 = new Date();
	System.out.println("Time after 100k get:"+ dateFormat.format(date3));
	//}
	//if(tokenc[0].equalsIgnoreCase("Del"))
	//{
	//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 Date date4 = new Date();
	System.out.println("Time before 100k del:"+ dateFormat.format(date4));
	for(int i = 0; i<100000; i++)
	{
	hashvalue = hf.HashFunc(Testkeyarr[i]);
	Serverloc = hashvalue % noOfServers;
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
	out.println("Del");
	out.println("Del");
	out.println(Testkeyarr[i]);
	in.readLine();
	}
	Date date5 = new Date();
	System.out.println("Time after 100k del:"+ dateFormat.format(date5));
	//}
	//}
			
	out.close();
	in.close();
	stdIn.close();
	indexSocket.close();	
	}		
}
