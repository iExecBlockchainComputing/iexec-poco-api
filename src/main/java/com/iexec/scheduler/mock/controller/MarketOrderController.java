package com.iexec.scheduler.mock.controller;

import com.iexec.common.model.*;
import com.iexec.scheduler.actuator.ActuatorService;
import com.iexec.scheduler.marketplace.MarketplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/marketorders")
public class MarketOrderController {

    private static final Logger log = LoggerFactory.getLogger(MarketOrderController.class);
    private final static ActuatorService actuatorService = ActuatorService.getInstance();


    //http://localhost:3030/api/marketorder/1
    @RequestMapping(value = "/{marketorderIdx}")
    public MarketOrderModel getMarketOrderModel(@PathVariable("marketorderIdx")  BigInteger marketorderIdx) {
        return MarketplaceService.getInstance().getMarketOrderModel(marketorderIdx);
    }

    //http://localhost:3030/api/marketorders/count
    @RequestMapping(value = "/count")
    public BigInteger getWorkerScore() {
        return actuatorService.getMarketOrdersCount();
    }


    /* Useless -> call GET /marketorders/1

    //http://localhost:3030/api/existingMarketOrder?marketorderIdx=1
    @RequestMapping(value = "/existingMarketOrder")
    public Boolean existingMarketOrder(@RequestParam("marketorderIdx") BigInteger marketorderIdx) {
        return actuatorService.existingMarketOrder(marketorderIdx);
    }

    //Useless -> infos in GET /marketorders/1

    //http://localhost:3030/api/getMarketOrderValue?marketorderIdx=1
    @RequestMapping(value = "/getMarketOrderValue")
    public BigInteger getMarketOrderValue(@RequestParam("marketorderIdx") BigInteger marketorderIdx) {
        return actuatorService.getMarketOrderValue(marketorderIdx);
    }

    //http://localhost:3030/api/getMarketOrderCategory?marketorderIdx=1
    @RequestMapping(value = "/getMarketOrderCategory")
    public BigInteger getMarketOrderCategory(@RequestParam("marketorderIdx") BigInteger marketorderIdx) {
        return actuatorService.getMarketOrderCategory(marketorderIdx);
    }

    //http://localhost:3030/api/getMarketOrderTrust?marketorderIdx=1
    @RequestMapping(value = "/getMarketOrderTrust")
    public BigInteger getMarketOrderTrust(@RequestParam("marketorderIdx") BigInteger marketorderIdx) {
        return actuatorService.getMarketOrderTrust(marketorderIdx);
    }

    */


}