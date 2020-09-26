/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.GUI;

/**
 *
 * @author A
 */
import Chatver2.Logics.ChatManager;
import javax.swing.*;


public class AppWindow extends JFrame {

    public AppWindow() {

        setBounds(600, 500, 300, 400);

        AppInterface interfaz = new AppInterface();

        ChatManager mgmt = new ChatManager(interfaz);

        add(interfaz);

        setVisible(true);
    }

}
