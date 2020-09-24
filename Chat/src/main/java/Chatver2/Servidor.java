/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2;
import javax.swing.*;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author A
 */
public class Servidor {
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
    
}
class MarcoServidor extends JFrame implements Runnable{
	public int puerto;
	public MarcoServidor(){
		
                this.puerto = 12001;
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
                
                Thread th = new Thread(this);
                
                th.start();
		
		}
	
	private	JTextArea areatexto;

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            ServerSocket servidor = new ServerSocket(this.puerto);
            Paquete rec;
            while (true){
                
                Socket s = servidor.accept();
                ObjectInputStream pk = new ObjectInputStream(s.getInputStream());
                
                rec = (Paquete)pk.readObject();
                
                areatexto.append("Mensaje: "+rec.getMsg());
                
                Socket sendto = new Socket("127.0.0.1",Integer.parseInt(rec.getPort()));
                ObjectOutputStream tosend = new ObjectOutputStream(sendto.getOutputStream());
                
                tosend.writeObject(pk);
                
                sendto.close();
                
                s.close();
            }
        }catch(Exception e){
            System.out.println(e);
            this.puerto++;
        }
    }
}
