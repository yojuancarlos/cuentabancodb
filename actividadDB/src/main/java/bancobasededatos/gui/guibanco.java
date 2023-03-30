package bancobasededatos.gui;

import java.util.List;
import java.util.Scanner;

import bancobasededatos.entidades.banco;
import bancobasededatos.entidades.cuentaAhorros;
import bancobasededatos.entidades.cuentaCorriente;
import bancobasededatos.repositorio.cuentaDb;
import bancobasededatos.servicios.serviciosbanco;

public class guibanco {
    private boolean running = true;
    private serviciosbanco serviciosbanco;

    public guibanco() {
        serviciosbanco = new serviciosbanco();
    }

    public void iniciar() throws Exception {
        System.out.println("Bienvenido al sistema de bancos");

        while (running) {
            System.out.println("1. Crear cuenta");
            System.out.println("2. Listar cuenta");
            System.out.println("3. Buscar cuenta");
            System.out.println("4. Eliminar cuenta");
            System.out.println("5. Realizar depósito");
            System.out.println("6. Realizar retiro");
            System.out.println("7. Realizar transferencia");
            System.out.println("8. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccion(opcion);
        }

    }

    private void seleccion(int seleccion) throws Exception {
        switch (seleccion) {
            case 1:
                crearPersona();
                break;
            case 2:
                listarPersonas();
                break;
            case 3:
                buscarPersona();
                break;
            case 4:
                eliminarPersona();
                break;
            case 5:
                realizarDeposito();
            break;
            case 6:
                realizarRetiro();;
            break;
            case 7:
                realizarTransferencia();;
            break;
            case 8:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void crearPersona() {
        System.out.println("Crear banco");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numerocuenta: ");
        String numerocuenta = scanner.nextLine();   
        System.out.println("saldo: ");
        int saldo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("propietario: ");
        String propietario = scanner.nextLine();
        System.out.println("tipo de cuenta: ");
        String tipo = scanner.nextLine();

        banco persona = new banco(numerocuenta,saldo,propietario,tipo);
        serviciosbanco.guardarcuenta(persona);

    }

    private void listarPersonas() {
        System.out.println("Listando cuentas");
        List<banco> cuentasenbasededatos = serviciosbanco.listarcuentas();

        for (banco cuentaenbasededatos : cuentasenbasededatos) {
            System.out.println(cuentaenbasededatos.getNumeroCuenta());
            System.out.println(cuentaenbasededatos.getSaldo());
        }
    }

    private void buscarPersona() {
        System.out.println("Buscar cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            banco cuentaencontrada = serviciosbanco.buscarcuenta(numerodecuenta);
            System.out.println("cuenta encontrada: " + cuentaencontrada.getNumeroCuenta());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarPersona() {
        System.out.println("Eliminarcuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            serviciosbanco.eliminarPersona(numerodecuenta);
            System.out.println("cuenta eliminada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public  void realizarDeposito() throws Exception {
        System.out.println("\n------ Realizar Depósito ------");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
       
        String numerodecuenta = scanner.nextLine();
        banco cuenta = serviciosbanco.buscarcuenta(numerodecuenta);
        if (cuenta == null) {
            System.out.println("La cuenta no existe");
            return;
        }
        System.out.print("Ingrese el monto a depositar: ");
        int monto = scanner.nextInt();
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero");
            return;
        
        }
        serviciosbanco.actualizarCuenta(numerodecuenta,cuenta);
        cuenta.depositar(monto);

        System.out.println("\nDepósito realizado exitosamente");
        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
    }


    public  void realizarRetiro() throws Exception {
        System.out.println("--realizar deposito------: ");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
        String numerodecuenta = scanner.nextLine();
        banco cuenta = serviciosbanco.buscarcuenta(numerodecuenta);

        if (cuenta == null) {
            System.out.println("La cuenta no existe.");
            return;
        }
        System.out.println("Ingrese la cantidad a retirar: ");
        int monto = scanner.nextInt();;
        cuenta.retirar(monto);
        System.out.println("Se ha retirado $" + monto + " de la cuenta " + numerodecuenta);
        System.out.println("El saldo actual de la cuenta es $" + cuenta.getSaldo());
        
        serviciosbanco.actualizarCuenta(numerodecuenta,cuenta);
    }


    public  void realizarTransferencia() throws Exception {
        Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese el número de cuenta de origen: ");
    String numeroCuentaOrigen = sc.nextLine();
    System.out.println("Ingrese el número de cuenta de destino: ");
    String numeroCuentaDestino = sc.nextLine();

    banco cuentaOrigen = serviciosbanco.buscarcuenta(numeroCuentaOrigen);
    banco cuentaDestino = serviciosbanco.buscarcuenta(numeroCuentaDestino);

    if (cuentaOrigen == null) {
        System.out.println("No existe una cuenta con ese número de cuenta.");
        return;
    }

    if (cuentaDestino == null) {
        System.out.println("No existe una cuenta con ese número de cuenta.");
        return;
    }

    if (cuentaOrigen.equals(cuentaDestino)) {
        System.out.println("No se puede transferir a la misma cuenta.");
        return;
    }

    System.out.println("Ingrese el monto a transferir: ");
    double monto = sc.nextDouble();

    if (monto <= 0) {
        System.out.println("El monto debe ser mayor que cero.");
        return;
    }

    if (cuentaOrigen.getSaldo() < monto) {
        System.out.println("El monto a transferir excede el saldo de la cuenta de origen.");
        return;
    }

    if (cuentaOrigen instanceof cuentaAhorros && cuentaDestino instanceof cuentaCorriente) {
        ((cuentaCorriente) cuentaOrigen).transferir(cuentaDestino, monto);
    } else if (cuentaOrigen instanceof cuentaAhorros && cuentaDestino instanceof  cuentaAhorros) {
        ((cuentaAhorros) cuentaOrigen).transferir(cuentaDestino, monto);
    } else {
        System.out.println("No se puede transferir entre cuentas de diferente tipo.");
        return;
    }

    System.out.println("Transferencia realizada con éxito.");
    }

    private void salir() {
        System.out.println("Salir");
        running = false;
    }



   
}
