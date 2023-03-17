public class Provider {
    private String name;
    private int phoneNumber;
    public Provider() {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    @Override
    public String toString() {
        return "Provider{" +
                "Nombre ='" + name + '\'' +
                '}';
    }
}
