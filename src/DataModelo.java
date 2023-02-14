
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class DataModelo {
        static Scanner Leer = new Scanner(System.in);
        static Scanner Leer2 = new Scanner(System.in);
        public DataModelo() {
        }

        public void buscarMedicine (Connection conn , int code) {
            try{

                String queryBuscar = "SELECT * From medicine where code ="+code;
                Statement consulta = conn.createStatement();
                ResultSet registro = consulta.executeQuery(queryBuscar);
                if (registro.next()){
                    String nom = registro.getString("code");
                    System.out.println(registro.getInt(1) + " #Code:" + registro.getInt(2) +
                            " #Nombre:" + registro.getString(3) + " #Cantidad:" + registro.getInt(4) +
                            " #Precio Unitario:" + registro.getDouble(5));
                    System.out.println(" ");
                    System.out.println("Ingrese el proveedor");
                    String prov = Leer2.nextLine();
                    String queryUp = "UPDATE `farmacia`.`medicine` SET provider_med = '" + prov + "' WHERE (`code` =" + code + ")";
                    int countUpdate = conn.prepareStatement(queryUp).executeUpdate();
                    System.out.println("Cambios realizados:"+ countUpdate);
                }else {
                    System.out.println("Codigo de medicina no existe");
                }

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public int recupararCode(Connection con) {
            int id = 1;
            try {
                ResultSet Query;
                PreparedStatement QueryInsert;
                QueryInsert = con.prepareStatement("Select max(ID) from Medicine");
                Query = QueryInsert.executeQuery();
                while (Query.next()) id = Query.getInt(1) + 1;
            } catch (Exception e) {
                System.out.println("no hay medicinas registradas");
            }
            return id;
        }


        public void eliminarMedicine(String rem) {
            String queryDL = "DELETE FROM `farmacia`.`medicine` WHERE (`code` =" + rem + ")";
            try {
                Connection con = Conexion.getInstancia().conectar();
                Statement statement = con.createStatement();
                statement.executeUpdate(queryDL);
                System.out.println("Medicina Eliminada");

            } catch (SQLException e) {
                System.out.println("Fallo eliminar de la base de datos");
            }

        }

        public void saveMedicine(Medicine c, int f) {
            try {
                Connection con = Conexion.getInstancia().conectar();
                PreparedStatement QueryInsert = con.prepareStatement("Insert into Clientes value (?,?,?,?,?,?)");
                QueryInsert.setInt(1, f);
                QueryInsert.setInt(2, c.getCode());
                QueryInsert.setString(3, c.getName());
                QueryInsert.setInt(4, c.getAmount());
                QueryInsert.setDouble(5, c.getUnitPrice());
                int medicineInsert = QueryInsert.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error");
            }
        }

        public static List<Provider> recuperarClientes() {
            List<Provider> result = new ArrayList<>();
            try {
                Connection conn = Conexion.getInstancia().conectar();
                ResultSet rs = conn.prepareStatement("Select * from provider;").executeQuery();
                while (rs.next()) {
                    Provider provider = new Provider();
                    provider.setName(rs.getString(2));
                    result.add(provider);
                }
            } catch (SQLException ex) {
                System.out.println("Error");
            }
            return result;
        }

        public static void buscarCliente(int celular1, int celular) {
            try {
                Connection conn = Conexion.getInstancia().conectar();
                String queryBuscar = "SELECT * From provider where DNI =" + celular1;
                Statement consulta = conn.createStatement();
                ResultSet registro = consulta.executeQuery(queryBuscar);
                if (registro.next()) {
                    System.out.println(registro.getInt(1) + " #Nombre:" + registro.getString(2));
                    System.out.println(" ");
                    String queryUp = "UPDATE `farmacia`.`provider` SET Celular = '" + celular + "' WHERE (`celular` =" + celular1 + ")";
                    int countUpdate = conn.prepareStatement(queryUp).executeUpdate();
                    System.out.println("Cambios realizados:" + countUpdate);
                } else {
                    System.out.println("Celular de Proveedor no fué encontrado");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }