package auto.cn.appinspection.commons;
/**
 * 提供当前应用访问服务器的请求地址
 */
public class AppNetConfig {

    //本地地址
    public static final String HOST = "http://172.16.36.92:21663/api/";//提供ip地址
    //提供用户登录的地址
    public static final String USERLOGIN = HOST + "user/userLogin/";//访问登录的url
    //提供下载计划的地址
    //http://localhost:21663/api/GetAllPlan/?username=%E5%B7%A1%E6%A3%80%E4%B9%99%E7%8F%AD&rolename=%E7%94%B5%E6%B0%94%E5%B2%97%E4%BD%8D%E7%82%B9%E6%A3%80%E5%91%98
    public static final String GETALLPLAN = HOST + "GetAllPlan/";//访问登录的url
    public static final String KEY_PLANID = "planId";
    public static final String DB_NAME = "plan.db";
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
