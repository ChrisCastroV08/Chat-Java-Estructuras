/**
 * Primer trabajo extraclase de Estructuras de Datos I
 * Por: Anthony Chaves Achoy
 * Segundo Semestre 2020
 */
package Chatver2;

import Chatver2.GUI.AppWindow;
import Chatver2.Logics.Logger.LoggerManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 *
 * @author Anthony Chaves Achoy
 * @version 2.0
 * @since 26/09/2020
 */
public class AppMain {
    
    public static Logger bitacora = LoggerManager.getBitacora("Chatver2.Logics", "bitacora.txt", Level.ALL);
    
    public static void main(String[] args) {
        
        AppWindow window = new AppWindow();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
