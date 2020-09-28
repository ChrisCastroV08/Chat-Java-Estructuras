
package Chatver2.Logics.Listeners;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.ChatsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * SendText is an ActionListener based class that will sent a message using socket communication
 * 
 */
public class SendText implements ActionListener {

    private AppInterface frame;
    private ChatsList listch;
    private int puertoEnvio;
    private int myPort;
    private String ip, myIp;
    /**
     * Class Cosntructor
     * @param l1 List that will be checked
     * @param fr Appinterface where changes will be made
     */
    public SendText(ChatsList l1, AppInterface fr) {
        frame = fr;
        listch = l1;
    }
    /**
     * Sets the port value for the sender
     * @param port int value that represents the port
     */
    public void setPort(int port) {
        myIp = "127.0.0.1";
        myPort = port;
    }
    /**
     * Sets the port where the data will be sent
     * @param ipOut String that represents the destiny ip
     * @param portOut  int that represents the destiny port
     */
    public void setPortOut(String ipOut,int portOut) {
        puertoEnvio = portOut;
        ip = ipOut;
    }

    @Override
    /**
     * Action listeners that gets triggered when the send button is pressed
     * Sends a message by using sockets
     */
    public void actionPerformed(ActionEvent e) {

        try {
            System.out.println(puertoEnvio);
            Chatver2.Logics.Package pk = new Chatver2.Logics.Package(Integer.toString(myPort),myIp,Integer.toString(puertoEnvio), frame.campo1.getText());
            Socket socketOut = new Socket(ip, puertoEnvio);

            ObjectOutputStream dataSend = new ObjectOutputStream(socketOut.getOutputStream());

            dataSend.writeObject(pk);
            frame.chat_space.append("Yo: " + frame.campo1.getText() + "\n");
            listch.appendChat(ip+":"+Integer.toString(puertoEnvio), "Yo: " + frame.campo1.getText() + "\n");
            socketOut.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }

}
