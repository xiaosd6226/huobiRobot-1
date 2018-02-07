package lixin.huobi.transactionrobot.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.Type;
import lixin.huobi.transactionrobot.huobiApi.PublicApi;
import lixin.huobi.transactionrobot.huobiApi.TradeApi;
import lixin.huobi.transactionrobot.utils.AllCoins;
import lixin.huobi.transactionrobot.utils.QueueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class NewCoinTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(NewCoinTask.class);
    private String area = "btc";

    public NewCoinTask(String area) {
        this.area = area;
    }
    @Override
    public void run() {
        JSONArray coins = AllCoins.getCoins();
        PublicApi publicApi = new PublicApi();
        TradeApi tradeApi = new TradeApi();
        while (true) {
            try {
                JSONObject currencys = publicApi.getAllCurrencys();
                JSONArray data = currencys.getJSONArray("data");
                //若币种数量变多，说明此时出现新币种
                if (data.size() > coins.size()) {
                    //从Queue获取新币种的sybmol
                    BlockingQueue<String> huobiQueue = QueueUtils.getHuobiQueue(area);
                    String sybmol = huobiQueue.remove();
                    logger.info("获取交易对:{}", sybmol);
                    //下单
                    logger.info("开始下单！");
                    tradeApi.placeOrder(Constant.SPOT_ACCOUNT, "1", "1", null, sybmol, Type.BUY_LIMIT);
                    logger.info("下单结束！");
                }
            } catch (Exception e) {
                logger.error("未能获取所有币种，api超时或异常！", e);
                continue;
            }
        }
    }
}
