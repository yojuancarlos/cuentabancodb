package bancobasededatos;

import java.util.ArrayList;
import java.util.List;

import bancobasededatos.entidades.banco;
import bancobasededatos.gui.guibanco;
import bancobasededatos.repositorio.cuentaDb;
import bancobasededatos.repositorio.repositoriocrud;

public class main2 {
    public static void main(String[] args) throws Exception {

        repositoriocrud repositoriocuenta = new cuentaDb();
        //banco cuenta = new banco("222222222222",100000,"pedro", "ahorro");
        // propietario,saldo,tipo, numerocuenta
        // guardar
        //repositoriocuenta.guardar(cuenta);
        // listar
        //List<banco> bancobasedb = (ArrayList<banco>) repositoriocuenta.listar();
        // ------ Buscar Persona ------
         //banco bancoencontrado = (banco) repositoriocuenta.buscar("310313111121");

         //System.out.println("La persona encontrada es: " + bancoencontrado.getNumeroCuenta());

        

         //for (banco cuentaenbasededatos : bancobasedb) {
        //System.out.println("esta es el numero cuenta  "+cuentaenbasededatos.getNumeroCuenta());
         //System.out.println("este es el saldo  "+cuentaenbasededatos.getSaldo());
         //System.out.println("este es el propietario  "+cuentaenbasededatos.getPropietario());
        // System.out.println("este es el tipo  "+cuentaenbasededatos.getTipo());


        guibanco gui = new guibanco();
        gui.iniciar();
         }
    }



