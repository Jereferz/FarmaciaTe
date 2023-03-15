import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class View {
    //Menu inicial que ve el usuario
    static Scanner read;
    static Scanner num1 ;

    public static int menu() {
        Scanner leer1 = new Scanner(System.in);
        //Excepcion para que no inserte simbolos ni letras
        try {
            int leer = 0;
            System.out.println("Farmacaco S.A");
            System.out.println("1. Agregar Proveedor");
            System.out.println("2. Agregar Medicamento");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Mostrar Proveedores");
            System.out.println("5. Cambiar Precio de Medicamento");
            System.out.println("6. Salir");
            leer = leer1.nextInt();
            return leer;
        }catch (InputMismatchException o) {
            System.out.println("No letras, no simbolos");
            System.out.println("Coloca un numero permitido");
            return 0 ;
        }

    }
    //menu numero 1 para ingresar proveedor
    public static Provider registerProvider() {
        Scanner leer2 = new Scanner(System.in);
        Provider p = new Provider();
        System.out.println("Ingrese el nombre del Proveedor");
        p.setName(leer2.next());
        System.out.println(p);
        return p;
    }
    public static Medicine addingMedicine() {
        int code;
        String name;
        int amount;
        double unit_price;
        Scanner read = new Scanner(System.in);
        System.out.println("Agregue nueva medicina");
        System.out.println("Codigo: ");
        code = read.nextInt();
        System.out.println("Nombre: ");
        name = read.next();
        System.out.println("Cantidad: ");
        amount= read.nextInt();
        System.out.println("Precio x unidad: ");
        unit_price = read.nextDouble();
        System.out.println("Cantidad: ");
        amount= read.nextInt();
        Medicine medicine = new Medicine(code,name,amount,unit_price,state);

        return medicine;
    }
    /*public static void listMedicine(Medicine PM) {
        System.out.println("Lista de las medicinas");
        System.out.println(PM);
    }*/
    public static void listMedicine(List<Medicine> al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println(i + "-" + al.get(i));
        }
    }

    public void listProvider(ResultSet prove) throws SQLException {
        int cont = 0;
        while(prove.next()) {
            ++cont;
            PrintStream a1 = System.out;
            int a = prove.getInt(1);
            a1.println("" + a + "Proveedor: " + prove.getString(2));
        }
    }

    public static void listProvider(Provider PM) {
        System.out.println("Lista de Proveedores");
        System.out.println(PM);
    }
}

