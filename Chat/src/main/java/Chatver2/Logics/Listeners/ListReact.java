/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics.Listeners;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.ChatsList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author A
 */
public class ListReact implements ListSelectionListener {
    private AppInterface frame;
    private ChatsList listch;
    private int puertoEnvio;
    private SendText evento;
    
    public ListReact(AppInterface fr, ChatsList l1, SendText ev){
        frame = fr;
        listch = l1;
        evento = ev;
    }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (!frame.listaContacts.isSelectionEmpty()) {
                puertoEnvio = Integer.parseInt(((String) frame.listaContacts.getSelectedValue()));
                evento.setPortOut(puertoEnvio);
                frame.chat_space.setText(listch.getChat(Integer.toString(puertoEnvio)));
            }
        }
    }
