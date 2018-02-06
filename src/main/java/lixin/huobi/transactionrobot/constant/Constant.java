package lixin.huobi.transactionrobot.constant;

import lixin.huobi.transactionrobot.utils.PropertyUtil;

public class Constant {
    public static String BASE_URL = "api.huobi.pro";
    public static String ACCESS_KEY = PropertyUtil.getBase("AccessKeyId");
    public static String SECRET_KEY = PropertyUtil.getBase("SecretKey");
    public static String SIGNATURE_METHOD = "HmacSHA256";
    public static String SIGNATURE_VERSION = "2";
    public static String POST = "POST";
    public static String GET = "GET";

    //user-agent
    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
}
