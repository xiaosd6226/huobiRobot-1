package lixin.huobi.transactionrobot.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.TradeType;
import lixin.huobi.transactionrobot.service.PublicService;
import lixin.huobi.transactionrobot.service.TradeService;
import lixin.huobi.transactionrobot.utils.AllCoins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


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
        PublicService publicService = new PublicService();
        TradeService tradeService = new TradeService();
        while (true) {
            try {
                JSONObject currencys = publicService.getAllCurrencys();
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
