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
     * @return  base_currency    基础币种
     * @return quote_currency   计价币种
     * @return price_precision  价格精度位数（0为个位）
     * @return amount_precision 数量精度位数（0为个位）
     * @return symbol_partition main主区，innovation创新区，bifurcation分叉区
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:13
     * @Description 查询系统支持的所有交易对及精度
     */
    public JSONObject getAllSymbols() throws Exception {
        Map<String,String> params = new HashMap();
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
