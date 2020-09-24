/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author A
 */
public class LaminaMarcoCliente extends JPanel implements Runnable{
	
    public LaminaMarcoCliente(){
            contacts = new ArrayList();
            puerto = 12001; 
            
            JLabel texto=new JLabel("-Chat-");

            add(texto);
            
            listaContacts = new JList();
            
            add(listaContacts);
            
            boton2 = new JButton("Agregar");
            
            AgregarCont newone = new AgregarCont();
            
            boton2.addActionListener(newone);
            
            add(boton2);
            
            port = new JTextField(8);

            add(port);

            

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
                    
                    
                    contacts.add(rec.getMyport());
                    listaContacts.setListData(contacts.toArray());
                    
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
    public int getPuerto(){
        return puerto;
    }
    private class AgregarCont implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            puertoEnvio = Integer.parseInt(port.getText());
            contacts.add(port.getText());
            listaContacts.setListData(contacts.toArray());
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
                Paquete pk = new Paquete(Integer.toString(getPuerto()),port.getText(), campo1.getText());
                Socket s = new Socket("127.0.0.1", Integer.parseInt(port.getText()));

                ObjectOutputStream datos = new ObjectOutputStream(s.getOutputStream());

                datos.writeObject(pk);

                s.close();

            }catch(Exception e1){
                System.out.println(e1);   
            }

        }

    }
	public java.util.List<String> contacts;
	public int puerto; 
        private JTextArea chat_space;
        		
	private JTextField campo1, port;
	
	private JButton miboton;
        
        private JButton boton2;
        
        private JList listaContacts;
        
        private int puertoEnvio;
        //static int sender;
}
