/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatver2.Logics.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anthony Chaves
 */
public class LoggerManager {
    public static Logger getBitacora(){
        return Logger.getGlobal();
    }
    public static Logger getBitacora(String pkg, String file, Level lvl){
       Logger bitacora = null;
       bitacora = Logger.getLogger(pkg);
       
       try{
           FileHandler handler = new FileHandler(file);
           bitacora.addHandler(handler);
       }
       catch(IOException e){
           bitacora = Logger.getGlobal();
           bitacora.severe("Error al crear la bitacora: "+ e.getMessage());
       }
       bitacora.setLevel(lvl);
       return bitacora;
    }
}
