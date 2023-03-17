import java.util.Scanner;

public class Validation {
    static Scanner read = new Scanner(System.in);
    //Validacion para cambiar tamaño de codigo por si en algun momento lo quiere cambiar el cliente
    private static String validarCode(String cadena) {
        if (cadena.length() == 4 ) {
            return cadena;
        }else {
            do {
                System.out.println("Ingrese un Codigo valido intente de nuevo");
                cadena = read.nextLine();
            } while (cadena.length() != 4);
            return cadena;
        }
    }
}
