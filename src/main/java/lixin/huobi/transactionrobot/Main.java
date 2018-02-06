package lixin.huobi.transactionrobot;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.huobiApi.MarketApi;
import lixin.huobi.transactionrobot.huobiApi.PublicApi;


public class Main {
    public static void main(String[] args){
        MarketApi marketApi = new MarketApi();
        PublicApi publicApi = new PublicApi();
        try {
            //JSONObject kline = publicApi.getAllCurrencys();
            //JSONObject kline = marketApi.getKline("btcusdt","1min",50);
            JSONObject kline = marketApi.getMarketDetailMerged("btcusdt");
            System.out.println(kline.toString());
        } catch (Exception e) {
            System.out.println("出错！");
        }
    }
}
