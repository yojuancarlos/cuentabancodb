package bancobasededatos;

import java.util.ArrayList;
import java.util.List;

import bancobasededatos.entidades.banco;
import bancobasededatos.repositorio.cuentaDb;
import bancobasededatos.repositorio.repositoriocrud;

public class main2 {
    public static void main(String[] args) {

        repositoriocrud repositoriocuenta = new cuentaDb();
        banco cuenta = new banco("111111111111",100000,"pedro", "ahorro");
        // propietario,saldo,tipo, numerocuenta
        // guardar
        repositoriocuenta.guardar(cuenta);
        // listar
        List<banco> bancobasedb = (ArrayList<banco>) repositoriocuenta.listar();
        

        

         for (banco cuentaenbasededatos : bancobasedb) {
        System.out.println("esta es el numero cuenta  "+cuentaenbasededatos.getNumeroCuenta());
         System.out.println("este es el saldo  "+cuentaenbasededatos.getSaldo());
         System.out.println("este es el propietario  "+cuentaenbasededatos.getPropietario());
         System.out.println("este es el tipo  "+cuentaenbasededatos.getTipo());
         }
    }

}

