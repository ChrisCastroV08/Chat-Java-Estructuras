
package Chatver2.Logics.Listeners;

import Chatver2.GUI.AppInterface;
import Chatver2.Logics.ChatsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import Chatver2.Logics.Logger.LoggerManager;
import java.util.logging.Level;
import Chatver2.AppMain;

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
    
    private Logger bitacora = AppMain.bitacora;

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
        Socket socketOut = null;

        try {
            
            System.out.println(puertoEnvio);
            Chatver2.Logics.Package pk = new Chatver2.Logics.Package(Integer.toString(myPort),myIp,Integer.toString(puertoEnvio), frame.campo1.getText());
            socketOut = new Socket(ip, puertoEnvio);

            ObjectOutputStream dataSend = new ObjectOutputStream(socketOut.getOutputStream());

            dataSend.writeObject(pk);
            frame.chat_space.append("Yo: " + frame.campo1.getText() + "\n");
            listch.appendChat(ip+":"+Integer.toString(puertoEnvio), "Yo: " + frame.campo1.getText() + "\n");
            
            bitacora.info("Mensaje enviado");
            
        } 
        catch (IllegalArgumentException e0){ // This occurs if the port number is out of the accepted range.
            bitacora.severe("El puerto esta fuera de rango"+e0.getMessage());
        }
        catch (ConnectException e1) { // This happens if the user we are trying to connect is offline.
            
            bitacora.severe("Se presento un error ya que el usuario deseado no está conectado"+e1.getMessage());
        }
        catch (UnknownHostException e2) { // This happens if there were incorrect info, such as the ip or the port.
            
            bitacora.severe("No se conoce el usuario destinatario"+e2.getMessage());
        }
        catch (IOException e3){ // This happens if there was an error while creating the connection and, by extension, the port.
            bitacora.severe("Ocurrio al crear el puerto"+e3.getMessage());
        }
        finally{
            if (socketOut != null){
                try{
                    socketOut.close();
                }
                catch (IOException e4){ // This exception detects if the socket didn't close,so it writes it in the .txt.
                    bitacora.severe("Ocurrio un error al cerrar el puerto"+e4.getMessage());
                }
            }
        }
    }

}
