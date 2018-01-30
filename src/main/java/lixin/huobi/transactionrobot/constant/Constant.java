package lixin.huobi.transactionrobot.constant;

import lixin.huobi.transactionrobot.utils.PropertyUtil;

public class Constant {
    public static String BASE_URL = "api.huobi.pro";
    public static String MARKET_BASE_URL = "https://api.huobi.pro/market";
    public static String TRADE_BASE_URL = "https://api.huobi.pro/v1";
    public static String ACCESS_KEY = PropertyUtil.getBase("AccessKeyId");
    public static String SECRET_KEY = PropertyUtil.getBase("SecretKey");
    public static String SIGNATURE_METHOD = "HmacSHA256";
    public static String SIGNATURE_VERSION = "2";
    public static String POST = "POST";
    public static String GET = "GET";


}
