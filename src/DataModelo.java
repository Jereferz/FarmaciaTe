
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                System.out.println("Fallo al eliminar de la base de datos");
            }

        }
        public static boolean saveProvider(Provider p) {
            boolean exit;
            try {
                Connection con = Conexion.getInstancia().conectar();
                PreparedStatement QueryInsert = con.prepareStatement("Insert into medicines value (?,?,?,?,?)");
                QueryInsert.setString(1, null);
                QueryInsert.setString(2, p.getName());
                QueryInsert.setInt(3, p.getPhoneNumber());
                int medicineInsert = QueryInsert.executeUpdate();
                exit = true;
            } catch (SQLException ex) {
                System.out.println("Error");
                exit = false;
            }
            return exit;
        }
        public static boolean saveMedicine(Medicine m) {
            boolean exit;
            try {
                Connection con = Conexion.getInstancia().conectar();
                PreparedStatement QueryInsert = con.prepareStatement("Insert into medicines value (?,?,?,?,?)");
                QueryInsert.setString(1, null);
                QueryInsert.setInt(2, m.getCode());
                QueryInsert.setString(3, m.getName());
                QueryInsert.setInt(4, m.getAmount());
                QueryInsert.setDouble(5, m.getUnitPrice());
                int medicineInsert = QueryInsert.executeUpdate();
                exit = true;
            } catch (SQLException ex) {
                System.out.println("Error");
                exit = false;
            }
            return exit;
        }

        public static List<Medicine> recoverMedicine() {
            List<Medicine> result = new ArrayList<>();
            try {
                Connection conn = Conexion.getInstancia().conectar();
                ResultSet rs = conn.prepareStatement("Select * from medicine where Estado = 1;").executeQuery();
                while (rs.next()) {
                    Medicine medicina = new Medicine();
                    medicina.setCode(rs.getInt(2));
                    medicina.setName(rs.getString(3));
                    medicina.setAmount(rs.getInt(4));
                    medicina.setUnitPrice(rs.getInt(5));
                    Medicine.setState(rs.getInt(6));
                    result.add(medicina);
                }
            } catch (SQLException e) {
                System.out.println("Error :C");
            }
            return result;
        }
        public static List<Provider> recoverProvider() {
            List<Provider> result = new ArrayList<>();
            try {
                Connection conn = Conexion.getInstancia().conectar();
                ResultSet rs = conn.prepareStatement("Select * from provider;").executeQuery();
                while (rs.next()) {
                    Provider provider = new Provider();
                    provider.setName(rs.getString(2));
                    result.add(provider);
                }
            } catch (SQLException e) {
                System.out.println("Error :C");
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
        //--------------------TXT--------------------//
        public static void saveProviderTxt(Provider p) {
            ArrayList<Provider> providers = new ArrayList<>();
            providers.addAll(DataModelo.recoverProviderTxt());
            providers.add(p);
            try {
                ObjectOutputStream copyDates = new ObjectOutputStream(new FileOutputStream("C:/dates/Provider.txt"));
                copyDates.writeObject(providers);
                copyDates.close();
            } catch (Exception e) {

            }
        }

        public static ArrayList recoverProviderTxt() {
            ArrayList<Provider> recoverP = new ArrayList<>();
            try {
                ObjectInputStream getDates = new ObjectInputStream(new FileInputStream("C:/dates/Provider.txt"));
                recoverP = (ArrayList<Provider>) getDates.readObject();
            } catch (Exception e) {

            }
            return recoverP;
        }

        public static void saveMedicineTxt(Medicine m) {
            ArrayList<Medicine> medicines = new ArrayList<>();
            medicines.addAll(DataModelo.recoverMedicineTxt());
            medicines.add(m);

            try {
                ObjectOutputStream copyDates = new ObjectOutputStream(new FileOutputStream("C:/dates/Medicine.txt"));
                copyDates.writeObject(medicines);
                copyDates.close();
            } catch (Exception e) {

            }
        }

        public static List<Medicine> recoverMedicineTxt() {
            List<Medicine> recoverM = new ArrayList<>();
            try {
                ObjectInputStream getDates = new ObjectInputStream(new FileInputStream("C:/dates/Medicine.txt"));
                recoverM = (ArrayList<Medicine>) getDates.readObject();

            } catch (Exception e) {

            }
            return recoverM;
        }
        public static void sobreescribirMed(List <Medicine> sobreescrito){
            ArrayList<Medicine> MedicinesTxt = (ArrayList<Medicine>) sobreescrito;
            try {
                ObjectOutputStream setDates= new ObjectOutputStream(new FileOutputStream("C:/MisFicheros/Medicine.txt"));
                setDates.writeObject(MedicinesTxt);
                setDates.close();
            }catch (Exception e){
                System.out.println("Fallo la conexion del txt guardar");
            }
        }
        public static List<Medicine> searchMedicineTxt(int code, String price) {
            List<Medicine> med = recoverMedicineTxt();
            try {
                for (int i = 0; i < med.size(); i++) {
                    if (med.get(i).getCode() == code) {
                        med.get(i).setUnitPrice(Integer.parseInt(price));
                        System.out.println("Encontrado");
                        System.out.println(med.get(i));
                        sobreescribirMed(med);
                        break;
                    }else {
                        System.out.println("no econtrado");
                    }
                }
                return med;
            } catch (Exception e) {
                return med;
            }

        }
        public static  void deleteMedicineTxt (String code){
            List<Medicine> medicines = recoverMedicineTxt();
            for (int i = 0; i <medicines.size(); i++) {
                if (Integer.parseInt(code) == medicines.get(i).getCode()) {
                    medicines.remove(i);
                    System.out.println("Eliminado correctamente");
                    sobreescribirMed(medicines);
                    break;
                }else {
                    System.out.println("El usuario no fue encontrado");
                }
            }

        }
    }