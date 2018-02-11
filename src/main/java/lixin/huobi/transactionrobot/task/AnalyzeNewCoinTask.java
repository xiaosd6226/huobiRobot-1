package lixin.huobi.transactionrobot.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by jodan on 2018/2/10.
 */
public class AnalyzeNewCoinTask implements Runnable{
    private String symbol;
    private MarketService marketService;
    private Logger logger = LoggerFactory.getLogger(AnalyzeNewCoinTask.class);
    public AnalyzeNewCoinTask(String symbol, MarketService marketService) {
        this.marketService = marketService;
        this.symbol = symbol;
    }


    @Override
    public void run() {
        //根据交易对查询买盘和卖盘
        while (true) {
            try {
                JSONObject marketDetail = marketService.getMarketDetailMerged(symbol);
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
    }
}
