package com.iexec.poco.api.controller;

import com.iexec.common.model.AppModel;
import com.iexec.common.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    //http://localhost:3030/api/app/0x
    @GetMapping(value = "/apps/{appId}")
    public AppModel getAppModel(@PathVariable("appId") String appId) {
        return ModelService.getInstance().getAppModel(appId);
    }

}