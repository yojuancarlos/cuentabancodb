package main.java.com.semillero;

import java.util.ArrayList;

import com.semillero.entidades.banco;
import com.semillero.entidades.cuentaAhorros;

import main.java.com.semillero.repositorio.cuentaDb;
import main.java.com.semillero.repositorio.repositoriocrud;

public class main {

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
