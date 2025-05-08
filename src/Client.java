import java.util.Objects;

public class Client {
    final String name;
    final String ID;

    public Client(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || this.getClass() != o.getClass()) return false;
//        Client client = (Client) o;
//        return Objects.equals(ID, client.ID);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(ID);
//    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
