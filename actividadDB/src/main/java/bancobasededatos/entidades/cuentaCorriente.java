package bancobasededatos.entidades;

import bancobasededatos.exepciones.MaximoRetirosException;
import bancobasededatos.exepciones.SaldoInsuficienteException;

public class cuentaCorriente extends banco{
    int numRetiros;
    public cuentaCorriente(String numeroCuenta, int saldo, String propietario,String tipo) {
        super(numeroCuenta, saldo, propietario, tipo);
        numRetiros=0;
        

    }

	public void retirar(float valor) throws SaldoInsuficienteException, MaximoRetirosException {
		if (valor > saldo) {
	        throw new SaldoInsuficienteException(propietario);
	    }

	    
        if (numRetiros >= 5) {
	        throw new MaximoRetirosException(propietario);
	    }

	    saldo -= valor;
	    numRetiros++;
		
	}
    
}
