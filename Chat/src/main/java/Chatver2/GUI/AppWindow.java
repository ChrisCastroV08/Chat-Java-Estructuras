package Chatver2.GUI;

import Chatver2.Logics.ChatManager;
import javax.swing.*;

/**
 * 
 * AppWindow defines the size of the display window and calls the GUI managment class
 * 
 */

public class AppWindow extends JFrame {

    public AppWindow() {

        setBounds(600, 500, 300, 400);

        AppInterface interfaz = new AppInterface();

        ChatManager mgmt = new ChatManager(interfaz);

        add(interfaz);

        setVisible(true);
    }

}
