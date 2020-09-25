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
import javax.swing.ListSelectionModel;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author A
 */
public class LaminaMarcoCliente extends JPanel implements Runnable{
	
    public LaminaMarcoCliente(){
            listch = new ChatsList();
            
            puerto = 12001; 
            
            JLabel texto=new JLabel("-Chat-");

            add(texto);
            
            listaContacts = new JList();
            
            ListReact lr = new ListReact();
            
            listaContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
            
            listaContacts.addListSelectionListener(lr);
            
            
            
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
                    
                    
                    
                    
                    if (!listch.isIn(rec.getMyport())){
                        //chat_space.append("Conversacion con"+rec.getMyport()+"\nRecibido: "+rec.getMsg()+"\n");
                        
                        listch.addBoth(rec.getMyport(), "Conversacion con: "+rec.getMyport()+"\n"+"Recibido: "+rec.getMsg()+"\n");
                        listaContacts.setListData(listch.getContacts().toArray());
                    }else{
                        chat_space.append("Recibido: "+rec.getMsg()+"\n");
                        listch.appendChat(rec.getMyport(), "Recibido: "+rec.getMsg()+"\n");
                    }

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
            //puertoEnvio = Integer.parseInt(port.getText());

            if (!listch.isIn(port.getText())){
                listch.addBoth(port.getText(), "Conversacion con: "+port.getText()+"\n");
                listaContacts.setListData(listch.getContacts().toArray());
            }
           
        }
        
    }
    
    private class ListReact implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (!listaContacts.isSelectionEmpty()){
            puertoEnvio = Integer.parseInt(((String)listaContacts.getSelectedValue()));
            chat_space.setText(listch.getChat(Integer.toString(puertoEnvio)));
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
                System.out.println(puertoEnvio);
                Paquete pk = new Paquete(Integer.toString(getPuerto()), Integer.toString(puertoEnvio), campo1.getText());
                Socket s = new Socket("127.0.0.1", puertoEnvio);

                ObjectOutputStream datos = new ObjectOutputStream(s.getOutputStream());

                datos.writeObject(pk);
                chat_space.append("Yo: "+campo1.getText()+"\n");
                listch.appendChat(port.getText(), "Yo: "+campo1.getText()+"\n");
                s.close();

            }catch(Exception e1){
                System.out.println(e1);   
            }

        }

    }
	public ChatsList listch;
        
	public int puerto; 
        
        private JTextArea chat_space;
        		
	private JTextField campo1, port;
	
	private JButton miboton;
        
        private JButton boton2;
        
        private JList listaContacts;
        
        private int puertoEnvio;
        //static int sender;
}
