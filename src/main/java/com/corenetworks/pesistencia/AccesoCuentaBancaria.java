package com.corenetworks.pesistencia;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoCuentaBancaria extends Conexion{
    public double solicitarSaldo(String numeroTarjeta) throws SQLException, ClassNotFoundException {
        //1. Declarar variables
        PreparedStatement sentencia = null;
        String sql = "select saldo from cuenta_bancaria where no_tarjeta = ?";
        double resultado = 0;
        ResultSet rejilla = null;
        //2. Abrir conexion
        abrirConexion();
        //3. Crear el statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,numeroTarjeta);
        //4. Ejecutarlo
        rejilla = sentencia.executeQuery();
        //5. Obtener el resultado
        if(rejilla.next()){
           resultado = Double.parseDouble(rejilla.getString("saldo").replace("€","").replace(".","").replace(",","."));
        }
        //6. Devolver
        return resultado;
    }

    public String obtenerPin(String numeroTarjeta) throws SQLException, ClassNotFoundException {
        //1. Declarar variables
        PreparedStatement sentencia = null;
        String sql = "select pin from cuenta_bancaria where no_tarjeta = ?";
        String resultado = null;
        ResultSet rejilla = null;
        //2. Abrir conexion
        abrirConexion();
        //3. Crear el statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,numeroTarjeta);
        //4. Ejecutarlo
        rejilla = sentencia.executeQuery();
        //5. Obtener el resultado
        if(rejilla.next()){
            resultado = (rejilla.getString("pin"));
        }
        //6. Devolver
        return resultado;
    }

    public void actualizaSaldo(String numeroTarjeta,double cantidad) throws SQLException, ClassNotFoundException {
        //1. Declarar variables
        PreparedStatement sentencia = null;
        String sql = "update cuenta_bancaria set saldo = saldo + cast(? as money) where no_tarjeta = ?";
        //2. Abrir conexion
        abrirConexion();
        //3. Crear el statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setBigDecimal(1,new BigDecimal(cantidad));
        sentencia.setString(2, numeroTarjeta);
        //4. Ejecutarlo
        sentencia.executeUpdate();
    }
}
