package lixin.huobi.transactionrobot.start;

import lixin.huobi.transactionrobot.task.NewCoinEasyTask;
import org.springframework.stereotype.Component;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jodan on 2018/2/7.
 */
@Component
public class StartThread {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);
    static {
        for(int i=1;i<=3;i++) {
            threadPool.execute(new NewCoinEasyTask("btc"));
        }
    }
}
