package com.iexec.poco.api.controller;

import com.iexec.common.model.ConsensusModel;
import com.iexec.scheduler.actuator.ActuatorService;
import com.iexec.scheduler.workerpool.WorkerPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class WorkerController {

    private static final Logger log = LoggerFactory.getLogger(WorkerController.class);
    private final static ActuatorService actuatorService = ActuatorService.getInstance();

    //http://localhost:3030/api/worker/0x/score
    @GetMapping(value = "/workers/{workerAddress}/score")
    public BigInteger getWorkerScore(@PathVariable(value = "workerAddress") String workerAddress) {
        return actuatorService.getWorkerScore(workerAddress);
    }

    @GetMapping(value = "/workers/{workerAddress}/workorders/{workOrderId}/consensus")
    public ConsensusModel getConsensusByWorkOrderId(@PathVariable("workerAddress") String workerAddress, @PathVariable("workOrderId") String workOrderId) {
        return WorkerPoolService.getInstance().getConsensusModelByWorkOrderId(workOrderId);
    }


}