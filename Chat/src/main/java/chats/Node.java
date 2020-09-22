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
    public Node(Socket valor, int prt, DataOutputStream output){
        this.value = valor;
        this.portc = prt;
        this.out = output;
    }
    public void setNext(Node sig){
        this.next = sig;
    }
    //public int getIndex(){
    //    return this.index;
    //}
    public int getPort(){
        return this.portc;
    }
    public Socket getSocket(){
        return this.value;
    }
    public DataOutputStream getDout(){
        return this.out;
    }
    
}
