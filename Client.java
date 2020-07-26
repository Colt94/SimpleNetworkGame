package freeSpace;


import java.io.*;
import java.net.*;



public class Client {

	private String userName;
	private String ipAddress;
	private int port;
	private Socket sock = null;
	private DataInputStream in;
	private DataOutputStream out;
	
	FreeSpace space;
	
	
	public Client(String userName, String ipAddress, int port) {
		this.userName = userName;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public void run() throws IOException  {
		
		sock = new Socket(ipAddress, port);
		//sock.socket.connect('10.0.0.174',111);
		in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
		out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
			
	}
	
	public int dataAvailable() throws IOException{
		
		return in.available();
	}
	
	public String ClientRead() {
		try {
			return in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public void ClientWrite(String move) {
		try {
			out.writeUTF(move);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("TEST");
		}
	}
	
	public void close() {
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class HandleDotState implements Runnable {

	RemoteFreeSpace fs;
	Client client;
	Dot dot;

	public HandleDotState(RemoteFreeSpace fs, Client client, Dot dot) {
		this.fs = fs;
		this.client = client;
		this.dot = dot;
	}
	public void run() {
		while(true) {
			//System.out.print("f");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String move = client.ClientRead();
			//System.out.printf("%s Recieved\n", move);
			if(move.equals("cyn1")) {
				dot.changeYDir(-1);
			}
			if(move.equals("cy1")) {
				dot.changeYDir(1);
			}
			if(move.equals("cx1")) {
				dot.changeXDir(1);
			}
			if(move.equals("cxn1")) {
				dot.changeXDir(-1);
			}
			if(move.equals("stopUp")) {
				dot.stop();
			}
			if(move.equals("stopDown")) {
				dot.stop();
			}
			if(move.equals("stopRight")) {
				dot.stop();
			}
			if(move.equals("stopLeft")) {
				dot.stop();
			}
			client.ClientWrite("confirm");
			
		}
		
	}
	
}
