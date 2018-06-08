package com.iexec.poco.api.controller;

import com.iexec.common.model.ConsensusModel;
import com.iexec.common.model.ModelService;
import com.iexec.common.model.WorkOrderModel;
import com.iexec.scheduler.workerpool.WorkerPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsensusController {

    private static final Logger log = LoggerFactory.getLogger(ConsensusController.class);

    @GetMapping(value = "/workorders/{workOrderId}/consensus")
    public ConsensusModel getConsensusByWorkOrderId(@PathVariable("workOrderId") String workOrderId) {
        return WorkerPoolService.getInstance().getConsensusModelByWorkOrderId(workOrderId);
    }

}