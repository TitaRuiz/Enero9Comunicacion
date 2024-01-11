package com.corenetworks.presentacion.cajero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServidorCajero {
    private static  int saldo=3000;
    public static void main(String[] args) {
        BufferedReader mensajeRecibido = null;
        PrintWriter mensajeAEnviar = null;
        String[] datos = null;
        try(ServerSocket servidor = new ServerSocket(3000);){
            while(true){
                System.out.println("Esperando petición...");
                Socket s1 = servidor.accept();
                mensajeRecibido = new BufferedReader(new InputStreamReader(s1.getInputStream()));

                //Dar formato a los datos
                datos = mensajeRecibido.readLine().split(",");
                double cantidad = Double.parseDouble(datos[2]);
                System.out.println(Arrays.toString(datos));
                mensajeAEnviar = new PrintWriter(s1.getOutputStream(),true);
                switch (datos[0]){
                    case "1": mensajeAEnviar.println("Su saldo es -> "+saldo);
                              break;
                    case "2": if(saldo >= cantidad) {
                        mensajeAEnviar.println("Su saldo es -> "+(saldo-cantidad));
                        saldo-=cantidad;
                    }  else{
                        mensajeAEnviar.println("Saldo insuficiente  -> "+(saldo));
                    }
                    break;
                    case "3":  mensajeAEnviar.println("Su saldo es -> "+(saldo+cantidad));
                               saldo  += cantidad;
                               break;
                }

            }



        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
