package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    ServerSocket ss;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;

    public static void main(String[] args) {
        new server();

    }

    public server() {
        try {
            ss = new ServerSocket(3333);
            s = ss.accept();//will block until there is another socket connection
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            listenD();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//make sure its bloody same with client it took my 15 min to realize that XD
    }

    public void listenD() {
        while (true) {
            try {
                while (din.available() == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                String datain = din.readUTF();

                dout.writeUTF(datain + " data returned from server,all good for second phase");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;//if client is Disconnected
            }
        }

        try {
            din.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            dout.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
