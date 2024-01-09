package com.corenetworks.presentacion.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteRobot {
    public static void main(String[] args) {
        String[] preguntas = {"Horario de la tienda",
                "¿Que días no abren",
                "¿Tienes el libro Java desde cero",
                "Precio del libro"};
        try (Socket cliente = new Socket("localhost",3000)){
              String preguntaSeleccionada = mostrarMenu();
            PrintWriter mensajeAEnviar = new PrintWriter(cliente.getOutputStream());
            mensajeAEnviar.println(preguntaSeleccionada);
            System.out.println("Esperando respuesta del servidor");
            //Esperamos la contestación del Servidor
            BufferedReader mensajeRecibido = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            System.out.println(mensajeRecibido.readLine());
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private static String mostrarMenu() {
        //Mostrar las preguntas del array
        //Recibir la respuesta del usuario
    }
}
