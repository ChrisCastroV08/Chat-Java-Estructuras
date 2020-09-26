/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics.Listeners;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.ChatsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author A
 */
public class SendText implements ActionListener {

    private AppInterface frame;
    private ChatsList listch;
    private int puertoEnvio;
    private int myPort;

    public SendText(ChatsList l1, AppInterface fr) {
        frame = fr;
        listch = l1;
    }

    public void setPort(int port) {
        myPort = port;
    }

    public void setPortOut(int portOut) {
        puertoEnvio = portOut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            System.out.println(puertoEnvio);
            Chatver2.Logics.Package pk = new Chatver2.Logics.Package(Integer.toString(myPort), Integer.toString(puertoEnvio), frame.campo1.getText());
            Socket socketOut = new Socket("127.0.0.1", puertoEnvio);

            ObjectOutputStream dataSend = new ObjectOutputStream(socketOut.getOutputStream());

            dataSend.writeObject(pk);
            frame.chat_space.append("Yo: " + frame.campo1.getText() + "\n");
            listch.appendChat(Integer.toString(puertoEnvio), "Yo: " + frame.campo1.getText() + "\n");
            socketOut.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }

}
