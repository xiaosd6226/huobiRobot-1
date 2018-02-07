package lixin.huobi.transactionrobot.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.huobiApi.PublicApi;


public class AllCoins {
    private static JSONArray coins = new JSONArray();

    static {
        loadCoins();
    }
    private static void loadCoins() {
        PublicApi publicApi = new PublicApi();
        try {
            JSONObject currencys = publicApi.getAllCurrencys();
            coins = currencys.getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getCoins() {
        return coins;
    }
}
