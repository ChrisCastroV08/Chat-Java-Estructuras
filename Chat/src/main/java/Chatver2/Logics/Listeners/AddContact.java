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

/**
 *
 * @author A
 */
public class AddContact implements ActionListener {

    private AppInterface frame;
    private ChatsList listch;

    public AddContact(ChatsList l1, AppInterface fr) {
        frame = fr;
        listch = l1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //puertoEnvio = Integer.parseInt(port.getText());

        if (!listch.isIn(frame.port.getText())) {
            listch.addBoth(frame.port.getText(), "Conversacion con: " + frame.port.getText() + "\n");
            frame.listaContacts.setListData(listch.getContacts().toArray());
        }

    }

}
