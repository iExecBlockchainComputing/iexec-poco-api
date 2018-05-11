package com.iexec.poco.api.controller;

import com.iexec.scheduler.actuator.ActuatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

@RestController
@RequestMapping("/contribution")
public class ContributionController {

    private static final Logger log = LoggerFactory.getLogger(ContributionController.class);
    private final static ActuatorService actuatorService = ActuatorService.getInstance();


    @RequestMapping(value = "/contributions/count")
    public Tuple2<BigInteger, BigInteger> getContributionHistory() {
        return actuatorService.getContributionHistory();
    }

    @RequestMapping(value = "/successcontributions/count")
    public BigInteger getSuccessContributionHistory() {
        return actuatorService.getSuccessContributionHistory();
    }

    @RequestMapping(value = "/failedcontributions/count")
    public BigInteger getFailledContributionHistory() {
        return actuatorService.getFailledContributionHistory();
    }

/*
    //http://localhost:3030/api/canICallRevealConsensus?workOrderId=1
    @RequestMapping(value="/canICallRevealConsensus")
    public boolean getMarketOrderTrust(@RequestParam("workOrderId") String workOrderId) {
        return contributionService.canICallRevealConsensus(workOrderId);
    }
*/


}