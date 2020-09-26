/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.Listeners.AddContact;
import Chatver2.Logics.Listeners.ListReact;
import Chatver2.Logics.Listeners.SendText;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ListSelectionModel;

/**
 *
 * @author A
 */
public class ChatManager implements Runnable {

    public AppInterface frame;

    public ChatsList listch;

    public int puerto;

    public int puertoEnvio;

    public SendText evento;

    public ChatManager(AppInterface fr) {
        frame = fr;

        listch = new ChatsList();

        puerto = 12001;
        

        AddContact newone = new AddContact(listch, frame);

        frame.boton2.addActionListener(newone);
        

        evento = new SendText(listch, frame);

        frame.miboton.addActionListener(evento);
        

        ListReact lr = new ListReact(frame, listch, evento);

        frame.listaContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        frame.listaContacts.addListSelectionListener(lr);
        

        Thread th = new Thread(this);

        th.start();
    }

    public int getPuerto() {
        return puerto;
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (true) {
            try {
                ServerSocket servidor = new ServerSocket(puerto);
                System.out.println(puerto);
                evento.setPort(puerto);
                Socket socketIn;
                Package rec;
                while (true) {

                    socketIn = servidor.accept();
                    ObjectInputStream pk = new ObjectInputStream(socketIn.getInputStream());

                    rec = (Package) pk.readObject();

                    if (!listch.isIn(rec.getMyport())) {

                        listch.addBoth(rec.getMyport(), "Conversacion con: " + rec.getMyport() + "\n" + "Recibido: " + rec.getMsg() + "\n");
                        frame.listaContacts.setListData(listch.getContacts().toArray());
                    } else {
                        listch.appendChat(rec.getMyport(), "Recibido: " + rec.getMsg() + "\n");
                        if (rec.getMyport().equals((String)frame.listaContacts.getSelectedValue())){
                            frame.chat_space.append("Recibido: " + rec.getMsg() + "\n");
                        }
                        
                    }

                    socketIn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                puerto++;
            }
        }
    }

}
