package lixin.huobi.transactionrobot;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.huobiApi.APIClient;
import lixin.huobi.transactionrobot.huobiApi.MarketApi;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        MarketApi marketApi = new MarketApi();
        try {
            JSONObject kline = marketApi.getMarketDepth("btcusdt", "step0");
            System.out.println(kline.toString());
        } catch (Exception e) {
            System.out.println("出错！");
        }
    }
}
