package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Queue;


public class Soctec {

	public static void main(String[] args) throws Throwable {
		ServerSocket listener = new ServerSocket(10000);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    out.println(true);
                    System.out.print(socket.getOutputStream());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
	}

}
