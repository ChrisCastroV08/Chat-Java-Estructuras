package Chatver3;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class cliente implements Runnable{

    /**
     * Clase donde se mandará el mensaje al puerto indicado
     */

    private int puerto;
    private String mensaje;

    public cliente(int puerto, String mensaje){

        /**
         *
         * Contructor de la clase cliente
         *
         * @param : numero del puerto y mensaje deseado
         *
         */
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {

        /**
         *
         * Se escribe el mensaje
         *
         */
        final String HOST = "127.0.0.1";

        DataOutput out;

        try {

            Socket sc = new Socket(HOST,puerto);

            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF(mensaje);

            sc.close();

        }catch (IOException ex){

        }
    }
}
