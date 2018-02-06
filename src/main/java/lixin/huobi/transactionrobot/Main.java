package lixin.huobi.transactionrobot;

import com.alibaba.fastjson.JSONObject;
import com.sun.media.jfxmedia.logging.Logger;
import lixin.huobi.transactionrobot.huobiApi.AssetAPI;
import lixin.huobi.transactionrobot.huobiApi.MarketApi;
import lixin.huobi.transactionrobot.huobiApi.PublicApi;
import lixin.huobi.transactionrobot.huobiApi.TradeApi;


public class Main {
    public static void main(String[] args){
        MarketApi marketApi = new MarketApi();
        PublicApi publicApi = new PublicApi();
        AssetAPI assetAPI = new AssetAPI();
        TradeApi tradeApi = new TradeApi();
        try {
            //JSONObject kline = publicApi.getCurrentTime();
            //JSONObject kline = marketApi.getKline("btcusdt","1min",50);
            //JSONObject kline = marketApi.getMarketDetailMerged("btcusdt");
            //JSONObject kline = assetAPI.getBalanceByAccountId("1678980");
            //System.out.println(kline.toString());
        } catch (Exception e) {
            System.out.println("出错！");
        }
    }
}
