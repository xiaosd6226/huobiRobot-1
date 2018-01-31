package lixin.huobi.transactionrobot.huobiApi;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/31 19:04
 * @Description 交易api
 */
public class TradeApi {
    /**
     * @param account_id 账户 ID，使用accounts方法获得。币币交易使用‘spot’账户的accountid；借贷资产交易，请使用‘margin’账户的accountid
     * @param amount     限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
     * @param price      下单价格，市价单不传该参数 false
     * @param source     订单来源 false
     * @param symbol     交易对 false
     * @param type       订单类型 false
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 下单
     */
    public JSONObject placeOrder(String account_id, String amount, String price, String source, String symbol, String type) throws Exception {
        Map<String,String> params = new HashMap();
        JSONObject body = new JSONObject();
        body.put("account-id", account_id);
        body.put("amount", "amount");
        body.put("amount", price);
        body.put("source", source);
        body.put("symbol", symbol);
        body.put("type", type);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, URL.PLACE_ORDER, params);
        JSONObject orderId = APIClient.doPost(URL.PLACE_ORDER, params, body.toString());
        return orderId;
    }

}
