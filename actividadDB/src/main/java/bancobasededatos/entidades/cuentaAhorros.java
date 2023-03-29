package bancobasededatos.entidades;

import bancobasededatos.exepciones.SaldoInsuficienteException;

public class cuentaAhorros extends banco{
    
    protected int numRetiros;
	protected double sobrecargo;
	protected double bono;


    public cuentaAhorros(String numeroCuenta, int saldo, String propietario, String tipo) {
        super(numeroCuenta, saldo, propietario, tipo);
        //TODO Auto-generated constructor stub
        numRetiros = 1;
    }

	public void retirar(float valor) throws SaldoInsuficienteException {
		if (valor > saldo) {
            throw new SaldoInsuficienteException(propietario);
        }
		
		saldo -= valor;
    	numRetiros++;

        if (numRetiros > 3) {
            sobrecargo = valor * 0.01;
            saldo -= sobrecargo;
        }}
	
	 @Override
	    public void depositar(float valor) {
	        if (numRetiros < 2) {
	            bono = valor * 0.005;
	            saldo += bono;
	        }

	        saldo += valor;
	    }

}
