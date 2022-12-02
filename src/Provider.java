public class Provider {
    private String name;
    //private ArrayList<Medicine> newmedicine;
    /*public Provider() {
        newmedicine = new ArrayList<Medicine>();
    }*/

    public Provider() {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //
    /*public void addMedicine(Medicine m) {
        newmedicine.add(m);
    }
    public void delMedicine(Medicine m) {
        newmedicine.remove(m);
    }*/

    @Override
    public String toString() {
        return "Provider{" +
                "Nombre ='" + name + '\'' +
                '}';
    }
    /*public String toString() {
        String c = "";
        for (int i=0;i<newmedicine.size();i++) {
            c = c + newmedicine.get(i).toString();
        }
        return "Provedor [ Nombre= " + name +" ] "+c;
    }*/
    /*public String toString(String sep) {
        String c = "";
        for (int i=0;i<newmedicine.size();i++) {
            c = c+newmedicine.get(i).toString("#")+sep;
        }
        return c;
    }*/
}
