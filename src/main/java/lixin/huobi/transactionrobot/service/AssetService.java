package lixin.huobi.transactionrobot.service;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Signature;
import lixin.huobi.transactionrobot.constant.URL;
import lixin.huobi.transactionrobot.utils.APIClient;
import lixin.huobi.transactionrobot.utils.SignatureUtils;
import org.springframework.stereotype.Service;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/31 18:49
 * @Description 用户资产api
 */
@Service
public class AssetService {

    /**
     * @return id    账号
     * @return state 账户状态  working：正常, lock：账户被锁定
     * @return type  账户类型  spot：现货账户
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:49
     * @Description 查询当前用户的所有账户(即account-id)
     */
    public JSONObject getAccounts() throws Exception {
        JSONObject accounts = APIClient.doGet(URL.ACCOUNTS, SignatureUtils.getSignatureParams(Signature.ACCOUNTS));
        return accounts;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:49
     * @Description 查询指定账户的余额
     */
    public JSONObject getBalanceBySpot() throws Exception {
        JSONObject balance = APIClient.doGet(URL.SPOT_BALANCE, SignatureUtils.getSignatureParams(Signature.SPOT_BALANCE));
        return balance;
    }

    /**
     * @Author lixin@wecash.net
     * @Date 2018/1/31 18:49
     * @Description 查询指定账户的余额
     */
    public JSONObject getBalanceByOtc() throws Exception {
        JSONObject balance = APIClient.doGet(URL.OTC_BALANCE, SignatureUtils.getSignatureParams(Signature.OTC_BALANCE));
        return balance;
    }

}
