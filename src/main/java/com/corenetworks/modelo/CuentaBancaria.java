package com.corenetworks.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaBancaria {
    private String id;
    private String tipoOperacion;
    private double cantidad;
}
