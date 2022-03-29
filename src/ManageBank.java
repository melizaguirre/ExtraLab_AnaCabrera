
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAC
 */
public class ManageBank {
     private RandomAccessFile cuentas;

    public ManageBank() throws FileNotFoundException {
        File carpeta = new File("Banco");
        carpeta.mkdir();

        if (carpeta.exists()) {
            cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        }
    }

    public boolean buscar(int code) throws IOException {
        cuentas.seek(0);
        while (cuentas.getFilePointer() < cuentas.length()) {
            if (code == cuentas.readInt()) {
                return true;
            } else {
                cuentas.readUTF();
                cuentas.readLong();
                cuentas.readDouble();
                cuentas.readUTF();
            }
        }
        return false;
    }

    public void addCuenta(int cod, String nombre, String tipo) throws AccountAlreadyExist {
        try {
            if (!buscar(cod)) {
                cuentas.seek(cuentas.length());
                cuentas.writeInt(cod);
                cuentas.writeUTF(nombre);
                cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
                double saldo = (tipo.equals("Nornmal")) ? TipoCuenta.Normal.getMinSaldo():(tipo.equals("Plantilla")) 
                        ? TipoCuenta.Planilla.getMinSaldo(): TipoCuenta.VIP.getMinSaldo();
                cuentas.writeDouble(saldo);
                cuentas.writeUTF(tipo);
                System.out.println("Cuenta añadida");
            } else {
                throw new AccountAlreadyExist(cod);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void deposito(int cod, double monto) throws IOException {
        if (buscar(cod)) {
            double saldo = cuentas.readDouble();
            buscar(cod);
            cuentas.readUTF();
            cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
            cuentas.writeDouble(saldo + monto);
            System.out.println("Deposito realizado, saldo actual: " + (saldo + monto) + " Lps.");
        } else {
            System.out.println("No se ha encontrado ninguna cuenta");
        }
    }

    public boolean retiro(int cod, double monto) throws IOException {
        if (buscar(cod)) {

            double saldo = cuentas.readDouble();
            if (monto < saldo) {
                buscar(cod);
                cuentas.readUTF();
                cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
                cuentas.writeDouble(saldo - monto);
                System.out.println("Se ha realizado un retiro de: " + monto + " Lps.\nSaldo actual: " + (saldo - monto) + " Lps.");
                return true;
            } else {
                System.out.println("No hay fondos");
                return false;
            }
        } else {
            System.out.println("No se ha encontrado ninguna cuenta");
            return false;
        }
    }

   

    public void Import(String filename) throws IOException {
        File registro = new File(filename + ".txt");
        FileWriter fw = new FileWriter(registro, false);
        String datos = "";
        cuentas.seek(0);
        while (cuentas.getFilePointer() < cuentas.length()) {
            int code = cuentas.readInt();
            String nombre = cuentas.readUTF();
            cuentas.readLong();
            double saldo = cuentas.readDouble();
            String tipo = cuentas.readUTF();
            datos += " Codigo: " + code + " Nombre: " + nombre + " Saldo: " + saldo + " Tipo: " + tipo + "\n";
        }
        fw.write(datos);
        System.out.println("Datos: \n" + datos);
    }
}
