package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MultiClients extends Thread {
	
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	boolean quite=false;
	public ClientData c;
	
	public MultiClients(Socket OurMultiSocket, client Clients)
	{
		s=OurMultiSocket;
		c=new ClientData();
	}
	public void ClientOutServerIn(String Text)
	{
		//write the line from console to server
		try {
			dout.writeUTF(c.GetChannel()+"="+this.getName()+": "+Text);
			dout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void SetClient(String channel,String Name)
	{
		c.SetName(Name);
		c.SetChannel(channel);
	}
	public void run()
	{
		try {
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			while(!quite)
			{
				try {
					while(din.available()==0)
					{
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//if there is something just show it on console
					//and then go back and do the same
					String reply=din.readUTF();
					String Chan=ExtractChannel(reply);
					PrintReply(Chan,reply);
					//System.out.println(reply);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						din.close();
						dout.close();
						s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				din.close();
				dout.close();
				s.close();
			} catch (IOException x) {
				// TODO Auto-generated catch block
				x.printStackTrace();
			}
		}
	}
	
	public void CloseClient()
	{
		try {
			din.close();
			dout.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String ExtractChannel(String X)
	{
		String[]Y=X.split("=");
		return Y[0];
	}
	public void PrintReply(String Chan,String Rep)
	{
		if(c.GetChannel().equals(Chan))
		{
			String []Y=Rep.split("=");
			System.out.println(Y[1]);
		}
		
	}
	class ClientData
	{
		public String ClientName;
		public String channel;
		
		public void SetChannel(String Chan)
		{
			channel=Chan;
		}
		public void SetName(String name)
		{
			ClientName=name;
		}
		public String GetChannel()
		{
			return channel;
		}
	}
	
}
