package bancobasededatos.servicios;

import java.util.List;

import bancobasededatos.entidades.banco;
import bancobasededatos.repositorio.cuentaDb;
import bancobasededatos.repositorio.repositoriocrud;

public class serviciosbanco {
    private repositoriocrud repositoriocuenta;

    public serviciosbanco() {
        repositoriocuenta = new cuentaDb();
    }

    public void guardarcuenta(banco newcuenta) {
        repositoriocuenta.guardar(newcuenta);
    }

    public List<banco> listarcuentas() {
        return (List<banco>) repositoriocuenta.listar();
    }

    public banco buscarcuenta(String numerodecuenta) throws Exception {
        Object cuenta = repositoriocuenta.buscar(numerodecuenta);
        if(cuenta == null) {
            throw new Exception("No se encontro la cuenta");
        }
        return (banco) cuenta;
    }

    public void eliminarPersona(String numerodecuenta) {
        repositoriocuenta.eliminar(numerodecuenta);
    }

}
