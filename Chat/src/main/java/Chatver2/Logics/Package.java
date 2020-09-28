/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics;

import java.io.Serializable;

/**
 *
 * Package defines the kind of object that will be send by the sockets
 */
public class Package implements Serializable {

    private String myport, port, msg, ip;
    /**
     * Class constructor
     * @param myport the sender port
     * @param ip the receiver ip
     * @param port the receiver port
     * @param msg the message that will be sent
     */
    public Package(String myport, String ip,String port, String msg) {
        this.myport = myport;
        this.port = port;
        this.msg = msg;
        this.ip = ip;
    }
    /**
     * 
     * @return sender ip as string
     */
    public String getMyIp(){
        return this.ip;
    }
    /**
     * 
     * @return sender port as string
     */
    public String getMyport() {
        return this.myport;
    }
    /**
     * 
     * @return destiny port as string
     */
    public String getPort() {
        return this.port;
    }
    /**
     * 
     * @return message as a string
     */
    public String getMsg() {
        return this.msg;
    }

}
