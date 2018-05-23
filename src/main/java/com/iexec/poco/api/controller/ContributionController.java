package com.iexec.poco.api.controller;

import com.iexec.common.model.ContributionModel;
import com.iexec.common.model.StateHistoryModel;
import com.iexec.scheduler.iexechub.IexecHubService;
import com.iexec.scheduler.workerpool.WorkerPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContributionController {

    private static final Logger log = LoggerFactory.getLogger(ContributionController.class);

    @GetMapping(value = "/contributions/history")
    public StateHistoryModel getContributionHistory() {
        return IexecHubService.getInstance().getContributionHistory();
    }

    //http://localhost:3030/api/workorders/{workOrderId}/contribution?worker=0x
    @GetMapping(value = "/workorders/{workOrderId}/contribution")
    public ContributionModel getWorkerContributionModelByWorkOrderId(@PathVariable("workOrderId") String workOrderId, @RequestParam("worker") String worker) {
        return WorkerPoolService.getInstance().getWorkerContributionModelByWorkOrderId(workOrderId, worker);
    }


    //http://localhost:3030/api/canICallRevealConsensus?workOrderId=1
    /*@RequestMapping(value="/canICallRevealConsensus")
    public boolean getMarketOrderTrust(@RequestParam("workOrderId") String workOrderId) {
        return contributionService.canICallRevealConsensus(workOrderId);
    }*/


}