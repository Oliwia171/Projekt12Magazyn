public class User {
    private String name;
    private String ID;

    public User(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }
    public String getname() {
        return name;
    }
    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User(id='" + ID + "', name='" + name+ "')" +
                "+";
    }
}