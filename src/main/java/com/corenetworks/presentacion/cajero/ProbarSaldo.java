package com.corenetworks.presentacion.cajero;

import com.corenetworks.pesistencia.AccesoCuentaBancaria;

import java.sql.SQLException;

public class ProbarSaldo {
    public static void main(String[] args) {
        AccesoCuentaBancaria aCB1 = new AccesoCuentaBancaria();
        try {
            aCB1.actualizaSaldo("12345",-100);
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

    }
}
