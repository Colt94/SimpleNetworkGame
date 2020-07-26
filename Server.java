package freeSpace;

import java.io.*;
import java.net.*;

public class Server {

	private ServerSocket srvSock;
	private Socket sock1, sock2;
	
	private int port;
	int client1, client2;
	String move1,move2;
	private boolean twoClients;
	
	public DataInputStream in1, in2 = null;
	public DataOutputStream out1, out2 = null;
	
	
	
	public Server(int port, int PlayerCount) {
		
		this.port = port;
		
		if (PlayerCount == 2) {
			twoClients = true;
		}
		else {
			twoClients = false;
		}
	}
	
	public void run() throws IOException {
		
		srvSock = new ServerSocket(port);
		
		if (twoClients) {
			System.out.print("Waiting for two connections...\n");
			Socket sock1 = srvSock.accept();
			System.out.print("Client 1 connected\n");
			
			
			in1 = new DataInputStream(new BufferedInputStream(sock1.getInputStream()));
			out1 = new DataOutputStream(new BufferedOutputStream(sock1.getOutputStream()));
			
			//write player color to connected client1
			out1.writeUTF("0");
			out1.flush();
			
			Socket sock2 = srvSock.accept();
			System.out.print("Client 2 connected\n");
		
			in2 = new DataInputStream(new BufferedInputStream(sock2.getInputStream()));
			out2 = new DataOutputStream(new BufferedOutputStream(sock2.getOutputStream()));
			
			//write player color to connected client2
			out2.writeUTF("1");
			out2.flush();
			
		}
		else {
			System.out.print("Waiting for client connection...\n");
			Socket sock1 = srvSock.accept();
			System.out.print("Client connected\n");
			
			in1 = new DataInputStream(new BufferedInputStream(sock1.getInputStream()));
			out1 = new DataOutputStream(new BufferedOutputStream(sock1.getOutputStream()));
		}
		
		out1.writeUTF("WELCOM");
		out2.writeUTF("WELCOME");
		out1.flush();
		out2.flush();
		System.out.print("WELCOME\n");
		
		//board = new Board();
		
	}
	
	
	public void ServerRead(int client) throws Exception {
		
		if(client == 1) {
			
			move1 = in1.readUTF();
			
		}
		else {
			move2 = in2.readUTF();
		}
	}
	
	public void ServerWrite(int client, String move) {
		
		if(client == 1) {
			try {
				out1.writeUTF(move);
				out1.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				out2.writeUTF(move);
				out2.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	public void close() {
		try {
			srvSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Server srv = new Server(111, 2);
		try {
			srv.run();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		(new Thread(new ServerHandler(srv))).start();
	}
}

class ServerHandler implements Runnable {
	
	Server srv;
	
	public ServerHandler(Server srv) {
		this.srv = srv;
	}
	
	public void run() {
		while(true) {
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				int data1 = srv.in1.available();
				int data2 = srv.in2.available();
				if(data1 != 0) {
					//String[] values = CSV.split(",");
					System.out.print("Data read from client1\n");
					String move1 = srv.in1.readUTF();
					srv.out2.writeUTF(move1); //send move to other client
					srv.out2.flush();
					System.out.print("Client1 move sent to client2\n");
					
					String c = srv.in2.readUTF();
				
					System.out.print("Client2 comfirmed\n");
					
					srv.out1.writeUTF("ok"); //send ok back to calling client
					srv.out1.flush();
					System.out.print("Sent ok to client1\n");
				}
			
				else if (data2 != 0) {
					//String[] values = CSV.split(",");
					String move2 = srv.in2.readUTF();
					System.out.printf("Data read %s from client2\n", move2);
					srv.out1.writeUTF(move2);
					srv.out1.flush();
					System.out.printf("%s sent to client1\n", move2);
					
					
					String c = srv.in1.readUTF();
					
					System.out.print("Client1 comfirmed\n");
					
					srv.out2.writeUTF("ok");
					srv.out2.flush();
					System.out.print("Sent ok to Client2");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
}