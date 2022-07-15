package domain;

public class Maid{
    private int id;
    private String name;
    private String contactNo;
    private boolean isAvailable;

    public Maid(int id, String name, String contactNo) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
        isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
