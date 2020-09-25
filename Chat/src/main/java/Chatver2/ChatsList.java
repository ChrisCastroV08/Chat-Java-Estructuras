/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2;

import java.util.ArrayList;

/**
 *
 * @author A
 */
public class ChatsList {
    private java.util.List <String> listContacts;
    private java.util.List <String> chatsAsoc;
    
    public ChatsList(){
        listContacts = new ArrayList();
        chatsAsoc = new ArrayList();
    }
    
    
    public void addBoth(String port, String chat){
        listContacts.add(port);
        chatsAsoc.add(chat);
    }
    public boolean isIn(String port){
        if (listContacts.size() > 0){
            return listContacts.contains(port);
        }
        else{
            return false;
        }
    }
    public String getChat(String port){
        int indexOf; 
        if (listContacts.contains(port)){
            indexOf = listContacts.indexOf(port);
            return chatsAsoc.get(indexOf);
        }
        else{
            return "";
        }
        
    }
    public void appendChat(String port,String newText){
        
        int indexOf = listContacts.indexOf(port);
        chatsAsoc.set(indexOf, newText);
        //chatsAsoc.get(indexOf). chatsAsoc.get(indexOf).concat(newText);
        //chatsAsoc.get(indexOf). = tmp;
        
    }
    public java.util.List <String> getContacts(){
        return listContacts;
    }
}
