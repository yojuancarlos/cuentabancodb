package main.java.com.semillero.repositorio;

public class main2 {
    
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

