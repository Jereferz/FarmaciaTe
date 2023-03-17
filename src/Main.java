import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;


public class Main {
        public static void main(String[] args) {
            //DataModelo model = new DataModelo();

            boolean bandera = true;
            do {
                try {
                    Connection conn = Conexion.getInstancia().conectar();
                    int opc = View.menu();
                    try {
                        Medicine med = null;
                        Provider pro = null;
                        if (0 != opc) {
                            switch (opc) {
                                case 1:
                                    med = View.addingMedicine();
                                    try {

                                        DataModelo.saveMedicine(med);
                                        break;
                                    }catch (Exception a){
                                        DataModelo.saveMedicineTxt(med);
                                        break;
                                    }
                                case 2:
                                    pro = View.registerProvider();
                                    try {
                                        DataModelo.saveProvider(pro);
                                        break;
                                    }catch (Exception a){
                                        DataModelo.saveProviderTxt(pro);
                                        break;
                                    }
                                case 3:
                                    try {
                                        List<Medicine> list = DataModelo.recoverMedicine();
                                        View.listMedicine(list);
                                        break;
                                    } catch (Exception a) {
                                        List<Medicine> list1 = DataModelo.recoverMedicineTxt();
                                        View.listMedicine(list1);
                                        break;
                                    }
                                case 4:
                                    try {
                                        List<Provider> listP = DataModelo.recoverProvider();
                                        View.listProvider(listP);
                                        break;
                                    } catch (Exception a) {
                                        List<Provider> listP1 = DataModelo.recoverProviderTxt();
                                        View.listProvider(listP1);
                                        break;
                                    }
                                case 5:
                                    bandera = false;
                                    Conexion.closeCone();
                                    break;
                            }
                        } else {
                            System.out.println(":( Dato no valido ");
                            Conexion.closeCone();
                            break;
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println(ex + " fallo");
                        Conexion.closeCone();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e + " La connexion a la Base de Datos fallo");
                }
            } while (bandera != false);

        }
}