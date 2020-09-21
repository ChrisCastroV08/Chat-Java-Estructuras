/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chats;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author A
 */
public class Node {
    public Node next = null;
    public Socket value;
    public int index;
    public int portc;
    public DataOutputStream out;
    public void setValue(Socket valor, int ind, int prt, DataOutputStream output){
        value = valor;
        index = ind;
        portc = prt;
        out = output;
    }
    public void setNext(Node sig){
        next = sig;
    }
    public int getIndex(){
        return index;
    }
    public int getPort(){
        return portc;
    }
    public Socket getSocket(){
        return value;
    }
    public DataOutputStream getDout(){
        return out;
    }
    
}
