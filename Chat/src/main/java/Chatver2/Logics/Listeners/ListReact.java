package Chatver2.Logics.Listeners;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.ChatsList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * ListReact is a ListListener based class which adds contacts to a list
 * 
 */
public class ListReact implements ListSelectionListener {
    private AppInterface frame;
    private ChatsList listch;
    private int puertoEnvio;
    private String ip;
    private SendText evento;
    
    /**
     * Class Constructor
     * @param fr AppInterface where the changes will be seen
     * @param l1 ChatList where the contacts will be written
     * @param ev SendText object where the port and ip will be written
     */
    public ListReact(AppInterface fr, ChatsList l1, SendText ev){
        frame = fr;
        listch = l1;
        evento = ev;
    }
        
        @Override
        /**
         * Listener that will be action when the value of the list changes
         */
        public void valueChanged(ListSelectionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (!frame.listaContacts.isSelectionEmpty()) {
                String[] paramsOut = ((String) frame.listaContacts.getSelectedValue()).split(":");
                puertoEnvio = Integer.parseInt(paramsOut[1]);
                ip = paramsOut[0];
                evento.setPortOut(ip,puertoEnvio);
                frame.chat_space.setText(listch.getChat(ip+":"+Integer.toString(puertoEnvio)));
            }
        }
    }
