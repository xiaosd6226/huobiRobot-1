package lixin.huobi.transactionrobot.service;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.URL;
import lixin.huobi.transactionrobot.utils.APIClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MarketService {

    /**
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/30 17:18
     * @Param
     * @Description 获取K线数据 get请求
     */
    public JSONObject getKline(String symbol, String period, Integer size) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        params.put("period", period);
        if (size != null) {
            params.put("size", String.valueOf(size));
        }
        APIClient.createSignature(Constant.ACCESS_KEY,Constant.SECRET_KEY,Constant.GET,Constant.BASE_URL, URL.KLINE,params);
        JSONObject kline = APIClient.doGet(URL.KLINE, params);
        return kline;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/30 18:15
     * @Param symbol 为交易对
     * @Description 获取聚合行情
     *   "tick": {
                    "id": K线id,
                    "amount": 成交量,
                    "count": 成交笔数,
                    "open": 开盘价,
                    "close": 收盘价,当K线为最晚的一根时，是最新成交价
                    "low": 最低价,
                    "high": 最高价,
                    "vol": 成交额, 即 sum(每一笔成交价 * 该笔的成交量)
                    "bid": [买1价,买1量],
                    "ask": [卖1价,卖1量]
                  }
     */
    public JSONObject getMarketDetailMerged(String symbol) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TRADE_DETAIL_MERGED, params);
        JSONObject marketDetailMerged = APIClient.doGet(URL.TRADE_DETAIL_MERGED, params);
        return marketDetailMerged;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/30 18:15
     * @Param symbol 为交易对，type为精度
     * @Description 获取当前的挂单  各150个
     * @return asks 卖单   bids 买单
     *   "tick": {
                    "id": 消息id,
                    "ts": 消息生成时间，单位：毫秒,
                    "bids": 买盘,[price(成交价), amount(成交量)], 按price降序,
                    "asks": 卖盘,[price(成交价), amount(成交量)], 按price升序
                 }
     */
    public JSONObject getMarketDepth(String symbol, String type) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        params.put("type", type);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.Depth, params);
        JSONObject marketDepth = APIClient.doGet(URL.Depth, params);
        return marketDepth;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/30 18:31
     * @param symbol 交易对
     * @Description  获取 Trade Detail 数据
     * @return
     */
    public JSONObject getTradeDetail(String symbol) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TRADE_DETAIL, params);
        JSONObject tradeDetail = APIClient.doGet(URL.TRADE_DETAIL, params);
        return tradeDetail;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/30 18:31
     * @param symbol 交易对
     * @param size 获取交易记录的数量
     * @Description 批量获取最近的交易记录
     * "data": {
            "id": 消息id,
            "ts": 最新成交时间,
            "data": [
                        {
                        "id": 成交id,
                        "price": 成交价,
                        "amount": 成交量,
                        "direction": 主动成交方向,
                        "ts": 成交时间
                        }
            ]
       }
     */
    public JSONObject getTradeHistory(String symbol,Integer size) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        if (size != null) {
            params.put("size", String.valueOf(size));
        }
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TRADE_HISTORY, params);
        JSONObject tradeHistory = APIClient.doGet(URL.TRADE_HISTORY, params);
        return tradeHistory;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/30 18:31
     * @param symbol 交易对
     * @Description 获取 Market Detail 24小时成交量数据
     *  "tick": {
                "id": 消息id,
                "ts": 24小时统计时间,
                "amount": 24小时成交量,
                "open": 前推24小时成交价,
                "close": 当前成交价,
                "high": 近24小时最高价,
                "low": 近24小时最低价,
                "count": 近24小时累积成交数,
                "vol": 近24小时累积成交额, 即 sum(每一笔成交价 * 该笔的成交量)
        }
     */
    public JSONObject get24TradeDetail(String symbol) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("symbol", symbol);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TRADE_DETAIL_24, params);
        JSONObject trade24Detail = APIClient.doGet(URL.TRADE_DETAIL_24, params);
        return trade24Detail;
    }

}
