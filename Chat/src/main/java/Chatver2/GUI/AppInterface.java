/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author A
 */
public class AppInterface extends JPanel {

    public AppInterface() {

        JLabel texto = new JLabel("-Chat-");

        add(texto);

        listaContacts = new JList();

        add(listaContacts);

        boton2 = new JButton("Agregar");

        add(boton2);

        port = new JTextField(8);

        add(port);

        chat_space = new JTextArea(12, 20);

        add(chat_space);

        campo1 = new JTextField(20);

        add(campo1);

        miboton = new JButton("Enviar");

        add(miboton);

        //Thread th = new Thread(this);
        //th.start();
    }

    public JTextArea chat_space;

    public JTextField campo1, port;

    public JButton miboton;

    public JButton boton2;

    public JList listaContacts;

    //static int sender;
}
