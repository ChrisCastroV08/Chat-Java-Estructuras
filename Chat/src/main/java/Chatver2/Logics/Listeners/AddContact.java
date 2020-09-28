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
 * Add Contatct is an ActionListener based class which adds a contact to a list (when a JButton is pressed) if it has not been added before
 */
public class AddContact implements ActionListener {

    private AppInterface frame;
    private ChatsList listch;
    /**
     * Class constructor
     * @param l1 list where the contact will be added
     * @param fr AppInterface where the information to do the class task will be based on
     */
    public AddContact(ChatsList l1, AppInterface fr) {
        frame = fr;
        listch = l1;
    }
    /**
     * Action Listener that will be excecuted when the button is pressed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String key = frame.ip.getText()+":"+frame.port.getText();
        if (!listch.isIn(key)) {
            listch.addBoth(key, "Conversacion con: " + frame.port.getText() + "\n");
            frame.listaContacts.setListData(listch.getContacts().toArray());
        }

    }

}
