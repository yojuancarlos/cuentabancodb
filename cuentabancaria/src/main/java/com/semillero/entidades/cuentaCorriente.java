package com.semillero.entidades;

public class cuentaCorriente extends banco{
    int numRetiros;
    public cuentaCorriente(int numeroCuenta, int saldo, String propietario) {
        super(numeroCuenta, saldo, propietario);
        numRetiros=0;
        

    }

    @Override
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
