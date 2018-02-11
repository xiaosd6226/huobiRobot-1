package lixin.huobi.transactionrobot.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.URL;
import lixin.huobi.transactionrobot.utils.APIClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/31 19:04
 * @Description 交易api
 */
@Service
public class TradeService {
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
    public String placeOrder(String account_id, String amount, String price, String source, String symbol, String type) throws Exception {
        Map<String,String> params = new HashMap();
        JSONObject body = new JSONObject();
        body.put("account-id", account_id);
        body.put("amount", amount);
        if (StringUtils.isNotEmpty(price)) {
            body.put("price", price);
        }
        if (StringUtils.isNotEmpty(source)) {
            body.put("source", source);
        }
        if (StringUtils.isNotEmpty(symbol)) {
            body.put("symbol", symbol);
        }
        if (StringUtils.isNotEmpty(type)) {
            body.put("type", type);
        }
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, URL.PLACE_ORDER, params);
        JSONObject order = APIClient.doPost(URL.PLACE_ORDER, params, body.toString());
        String orderId = order.getString("data");
        return orderId;
    }

    /**
     * @param order_id  订单ID，填在path中
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 申请撤销一个订单请求
     * @return data  订单id
     */
    public JSONObject cancelOrder(String order_id) throws Exception {
        Map<String,String> params = new HashMap();
        String uri = URL.CANCEL_ORDER.replace("{order-id}", order_id);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, uri, params);
        JSONObject cancelOrder = APIClient.doPost(uri, params, null);
        return cancelOrder;
    }

    /**
     * @param order_ids  撤销订单ID列表 list 单次不超过50个订单id
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 批量撤销订单
     * @return data  撤单结果 map
     */
    public JSONObject cancelOrders(JSONArray order_ids) throws Exception {
        Map<String,String> params = new HashMap();
        JSONObject body = new JSONObject();
        body.put("order-ids", order_ids);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, URL.CANCEL_MUTI_ORDER, params);
        JSONObject cancelOrders = APIClient.doPost(URL.CANCEL_MUTI_ORDER, params, body.toString());
        return cancelOrders;
    }

    /**
     * @param order_id  订单ID
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 查询某个订单详情
     * @return data  撤单结果 map
     */
    public JSONObject queryOrderDetail(String order_id) throws Exception {
        Map<String,String> params = new HashMap();
        String uri = URL.ORDERS_DETAIL.replace("{order-id}", order_id);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, uri, params);
        JSONObject orderDetail = APIClient.doPost(uri, params, null);
        return orderDetail;
    }

    /**
     * @param order_id  订单ID
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 查询某个订单的成交明细
     * @return
     */
    public JSONObject queryOrderBargin(String order_id) throws Exception {
        Map<String,String> params = new HashMap();
        String uri = URL.BARGAIN_DETAIL.replace("{order-id}", order_id);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, uri, params);
        JSONObject bargainDetail = APIClient.doPost(uri, params, null);
        return bargainDetail;
    }

    /**
     * @param symbol     交易对  true
     * @param types      查询的订单类型组合，使用','分割    false
     * @param start_date 查询开始日期, 日期格式yyyy-mm-dd   false
     * @param end_date   查询结束日期, 日期格式yyyy-mm-dd   false
     * @param states     查询的订单状态组合，使用','分割   false
     * @param from       查询起始 ID   false
     * @param direct     查询方向  false
     * @param size       查询记录大小  false
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 查询某个订单的成交明细
     */
    public JSONObject queryEntrust(String symbol, String types, String start_date, String end_date, String states, String from, String direct, String size) throws Exception {
        Map<String, String> params = new HashMap();
        JSONObject body = new JSONObject();
        body.put("symbol", symbol);
        if (StringUtils.isNotEmpty(types)) {
            body.put("types", types);
        }
        if (StringUtils.isNotEmpty(start_date)) {
            body.put("start-date", start_date);
        }
        if (StringUtils.isNotEmpty(end_date)) {
            body.put("end-date", end_date);
        }
        if (StringUtils.isNotEmpty(states)) {
            body.put("states", states);
        }
        if (StringUtils.isNotEmpty(from)) {
            body.put("from", from);
        }
        if (StringUtils.isNotEmpty(direct)) {
            body.put("direct", direct);
        }
        if (StringUtils.isNotEmpty(size)) {
            body.put("size", size);
        }
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, URL.ORDERS_ENTRUST, params);
        JSONObject entrusts = APIClient.doPost(URL.ORDERS_ENTRUST, params, body.toString());
        return entrusts;
    }

    /**
     * @param symbol     交易对  true
     * @param types      查询的订单类型组合，使用','分割    false
     * @param start_date 查询开始日期, 日期格式yyyy-mm-dd   false
     * @param end_date   查询结束日期, 日期格式yyyy-mm-dd   false
     * @param from       查询起始 ID   false
     * @param direct     查询方向  false
     * @param size       查询记录大小  false
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/31 19:10
     * @Description 查询某个订单的成交明细
     */
    public JSONObject queryEntrust(String symbol, String types, String start_date, String end_date, String from, String direct, String size) throws Exception {
        Map<String, String> params = new HashMap();
        JSONObject body = new JSONObject();
        body.put("symbol", symbol);
        if (StringUtils.isNotEmpty(types)) {
            body.put("types", types);
        }
        if (StringUtils.isNotEmpty(start_date)) {
            body.put("start-date", start_date);
        }
        if (StringUtils.isNotEmpty(end_date)) {
            body.put("end-date", end_date);
        }
        if (StringUtils.isNotEmpty(from)) {
            body.put("from", from);
        }
        if (StringUtils.isNotEmpty(direct)) {
            body.put("direct", direct);
        }
        if (StringUtils.isNotEmpty(size)) {
            body.put("size", size);
        }
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.POST, Constant.BASE_URL, URL.ALL_BARGAIN_DETAIL, params);
        JSONObject mutiBargainDetail = APIClient.doPost(URL.ALL_BARGAIN_DETAIL, params, body.toString());
        return mutiBargainDetail;
    }
}
