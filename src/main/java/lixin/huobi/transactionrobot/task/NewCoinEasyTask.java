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
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingQueue;


public class NewCoinEasyTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(NewCoinEasyTask.class);
    private String area = "btc";
    private String sybmol;
    private String price;

    public NewCoinEasyTask(String area) {
        this.area = area;
    }

    public NewCoinEasyTask(String area, String sybmol) {
        this.sybmol = sybmol;
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
                    logger.info("获取交易对:{}", sybmol);
                    if (StringUtils.isEmpty(sybmol)) {
                        data.removeAll(coins);
                        sybmol = data.getString(0) + area;
                    }

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
