package Client;
///////////////////////////////////////
//Reference Pages used to understand the socket and some other functions:
//https://www.tutorialspoint.com/java/io/dataoutputstream_writeutf.html
////////////////////////////////////////
//stream of input and output

import java.io.DataInputStream;
import java.io.DataOutputStream;
//////////////////////////////////////
import java.io.IOException;
import java.net.Socket;//this is the socket package
/*dont under any circumstance remove this import XD*/
import java.net.UnknownHostException;
///////////////////////////////////////
//our scanner import
import java.util.Scanner;

///////////////////////////////////////
public class client {
    /////////////////////////////////////
    //better than sending them as an arguments to each function
    Socket s;
    DataInputStream din;
    DataOutputStream dout;

    ////////////////////////////////////
    public static void main(String[] args) {
        new client();

    }

    public client() {
        try {
            s = new Socket("localhost", 3333);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            ListenForInput();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Listen for input from user and response from server
    //user will use quite for leaving the chat
    public void ListenForInput() {
        //Scanners are used to read input of user from conceal
        Scanner console = new Scanner(System.in);
        while (true) {
            //waiting for a line form console
            while (!console.hasNextLine())//only run upon pressing run
            {//make sure not to leave thread awake, my cpu was overloaded XD
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String input = console.nextLine();//this method will automatically get the new line
            if (input.toLowerCase().equals("quit")) {
                break;
            }
            try {
                //write the line from console to server
                dout.writeUTF(input);//the other method is redoTF uses checksum to check data
                //we need to wait for our data now
                //if there is nothing coming we just have to wait
                while (din.available() == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //if there is something just show it on console
                //and then go back and do the same
                String reply = din.readUTF();
                System.out.println(reply);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
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
    }

}
