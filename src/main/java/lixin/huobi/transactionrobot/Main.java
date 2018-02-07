package lixin.huobi.transactionrobot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.huobiApi.AssetAPI;
import lixin.huobi.transactionrobot.huobiApi.MarketApi;
import lixin.huobi.transactionrobot.huobiApi.PublicApi;
import lixin.huobi.transactionrobot.huobiApi.TradeApi;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);
    public static void main(String[] args){
        MarketApi marketApi = new MarketApi();
        PublicApi publicApi = new PublicApi();
        AssetAPI assetAPI = new AssetAPI();
        TradeApi tradeApi = new TradeApi();
        try {
            long startTime = System.currentTimeMillis();
            //JSONObject kline = publicApi.getCurrentTime();
            //JSONObject kline = assetAPI.getAccounts();
            //JSONObject kline = publicApi.getAllSymbols();
            //JSONObject kline = publicApi.getAllCurrencys();
            //JSONObject kline = marketApi.getKline("btcusdt","1min",50);
            //JSONObject kline = marketApi.getMarketDetailMerged("btcusdt");
            //JSONObject kline = assetAPI.getBalanceBySpot();
            //System.out.println(kline.toString());
            //threadPool.execute(new NewCoinEasyTask("btc",""));
//            String orderId = tradeApi.placeOrder(Constant.SPOT_ACCOUNT, "1", "0.00000110", null, "dtabtc", Type.BUY_LIMIT);
//            System.out.println(orderId);
            String orderId = "1241877666";
//tradeApi.cancelOrder(orderId);
            JSONArray array = new JSONArray();
            array.add(orderId);
            JSONObject object = tradeApi.cancelOrders(array);
            System.out.println(object.toString());
            long endTime = System.currentTimeMillis();
            System.out.println((endTime-startTime)/1000);
        } catch (Exception e) {
            System.out.println("出错！");
        }
    }
}
