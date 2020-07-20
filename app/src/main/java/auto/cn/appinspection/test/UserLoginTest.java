package auto.cn.appinspection.test;

import java.io.Serializable;

public class UserLoginTest implements Serializable {
    private String username;
    private String password;

    public UserLoginTest() {
    }

    public UserLoginTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginTest{" +
                "Username='" + username + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
