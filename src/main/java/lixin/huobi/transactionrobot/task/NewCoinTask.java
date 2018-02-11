package lixin.huobi.transactionrobot.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.TradeType;
import lixin.huobi.transactionrobot.service.PublicService;
import lixin.huobi.transactionrobot.service.TradeService;
import lixin.huobi.transactionrobot.utils.AllCoins;
import lixin.huobi.transactionrobot.utils.QueueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        PublicService publicService = new PublicService();
        TradeService tradeService = new TradeService();
        while (true) {
            try {
                JSONObject currencys = publicService.getAllCurrencys();
                JSONArray data = currencys.getJSONArray("data");
                //若币种数量变多，说明此时出现新币种
                if (data.size() > coins.size()) {
                    //从Queue获取新币种的sybmol
                    BlockingQueue<String> huobiQueue = QueueUtils.getHuobiQueue(area);
                    String sybmol = huobiQueue.remove();
                    logger.info("获取交易对:{}", sybmol);
                    //下单
                    logger.info("开始下单！");
                    tradeService.placeOrder(Constant.SPOT_ACCOUNT, "1", "1", null, sybmol, TradeType.BUY_LIMIT);
                    logger.info("下单结束！");
                }
            } catch (Exception e) {
                logger.error("未能获取所有币种，api超时或异常！", e);
                continue;
            }
        }
    }
}
