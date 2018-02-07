package lixin.huobi.transactionrobot.utils;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.constant.Signature;
import lixin.huobi.transactionrobot.constant.URL;
import lixin.huobi.transactionrobot.huobiApi.APIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignatureUtils {
    public static Map<String, Map<String, String>> signatureMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(SignatureUtils.class);
    public static Map<String, String> spotParams = new HashMap<>();
    public static Map<String, String> otcParams = new HashMap<>();
    static {
        spotParams.put("account-id", Constant.SPOT_ACCOUNT);
        signatureMap.put(Signature.ALL_SYMBOLS, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.SYMBOLS, new HashMap<>()));
        signatureMap.put(Signature.ALL_CURRENCYS, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.CURRENCYS, new HashMap<>()));
        signatureMap.put(Signature.CURRENT_TIME, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.TIME_STAMP, new HashMap<>()));
        signatureMap.put(Signature.ACCOUNTS, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.ACCOUNTS, new HashMap<>()));
        signatureMap.put(Signature.SPOT_BALANCE, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.SPOT_BALANCE, spotParams));
        otcParams.put("account-id", Constant.OTC_ACCOUNT);
        signatureMap.put(Signature.OTC_BALANCE, APIClient.createSignature(Constant.ACCESS_KEY, Constant.SECRET_KEY, Constant.GET, Constant.BASE_URL, URL.OTC_BALANCE, otcParams));
    }

    public static Map<String, String> getSignatureParams(String key){
        return signatureMap.get(key);
    }
}
