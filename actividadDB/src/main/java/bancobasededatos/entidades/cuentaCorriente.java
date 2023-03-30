package bancobasededatos.entidades;

import bancobasededatos.exepciones.MaximoRetirosException;
import bancobasededatos.exepciones.SaldoInsuficienteException;

public class cuentaCorriente extends banco{
    protected int numRetiros;
	
    protected int numTransferenciasAhorro;
    protected int numTransferenciasCorriente;
    public cuentaCorriente(String numeroCuenta, int saldo, String propietario,String tipo) {
        super(numeroCuenta, saldo, propietario, tipo);
        
		this.numRetiros = 0;
        this.numTransferenciasAhorro = 0;
        this.numTransferenciasCorriente = 0;
        

    }
	public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a retirar debe ser mayor a 0");
        } else if (monto > saldo) {
            System.out.println("El monto a retirar es mayor que el saldo disponible");
        } else if (numRetiros >= 5) {
            System.out.println("Ha excedido el número máximo de retiros en esta cuenta");
        } else {
            saldo -= monto;
            numRetiros++;
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        }
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a depositar debe ser mayor a 0");
        } else {
            saldo += monto;
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
        }
    }

    public void transferir(banco cuentaDestino, double monto) {
        if (monto <= 0) {
            System.out.println("El monto a transferir debe ser mayor a 0");
        } else if (monto > saldo) {
            System.out.println("El monto a transferir es mayor que el saldo disponible");
        } else if (cuentaDestino == null) {
            System.out.println("La cuenta de destino no existe");
        } else if (cuentaDestino == this) {
            System.out.println("No se puede transferir a sí mismo");
        } else if (!(cuentaDestino instanceof cuentaCorriente) && numTransferenciasAhorro >= 2) {
            System.out.println("Ha excedido el número máximo de transferencias a cuentas de ahorro");
        } else if (((cuentaDestino instanceof cuentaCorriente) || (cuentaDestino instanceof cuentaAhorros)) 
                && (numTransferenciasCorriente + numTransferenciasAhorro) >= 10) {
            System.out.println("Ha excedido el número máximo de transferencias a cuentas corrientes");
        } else {
            double montoTotal = monto;
            if (cuentaDestino instanceof cuentaAhorros) {
                montoTotal += monto * 0.015;
                numTransferenciasAhorro++;
            } else {
                montoTotal += monto * 0.02;
                numTransferenciasCorriente++;
            }
            saldo -= montoTotal;
            cuentaDestino.depositar(monto);
            System.out.println("Transferencia exitosa. Nuevo saldo: " + saldo);
        }
    }

	
    
}
