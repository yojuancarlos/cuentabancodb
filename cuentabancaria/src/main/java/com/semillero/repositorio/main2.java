package main.java.com.semillero.repositorio;

import java.util.ArrayList;
import java.util.List;

import com.semillero.entidades.banco;
import com.semillero.entidades.cuentaAhorros;

public class main2 {

    public static void main(String[] args) {

        repositoriocrud repositoriocuenta = new cuentaDb();
        banco cuenta = new banco("pedro", 1000, "ahorro", "123");
        // propietario,saldo,tipo, numerocuenta
        // guardar
        repositoriocuenta.guardar(cuenta);
        // listar
        List<banco> bancobasedb = (ArrayList<banco>) repositoriocuenta.listar();
    }
}