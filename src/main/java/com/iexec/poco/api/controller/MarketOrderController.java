package com.iexec.poco.api.controller;

import com.iexec.common.model.MarketOrderModel;
import com.iexec.scheduler.actuator.ActuatorService;
import com.iexec.scheduler.marketplace.MarketplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/marketorders")
public class MarketOrderController {

    private static final Logger log = LoggerFactory.getLogger(MarketOrderController.class);

    //http://localhost:3030/api/marketorder/1
    @GetMapping(value = "/{marketorderIdx}")
    public MarketOrderModel getMarketOrderModel(@PathVariable("marketorderIdx") BigInteger marketorderIdx) {
        return MarketplaceService.getInstance().getMarketOrderModel(marketorderIdx);
    }

    //http://localhost:3030/api/marketorders/count
    @GetMapping(value = "/count")
    public BigInteger getWorkerScore() {
        return MarketplaceService.getInstance().getMarketOrderCount();
    }


}