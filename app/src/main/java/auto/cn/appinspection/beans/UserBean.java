package auto.cn.appinspection.beans;

public class UserBean {

    /**
     * Username : admin
     * Rolename : 系统管理员
     * LoginTime : 2020-06-28 14:21:56
     * IsLogin : true
     */

    private String Username;
    private String Rolename;
    private String LoginTime;
    private boolean IsLogin;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getRolename() {
        return Rolename;
    }

    public void setRolename(String Rolename) {
        this.Rolename = Rolename;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String LoginTime) {
        this.LoginTime = LoginTime;
    }

    public boolean getIsLogin() {
        return IsLogin;
    }

    public void setIsLogin(boolean IsLogin) {
        this.IsLogin = IsLogin;
    }
}
