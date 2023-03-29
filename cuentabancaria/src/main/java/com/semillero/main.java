package main.java.com.semillero;

import com.semillero.entidades.banco;

import main.java.com.semillero.repositorio.cuentaDb;
import main.java.com.semillero.repositorio.repositoriocrud;

public class main {
    
    public static void main(String[] args) {

        repositoriocrud  repositoriocuenta = new cuentaDb();
        
        banco cuenta = new banco(1,10000,"pedro","ahorro");
        
//guardar
repositoriocuenta.guardar(cuenta);
//listar
ArrayList<cuenta>bancobasedb = (ArrayList<banco>)
         repositoriocuenta.listar();
    }
}
