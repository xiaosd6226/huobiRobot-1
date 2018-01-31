package lixin.huobi.transactionrobot.huobiApi;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/30 19:04
 * @Description 公共api
 */
public class PublicApi {

    /**
     * @param base_currency    基础币种
     * @param quote_currency   计价币种
     * @param price_precision  价格精度位数（0为个位）
     * @param amount_precision 数量精度位数（0为个位）
     * @param symbol_partition main主区，innovation创新区，bifurcation分叉区
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:13
     * @Description 查询系统支持的所有交易对及精度
     */
    public JSONObject getAllSymbols(String base_currency, String quote_currency, String price_precision, String amount_precision, String symbol_partition) throws Exception {
        Map<String,String> params = new HashMap();
        params.put("base-currency", base_currency);
        params.put("quote-currency", quote_currency);
        params.put("price-precision", price_precision);
        params.put("amount-precision", amount_precision);
        params.put("symbol-partition", symbol_partition);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.SYMBOLS, params);
        JSONObject symbols = APIClient.doGet(URL.SYMBOLS, params);
        return symbols;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:13
     * @Description 查询系统支持的所有币种
     */
    public JSONObject getAllCurrencys () throws Exception {
        Map<String,String> params = new HashMap();
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.CURRENCYS, params);
        JSONObject currencys = APIClient.doGet(URL.CURRENCYS, params);
        return currencys;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:13
     * @Description 查询系统当前时间
     */
    public JSONObject getCurrentTime() throws Exception {
        Map<String,String> params = new HashMap();
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TIME_STAMP, params);
        JSONObject currentTime = APIClient.doGet(URL.TIME_STAMP, params);
        return currentTime;
    }
}
