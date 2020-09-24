/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2;

/**
 *
 * @author A
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,500,300,400);
				
		LaminaMarcoCliente milamina = new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}
