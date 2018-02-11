package lixin.huobi.transactionrobot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.DepthType;
import lixin.huobi.transactionrobot.service.AssetService;
import lixin.huobi.transactionrobot.service.MarketService;
import lixin.huobi.transactionrobot.service.PublicService;
import lixin.huobi.transactionrobot.service.TradeService;
import lixin.huobi.transactionrobot.task.NewCoinEasyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args){
        MarketService marketService = new MarketService();
        PublicService publicService = new PublicService();
        AssetService assetService = new AssetService();
        TradeService tradeService = new TradeService();
        try {
            long startTime = System.currentTimeMillis();
            //JSONObject kline = publicService.getCurrentTime();
            //JSONObject kline = assetService.getAccounts();
            //JSONObject kline = publicService.getAllSymbols();
            //JSONObject kline = publicService.getAllCurrencys();
            //JSONObject kline = marketService.getKline("btcusdt","1min",50);
            //JSONObject kline = marketService.getMarketDetailMerged("btcusdt");
            //JSONObject kline = assetService.getBalanceBySpot();
            //System.out.println(kline.toString());
           // threadPool.execute(new NewCoinEasyTask("btc"));
//            String orderId = tradeService.placeOrder(Constant.SPOT_ACCOUNT, "1", "0.00000110", null, "dtabtc", TradeType.BUY_LIMIT);
//            System.out.println(orderId);
           // String orderId = "1241877666";
//tradeService.cancelOrder(orderId);
//            JSONArray array = new JSONArray();
//            array.add(orderId);
//            JSONObject object = tradeService.cancelOrders(array);
//            System.out.println(object.toString());
            while (true) {
                try {
                    JSONObject marketDetail = marketService.getMarketDetailMerged("ethbtc");
                    if (null != marketDetail && "ok".equals(marketDetail.getString("status"))) {
                        JSONObject tick = marketDetail.getJSONObject("tick");
                        if (null == tick) {
                            continue;
                        }
                        //获取买盘
                        JSONArray bid = tick.getJSONArray("bid");
                        BigDecimal buyPrice = bid.getBigDecimal(0);
                        BigDecimal buyAmount = bid.getBigDecimal(1);
                        //获取卖盘
                        JSONArray ask = tick.getJSONArray("ask");
                        BigDecimal sellPrice = ask.getBigDecimal(0);
                        BigDecimal sellAmount = ask.getBigDecimal(1);
                        logger.info("买单价格为：{}, 量为：{}；卖单价格为: {}, 量为：{}", buyPrice, buyAmount, sellPrice, sellAmount);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            long endTime = System.currentTimeMillis();
//            System.out.println((endTime-startTime)/1000);
        } catch (Exception e) {
            System.out.println("出错！");
        }
    }
}
