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
public class Lista {
    public Node head = null;
    public int largo = 0;

    public void appe(Socket valor, int porto, DataOutputStream dout){
        largo ++;
        if (head == null){
            head = new Node();
            head.setValue(valor, largo-1, porto, dout);
        }
        else{
            Node tmp = head;
            while (tmp != null){
                if (tmp.next == null){
                    break;
                }
                tmp = tmp.next;
            }
            tmp.next = new Node();
            tmp.next.setValue(valor, largo-1, porto, dout);
        }
    }
    
    public void printL(){
        Node tmp = head;
        System.out.print("[");
        while (tmp != null){
            System.out.print(tmp.value);
            if (tmp.next != null){
                System.out.print(", ");
            }
            tmp = tmp.next;
        }
        System.out.println("]");
    }
    public int getLargo(){
        return largo;
    }
    public boolean isIn(int port){
        Node tmp = head;
        boolean found = false;
        while (tmp != null){
            if (tmp.getPort() == port){
                found = true;
                break;
            }
        }
        return found;
    }
    public Socket searchSocket(int port){
        Node tmp = head;
        Socket socketget = null;
        while (tmp != null){
            if (tmp.getPort() == port){
                socketget = tmp.getSocket();
                break;
            }
        }
        return socketget;
    }
    public DataOutputStream searchdout(int port){
        Node tmp = head;
        DataOutputStream dout = null;
        while (tmp != null){
            if (tmp.getPort() == port){
                dout = tmp.getDout();
                break;
            }
        }
        return dout;
    }
    public int srchByIndex(int index){
        Node tmp = head;
        int port = 0;
        while (tmp != null){
            if (tmp.getIndex() == index){
                port = tmp.getPort();
                break;
            }
        }
        return port;
    }
}
    
