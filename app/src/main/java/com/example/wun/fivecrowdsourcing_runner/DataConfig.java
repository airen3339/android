package com.example.wun.fivecrowdsourcing_runner;

/**
 * Created by WUN on 2018/2/12.
 */


public class DataConfig {

    public static final boolean debugFlag=false;
    public static String UploadImage = "RunnerUploadImage";
    public static String RunnerInfoServlet = "RunnerInfoServlet";
    public static String RunnerGetOrder = "SendDelOrderServlet";
    public static String PENDING_INIT = "RunnerInitOrderServlet";
    public static String SENDING_LOCATION = "ReturnRunnerInfo";
    public static String SENDING_INIT = "RunnerSendingOrderInit";
//    public static final String  URL="http://115.159.101.178/FiveCrowdsourcing-Server/";//公网ip
//    public static final String  URL="http://172.20.10.4:8080/FiveCrowdsourcing-Server/";//个人热点
    //public static String URL = "http://192.168.1.10:8080/runnerTomcat/"; // IP地址请改为你自己的IP
    //public static String URL = "http://192.168.1.10:8080/FiveCrowdsourcing-Server/";
    public static final String  URL="http://192.168.43.245:8080/FiveCrowdsourcing-Server/";//无闪讯
    //public static final String  URL="http://172.20.10.3:8080/FiveCrowdsourcing-Server/";//zzc手机热点
   // public static final String  URL="http://180.163.32.172:8080/FiveCrowdsourcing-Server/";
    //
   // public static final String  URL="http://101.227.139.187:8080/FiveCrowdsourcing-Server/";

    //public static final String  URL="http://172.22.121.113:8080/FiveCrowdsourcing-Server/";//闪讯
   // public static final String  URL="http://192.168.43.121:8080/FiveCrowdsourcing-Server/";//闪讯
    //public static String URL = "http://192.168.1.10:8080/MyWorld_Service/";
    //public static String URL_Register = URL + "runnerRegister";
    public static String URL_Register = URL + "runnerRegister";
    //public static String URL_Login = URL + "runnerLogin";

    public static String URL_Login = "RunnerLoginServlet";
    //public static String URL_Login = URL + "LoginServlet";


}
