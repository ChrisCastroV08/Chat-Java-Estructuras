/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics;

import java.util.ArrayList;

/**
 *
 * ChatsList provides an association between to lists, one with the contacts and the other with the chats associated to it.
 * 
 */
public class ChatsList {

    private java.util.List<String> listContacts;
    private java.util.List<String> chatsAsoc;
    //private java.util.List<String> ipsChats;
    /**
     * Class Constructor
     */
    public ChatsList() {
        listContacts = new ArrayList();
        chatsAsoc = new ArrayList();
    }
    /**
     * Appends new elements to list
     * @param port is an String that will be append to the contact list
     * @param chat is an String that will be append to the chat list
     */
    public void addBoth(String port, String chat) {
        listContacts.add(port);
        chatsAsoc.add(chat);
    }
    /**
     * Checks out if a contact is already created
     * @param port is an String of the contact
     * @return boolean value that will confirm if the contact is already created
     */
    public boolean isIn(String port) {
        if (listContacts.size() > 0) {
            return listContacts.contains(port);
        } else {
            return false;
        }
    }
    /**
     * Returns the chat associated to a contact
     * @param port is an String of the contact
     * @return the chat associated to a contact
     */
    public String getChat(String port) {
        int indexOf;
        if (listContacts.contains(port)) {
            indexOf = listContacts.indexOf(port);
            return chatsAsoc.get(indexOf);
        } else {
            return "";
        }

    }
    /**
     * Appends a string to the current chat
     * @param port the contact its chat will be appended
     * @param newText the string that will be added
     */
    public void appendChat(String port, String newText) {

        int indexOf = listContacts.indexOf(port);
        chatsAsoc.set(indexOf, chatsAsoc.get(indexOf)+newText);
        System.out.println(chatsAsoc.get(indexOf));
        //chatsAsoc.get(indexOf). chatsAsoc.get(indexOf).concat(newText);
        //chatsAsoc.get(indexOf). = tmp;

    }
    /**
     * Returns the current contacts
     * @return a list of strings
     */
    public java.util.List<String> getContacts() {
        return listContacts;
    }
}
