
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAC
 */
public class Main {
     public static void main(String[] args) throws AccountAlreadyExist, IOException {
        ManageBank mb = new ManageBank();
        Scanner leer = new Scanner(System.in);
        
         int opcion = 0;
        do {
            System.out.println("\n Menu \n----------");
            System.out.println("1- Agregar Cuenta");
            System.out.println("2- Deposito");
            System.out.println("3- Retiro");
            System.out.println("4- Registrar intereses");
            System.out.println("5- Importar");
            System.out.println("6- Salir");
            
             try {
                opcion = leer.nextInt();
             
                switch (opcion) {
                    case 1:
                        
                         System.out.println("Ingrese un codigo: ");
                         int code = leer.nextInt();
                         System.out.println("Ingrese un nombre: ");
                         String name = leer.next();
                         System.out.println("Ingrese el tipo(Normal, Planilla y VIP): ");
                         String tipo = leer.next();
                         mb.addCuenta(code, name, tipo);
                        break;
                    case 2:
                         System.out.println("Ingrese el codigo de su cuenta: ");
                         int codigo = leer.nextInt();
                         System.out.println("Ingrese el monto de deposito: ");
                         double monto = leer.nextDouble();
                         mb.deposito(codigo, monto);
                        break;
                    case 3:   
                        System.out.println("Ingrese su codigo de cuenta: ");
                        int cod = leer.nextInt();
                        System.out.println("Ingrese el monto a retirar: ");
                        double mont = leer.nextDouble();
                        mb.retiro(cod, mont);
                        
                        break;
                    case 4:
                     
                        break;
                    case 5:
                        System.out.println("Ingrese el nombre del archivo que desea importar: ");
                        String fileName = leer.next();
                        mb.Import(fileName);
                        break;
                   
                    case 6:
                        System.exit(0);
                        break;
                }
            } catch (IOException e) {
                
            }
        }while (true);
     }
        
}
