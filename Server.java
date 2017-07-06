package com.aowin.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss=new ServerSocket(9089);
		ArrayList<Socket> listSocket=new ArrayList<Socket> ();
		while(true){
			System.out.println("等待客户机的连接");
			try {
				Socket s=ss.accept();
				 listSocket.add(s);
				 new ServerThread(listSocket,s).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	
	static class ServerThread extends Thread{
		ArrayList<Socket> list;
		Socket soc;
		
		BufferedReader br1;
		
		
		public ServerThread(ArrayList<Socket> list, Socket soc) {
			super();
			this.list = list;
			this.soc = soc;
		}

		public void run(){
			InputStream is;
			try {
				is=soc.getInputStream();
				br1=new BufferedReader(new InputStreamReader(is));
				while(true){
					String message=br1.readLine();
					System.out.println(message);
					for(Socket s:list){
						PrintWriter pw=new PrintWriter(s.getOutputStream());
						pw.println(message);
						pw.flush();
					}
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
