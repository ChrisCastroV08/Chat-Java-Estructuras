/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Chatver2;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;

/**
 *
 * @author A
 */
public class Cliente {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

    
    class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina = new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}


class LaminaMarcoCliente extends JPanel implements Runnable{
	
	public LaminaMarcoCliente(){
                
                puerto = 12001; 
                port = new JTextField(8);
                
                add(port);
	
		JLabel texto=new JLabel("-Chat-");
		
		add(texto);
                
                chat_space = new JTextArea(12,20);
                
                add(chat_space);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		
                
                EnviarTexto evento = new EnviarTexto();
                
                miboton.addActionListener(evento);
                        
                add(miboton);	
                
                Thread th = new Thread(this);
                
                th.start();
		
	}

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (true){
            try{
                ServerSocket servidor = new ServerSocket(puerto);
                System.out.println(puerto);
                Socket s;
                Paquete rec;
                while (true){

                    s = servidor.accept();
                    ObjectInputStream pk = new ObjectInputStream(s.getInputStream());

                    rec = (Paquete)pk.readObject();

                    chat_space.append("Mensaje: "+rec.getMsg()+"From: "+rec.getMyport());

                    //Socket sendto = new Socket("127.0.0.1",Integer.parseInt(rec.getPort()));
                    //ObjectOutputStream tosend = new ObjectOutputStream(sendto.getOutputStream());

                    //tosend.writeObject(pk);

                    //sendto.close();

                    s.close();
                }
            }catch(Exception e){
                System.out.println(e);
                puerto++;
            }
        }
    }
    private class EnviarTexto implements ActionListener{
    
    
    //Socket misocket = new Socket("127.0.0.1", 12001);
    //Paquete datos = new Paquete("12001", port.getText(),campo1.getText());
        

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
        try{
            Paquete pk = new Paquete(Integer.toString(puerto),port.getText(), campo1.getText());
            Socket s = new Socket("127.0.0.1", Integer.parseInt(port.getText()));
            
            ObjectOutputStream datos = new ObjectOutputStream(s.getOutputStream());
            
            datos.writeObject(pk);
            
            s.close();
            
        }catch(Exception e1){
            System.out.println(e1);   
        }
        
    }
    }
	
	private int puerto; 
        private JTextArea chat_space;
        		
	private JTextField campo1, port;
	
	private JButton miboton;
}

class Paquete implements Serializable{
    private String myport, port, msg;
    
    public Paquete(String myport, String port, String msg){
        this.myport = myport;
        this.port = port;
        this.msg = msg;
    }
    public String getMyport(){
        return this.port;
    }
    public String getPort(){
        return this.port;
    }
    public String getMsg(){
        return this.msg;
    }

}
