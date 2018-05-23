package com.iexec.poco.api.controller;

import com.iexec.common.model.DatasetModel;
import com.iexec.common.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatasetController {

    private static final Logger log = LoggerFactory.getLogger(DatasetController.class);

    //http://localhost:3030/api/dataset/0x
    @GetMapping(value = "/datasets/{datasetId}")
    public DatasetModel getDatasetModel(@PathVariable("datasetId") String datasetId) {
        return ModelService.getInstance().getDatasetModel(datasetId);
    }

}