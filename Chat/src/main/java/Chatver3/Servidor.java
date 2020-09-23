package Chatver3;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable{

    /**
     *
     * Clase del servidor
     *
     */

    private int puerto;

    private int Label_puerto;

    public Servidor(int puerto) {
        /**
         *
         * Constructor de Servidor
         *
         */

        this.puerto = puerto;
        Label_puerto = puerto;
    }

    public int getLabel_puerto() {
        return Label_puerto;
    }

    public void setLabel_puerto(int label_puerto) {
        Label_puerto = label_puerto;
    }

    @Override
    public void run() {

        /**
         *
         * Busca un puerto desosupado para escuchar en ese puerto.
         * Si no encuentra el puerto, pasa al siguiente.
         *
         */

        while (true){

            setLabel_puerto(puerto);

            System.out.println(puerto + "puerto servidor");

            ServerSocket servidor = null;

            Socket sc = null;
            DataInputStream in;
            DataOutput out;

            try{

                servidor = new ServerSocket(puerto);


                while (true){

                    sc = servidor.accept();

                    in = new DataInputStream(sc.getInputStream());

                    String mensaje = in.readUTF();

                    this.setChanged();
                    this.notifyObservers(mensaje);
                    this.clearChanged();

                    sc.close();


                }
            } catch (IOException ex){
                this.puerto+=1;


            }
        }

    }

}
