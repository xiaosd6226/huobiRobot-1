package lixin.huobi.transactionrobot.huobiApi;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/31 18:49
 * @Description 用户资产api
 */
public class AssetAPI {

    /**
     * @return id    账号
     * @return state 账户状态  working：正常, lock：账户被锁定
     * @return type  账户类型  spot：现货账户
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:49
     * @Description 查询当前用户的所有账户(即account-id)
     */
    public JSONObject getAccounts() throws Exception {
        Map<String,String> params = new HashMap();
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.ACCOUNTS, params);
        JSONObject accounts = APIClient.doGet(URL.ACCOUNTS, params);
        return accounts;
    }

    /**
     * @param account_id    账号
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:49
     * @Description 查询指定账户的余额
     */
    public JSONObject getBalanceByAccountId(String account_id) throws Exception {
        String url = URL.BALANCE.replace("{account-id}", account_id);
        Map<String,String> params = new HashMap();
        params.put("account-id", account_id);
        APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, url, params);
        JSONObject balance = APIClient.doGet(url, params);
        return balance;
    }

}
