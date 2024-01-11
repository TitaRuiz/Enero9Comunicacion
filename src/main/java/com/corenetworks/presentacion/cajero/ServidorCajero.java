package com.corenetworks.presentacion.cajero;

import com.corenetworks.pesistencia.AccesoCuentaBancaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;

public class ServidorCajero {
    private static double saldo;

    public static void main(String[] args) {
        BufferedReader mensajeRecibido = null;
        PrintWriter mensajeAEnviar = null;
        String[] datos = null;
        AccesoCuentaBancaria aCB = new AccesoCuentaBancaria();
        try (ServerSocket servidor = new ServerSocket(3000);) {
            while (true) {
                System.out.println("Esperando petición...");
                Socket s1 = servidor.accept();
                mensajeRecibido = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                //Dar formato a los datos
                datos = mensajeRecibido.readLine().split(",");
                double cantidad = Double.parseDouble(datos[2]);
                System.out.println(Arrays.toString(datos));
                saldo = aCB.solicitarSaldo(datos[1]);
                mensajeAEnviar = new PrintWriter(s1.getOutputStream(), true);
                switch (datos[0]) {
                    case "1":
                        mensajeAEnviar.println("Su saldo es -> " + saldo);
                        break;
                    case "2":
                        if (saldo >= cantidad) {
                            aCB.actualizaSaldo(datos[1], cantidad * -1);
                            saldo = aCB.solicitarSaldo(datos[1]);
                            mensajeAEnviar.println("Su saldo es -> " + saldo);

                        } else {
                            mensajeAEnviar.println("Saldo insuficiente  -> " + (saldo));
                        }
                        break;
                    case "3":
                        aCB.actualizaSaldo(datos[1], cantidad);
                        saldo = aCB.solicitarSaldo(datos[1]);
                        mensajeAEnviar.println("Su saldo es -> " + saldo);
                        break;
                }

            }


        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println(e.toString());
            ;
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
            ;
        }

    }
}
