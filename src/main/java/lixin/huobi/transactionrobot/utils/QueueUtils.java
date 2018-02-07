package lixin.huobi.transactionrobot.utils;

import lixin.huobi.transactionrobot.Exception.WrongAreaException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueUtils {
    private static final BlockingQueue<String> btcQueue;
    private static final BlockingQueue<String> ethQueue;
    private static final BlockingQueue<String> usdtQueue;
    static {
        btcQueue = new LinkedBlockingQueue<>();
        ethQueue = new LinkedBlockingQueue<>();
        usdtQueue = new LinkedBlockingQueue<>();
    }

    public static BlockingQueue<String> getBtcQueue() {
        return btcQueue;
    }
    public static BlockingQueue<String> getEthQueue() {
        return ethQueue;
    }
    public static BlockingQueue<String> getUsdtQueue() {
        return usdtQueue;
    }

    public static BlockingQueue<String> getHuobiQueue(String area) throws WrongAreaException {
        switch (area.toLowerCase().trim()) {
            case "btc":
                return btcQueue;
            case "eth":
                return ethQueue;
            case "usdt":
                return usdtQueue;
            default:
                throw new WrongAreaException("传入交易区域参数错误：" + area);
        }
    }
}
