package lixin.huobi.transactionrobot.constant;

import lixin.huobi.transactionrobot.utils.PropertyUtil;

import java.util.Properties;

public class URL {
    //行情api相对路径
    public static String KLINE = "/market/history/kline";
    public static String Depth = "/market/depth";
    public static String TRADE_DETAIL = "/market/trade";
    public static String TRADE_HISTORY = "/market/history/trade";
    public static String TRADE_DETAIL_24 = "/market/detail";
    public static String TRADE_DETAIL_MERGED = "/market/detail/merged";

    //公共api相对路径
    public static String SYMBOLS = "/v1/common/symbols";
    public static String CURRENCYS = "/v1/common/currencys";
    public static String TIME_STAMP = "/v1/common/timestamp";

    //用户资产api相对路径
    public static String ACCOUNTS = "/v1/account/accounts";
    public static String BALANCE = "/v1/account/accounts/{account-id}/balance";

    //交易api
    public static String PLACE_ORDER = "/v1/order/orders/place";
    public static String CANCEL_ORDER = "/v1/order/orders/{order-id}/submitcancel";
    public static String CANCEL_MUTI_ORDER = "/v1/order/orders/batchcancel";
    public static String ORDERS_DETAIL = "/v1/order/orders/{order-id}";
    public static String BARGAIN_DETAIL = "/v1/order/orders/{order-id}/matchresults";
    public static String ORDERS_ENTRUST = "/v1/order/orders";
    public static String ALL_BARGAIN_DETAIL = "/v1/order/matchresults";


}
