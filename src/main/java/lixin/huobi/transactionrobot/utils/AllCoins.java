package lixin.huobi.transactionrobot.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.service.PublicService;
import org.springframework.stereotype.Component;

@Component
public class AllCoins {
    private static JSONArray coins = new JSONArray();

    static {
        loadCoins();
    }
    private static void loadCoins() {
        PublicService publicService = new PublicService();
        try {
            JSONObject currencys = publicService.getAllCurrencys();
            coins = currencys.getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getCoins() {
        return coins;
    }
}
