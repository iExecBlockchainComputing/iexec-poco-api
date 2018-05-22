package com.iexec.poco.api.controller;

import com.iexec.common.model.ModelService;
import com.iexec.common.model.WorkOrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkOrderController {

    private static final Logger log = LoggerFactory.getLogger(WorkOrderController.class);

    //http://localhost:3030/api/workorder/0x
    @GetMapping(value = "/workorders/{workOrderId}")
    public WorkOrderModel getWorkOrderModel(@PathVariable("workOrderId") String workOrderId) {
        return ModelService.getInstance().getWorkOrderModel(workOrderId);
    }

}