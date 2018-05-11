package com.iexec.poco.api.controller;

import com.iexec.common.model.AppModel;
import com.iexec.common.model.DatasetModel;
import com.iexec.common.model.ModelService;
import com.iexec.common.model.WorkOrderModel;
import com.iexec.scheduler.actuator.ActuatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class ModelController {

    private static final Logger log = LoggerFactory.getLogger(ModelController.class);
    private final static ActuatorService actuatorService = ActuatorService.getInstance();

    //http://localhost:3030/api/workorder/0x
    @RequestMapping(value = "/workorders/{workOrderId}")
    public WorkOrderModel getWorkOrderModel(@PathVariable("workOrderId") String workOrderId) {
        return ModelService.getInstance().getWorkOrderModel(workOrderId);
    }

    //http://localhost:3030/api/app/0x
    @RequestMapping(value = "/apps/{appId}")
    public AppModel getAppModel(@PathVariable("appId") String appId) {
        return ModelService.getInstance().getAppModel(appId);
    }

    //http://localhost:3030/api/dataset/0x
    @RequestMapping(value = "/datasets/{datasetId}")
    public DatasetModel getDatasetModel(@PathVariable("datasetId") String datasetId) {
        return ModelService.getInstance().getDatasetModel(datasetId);
    }

    //http://localhost:3030/api/worker/0x/score
    @RequestMapping(value = "/worker/{workerAddress}/score")
    public BigInteger getWorkerScore(@PathVariable("workerAddress") String workerAddress) {
        return actuatorService.getWorkerScore(workerAddress);
    }

}