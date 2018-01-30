package lixin.huobi.transactionrobot.huobiApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.constant.Constant;
import lixin.huobi.transactionrobot.utils.HttpInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class APIClient {

    private static final Map<String, String> postHeaders = new HashMap<>();
    private static final Map<String, String> getHeaders = new HashMap<>();
    static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    static final ZoneId ZONE_GMT = ZoneId.of("Z");
    private static Logger logger = LoggerFactory.getLogger(APIClient.class);
    static {
        postHeaders.put("Content-Type", "application/json");
        getHeaders.put("Content-Type", "application/x-www-form-urlencoded");
    }

    /**
     * 创建一个有效的签名。该方法为客户端调用，将在传入的params中添加AccessKeyId、Timestamp、SignatureVersion、SignatureMethod、Signature参数。
     *
     * @param accessKey       AppKeyId.
     * @param secretKey AppKeySecret.
     * @param method       请求方法，"GET"或"POST"
     * @param host         请求域名，例如"be.huobi.com"
     * @param uri          请求路径，注意不含?以及后的参数，例如"/v1/api/info"
     * @param params       原始请求参数，以Key-Value存储，注意Value不要编码
     */
    public static void createSignature(String accessKey, String secretKey, String method, String host,
                                String uri, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(method.toUpperCase()).append('\n') // GET
                .append(host.toLowerCase()).append('\n') // Host
                .append(uri).append('\n'); // /path
        //params.remove("Signature");
        params.put("AccessKeyId", accessKey);
        params.put("SignatureVersion", Constant.SIGNATURE_VERSION);
        params.put("SignatureMethod", Constant.SIGNATURE_METHOD);
        params.put("Timestamp", gmtNow());
        // build signature:
        SortedMap<String, String> map = new TreeMap<>(params);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        // remove last '&':
        sb.deleteCharAt(sb.length() - 1);
        logger.info("最终的要进行签名计算的字符串为:"+"\n"+"{}", sb.toString());
        // sign:
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        String payload = sb.toString();
        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.getEncoder().encodeToString(hash);
        params.put("Signature", actualSign);
        logger.info("签名计算结果：{}", actualSign);
        logger.info("最终参数为：{}", params.toString());
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    /**
     * Return epoch seconds
     */
    private static long epochNow() {
        return Instant.now().getEpochSecond();
    }

    //对固定格式的时间进行URI编码
    private static String gmtNow() {
        return Instant.ofEpochSecond(epochNow()).atZone(ZONE_GMT).format(DT_FORMAT);
    }

    private static String toRequestString(Map<String, String> params) {
        return String.join("&", params.entrySet().stream().map((entry) -> {
            return entry.getKey() + "=" + urlEncode(entry.getValue());
        }).collect(Collectors.toList()));
    }

    /**
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/30 16:18
     * @param uri   相对路径
     * @param params AccessKeyId（访问密匙）、SignatureMethod（签名方法）、SignatureVersion（签名版本）、Timestamp（时间戳）
     * @param body 要传入的参数，例如order_id
     */
    public static JSONObject doPost(String uri, Map<String, String> params, String body) throws Exception {
        String url = "https://" + Constant.BASE_URL + uri + "?" + toRequestString(params);
        String responseStr = HttpInstance.post(url, postHeaders, null, body, null);
        JSONObject returnValue = JSON.parseObject(responseStr);
        return returnValue;
    }

    /**
     * @return
     * @Author lixin@wecash.net
     * @Date 2018/1/30 16:18
     * @param uri   相对路径
     * @param params AccessKeyId（访问密匙）、SignatureMethod（签名方法）、SignatureVersion（签名版本）、Timestamp（时间戳）
     */
    public static JSONObject doGet(String uri, Map<String, String> params) throws Exception {
        String url = "https://" + Constant.BASE_URL + uri + "?" + toRequestString(params);
        String responseStr = HttpInstance.get(url, getHeaders);
        JSONObject returnValue = JSON.parseObject(responseStr);
        return returnValue;
    }
}
