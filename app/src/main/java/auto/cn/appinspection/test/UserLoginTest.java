package auto.cn.appinspection.test;

import java.io.Serializable;

public class UserLoginTest implements Serializable {
    private String LoginName;
    private String LoginPws;

    public UserLoginTest() {
    }

    public UserLoginTest(String username, String password) {
        this.LoginName = username;
        this.LoginPws = password;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        this.LoginName = loginName;
    }

    public String getLoginPws() {
        return LoginPws;
    }

    public void setLoginPws(String loginPws) {
        this.LoginPws = loginPws;
    }

    @Override
    public String toString() {
        return "UserLoginTest{" +
                "Username='" + LoginName + '\'' +
                ", Password='" + LoginPws + '\'' +
                '}';
    }
}
