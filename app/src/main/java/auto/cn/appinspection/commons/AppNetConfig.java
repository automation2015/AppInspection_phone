package auto.cn.appinspection.commons;
/**
 * 提供当前应用访问服务器的请求地址
 */
public class AppNetConfig {

    //本地地址
    public static final String HOST = "http://192.168.1.128:7000/";//提供ip地址
    //提供web应用的地址
    public static final String USERLOGIN = HOST + "api/user/userLogin/";//访问登录的url

//    *****************************************************
    public static final int PICTURE = 100;
    public static final int CAMERA = 200;

    String url="http://192.168.1.128:7000/api/user/loginG/?username=admin&password=1";
    String url2="http://api.map.baidu.com/telematics/v3/weather?location=济南&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
    String url3= "http://192.168.1.128:59051/api/SanHengResult/JieJingQi_100T";

    //外网账号
//    public static final String IPADDRESS = "182.92.5.3";
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

//    public static final String INDEX = BASE_URL + "index";//访问首页数据
//
//
//
//    public static final String PRODUCT = BASE_URL + "product";//访问“所有理财”的url
//
//    public static final String UPDATE = BASE_URL + "update.json";//访问服务器端当前应用的版本信息
//
//    public static final String REGISTER = BASE_URL + "UserRegister";//注册
//
//    public static final String FEEDBACK = BASE_URL + "FeedBack";//用户反馈


}
