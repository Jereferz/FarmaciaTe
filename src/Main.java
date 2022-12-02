import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;


public class Main {

        public static void main(String[] args) {
            DataModelo model = new DataModelo();
            boolean bandera = true;
            do {
                try {
                    int opc = View.menu();
                    try {
                        if (0 != opc) {
                            switch (opc) {
                                case 1:
                                    Connection conn = Conexion.getInstancia().conectar();
                                    Medicine med = View.agregarCliente();
                                    int filas = model.recupararCode(conn);
                                    model.saveClient(med, filas);
                                    break;
                                case 2:
                                    var list = model.recuperarClientes();
                                    Vista.verC(list);
                                    break;
                                case 3:
                                    var list2 = model.recuperarClientes();
                                    Vista.verC(list2);
                                    String rem = Vista.eliminarCliente();
                                    if (rem.equals("s")) {
                                        System.out.println("Cancelado...");
                                        break;
                                    } else {
                                        model.eliminarCliente(rem);
                                    }
                                    break;
                                case 4:
                                    int dni = Vista.saveDni();
                                    String couta = Vista.newCouta();
                                    model.buscarCliente(dni, couta);
                                    break;
                                case 5:
                                    bandera = false;
                                    Conexion.cerrarConex();
                                    break;
                            }
                        } else {
                            System.out.println(":( Dato no valido ");
                            Conexion.cerrarConex();
                            break;
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println(ex + " fallo");
                        Conexion.cerrarConex();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e + " La connexion a la Base de Datos fallo");
                }
            } while (bandera != false);

        }

    public static void main(String[] args) {
        DataModelo modelo = new DataModelo();
        boolean flat = true;
        int leer;
        do {
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia", "root", "");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia", "root", "");
                Statement statement = conn.createStatement();
                ResultSet Query = statement.executeQuery("SELECT * From medicine");
                ResultSet QueryPV = statement.executeQuery("SELECT * From provider");
                PreparedStatement QueryInsert = conn.prepareStatement("Insert into medicine value (?,?,?,?,?)");
                PreparedStatement QueryInsertPV = conn.prepareStatement("Insert into provider value (?,?)");
                leer = View.menu();
                String l = "a";
                System.out.println(leer);
                if (leer == 1) {

                    Provider p = View.registerProvider();
                    int fila = modelo.idDBA(conn,QueryInsertPV,QueryPV);
                    QueryInsertPV.setInt(1, fila);
                    QueryInsertPV.setString(2, p.getName());
                    QueryInsertPV.executeUpdate();
                    System.out.println(" Proveedor registrado");
                    //proveedor = View.registerProvider(new Provider());
                    //System.out.println(proveedor);
                } else if (leer == 2) {
                    //Provider medicine1 = View.addingMedicine(proveedor);
                    //String st_medicine = medicine1.toString("#");
                    //System.out.println(st_medicine);
                } else if (leer == 3) {
                    modelo.listProvider(QueryPV);
                    statement.close();
                    QueryPV.close();
                    conn.close();

                }else if (leer == 4){
                    flat = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("" + e + " La connexion a la Base de Datos fallo");

            } catch (InputMismatchException var14) {
            System.out.println("" + var14 + " fallo");
            }


        } while (flat);
    }
}