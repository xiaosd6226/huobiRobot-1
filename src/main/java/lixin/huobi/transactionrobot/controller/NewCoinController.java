package lixin.huobi.transactionrobot.controller;

import com.alibaba.fastjson.JSONObject;
import lixin.huobi.transactionrobot.service.AssetService;
import lixin.huobi.transactionrobot.service.MarketService;
import lixin.huobi.transactionrobot.service.PublicService;
import lixin.huobi.transactionrobot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jodan on 2018/2/10.
 */
@RestController
@RequestMapping(value="newCoin")
public class NewCoinController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private TradeService tradeService;

    @PostMapping(value = "start/{sybmol}")
    public JSONObject getNewCoinInfo(@PathVariable("sybmol") String sybmol) {
        return null;
    }
}
