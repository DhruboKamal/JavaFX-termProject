package SharedClass;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 13593L;
    private boolean status;
    private String userName;
    private String password;

    public User() {
    }

    public User(boolean status, String userName, String password) {
        this.status = status;
        this.userName = userName;
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean getstatus() {
        return status;
    }

    public void setstatus(boolean status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

