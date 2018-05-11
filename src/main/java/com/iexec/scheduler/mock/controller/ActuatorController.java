package com.iexec.scheduler.mock.controller;

import com.iexec.common.contracts.generated.IexecHub;
import com.iexec.common.ethereum.CommonConfiguration;
import com.iexec.common.ethereum.CredentialsService;
import com.iexec.common.ethereum.IexecConfigurationService;
import com.iexec.scheduler.actuator.ActuatorService;
import com.iexec.scheduler.iexechub.IexecHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;

import static com.iexec.common.ethereum.Utils.getTransactionStatusFromEvents;

@RestController
public class ActuatorController {

    private static final Logger log = LoggerFactory.getLogger(ActuatorController.class);
    private final static ActuatorService actuatorService = ActuatorService.getInstance();
    private final static IexecHubService iexecHubService = IexecHubService.getInstance();
    private final CommonConfiguration configuration = IexecConfigurationService.getInstance().getCommonConfiguration();


    //http://localhost:3030/api/createApp
    //in browser = 0xc459f24cbeea4a3122af71d21ce02d28ed38a802
    //in log : CreateApp [address:0xc459f24cbeea4a3122af71d21ce02d28ed38a802]
    @RequestMapping(value = "/createApp")
    public String createApp(@RequestParam(value = "appName", defaultValue = "EchoInDocker") String appName,
                            @RequestParam(value = "appPrice", defaultValue = "0") BigInteger appPrice,
                            @RequestParam(value = "appParams", defaultValue = "{type: 'DOCKER', envvars: 'XWDOCKERIMAGE=ubuntu'}") String appParams) {
        String appAddress = null;

        try {
            TransactionReceipt createAppReceipt = iexecHubService.getIexecHub().createApp(appName, appPrice, appParams).send();
            List<IexecHub.CreateAppEventResponse> createAppEvents = iexecHubService.getIexecHub().getCreateAppEvents(createAppReceipt);
            log.info("CreateApp [appName:{}, appPrice:{}, appParams:{}, transactionStatus:{}] ",
                    appName, appPrice, appParams, getTransactionStatusFromEvents(createAppEvents));
            if (createAppEvents.size() > 0) {
                appAddress = createAppEvents.get(0).app;
                log.info("App created [address:{}] ", appAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appAddress;
    }

    //http://localhost:3030/api/createMarketOrder?category=1&trust=0&value=0&volume=1
    //in browser = 1
    //in log: Received marketOrderCreatedEvent [marketorderIdx:1]
    @RequestMapping(value = "/createMarketOrder")
    public BigInteger createMarketOrder(@RequestParam("category") BigInteger category, @RequestParam("trust") BigInteger trust, @RequestParam("value") BigInteger value, @RequestParam("volume") BigInteger volume) {
        return actuatorService.createMarketOrder(category, trust, value, volume);
    }

    //http://localhost:3030/api/buyForWorkOrder?marketorderIdx=1&&app=0xc459f24cbeea4a3122af71d21ce02d28ed38a802&params=echo%20Hello
    // in browser = 0xffe9d4aa247e7e0615b24503981193486a60470f
    // in log = WorkOrder created : [woid:0xffe9d4aa247e7e0615b24503981193486a60470f]
    @RequestMapping(value = "/buyForWorkOrder")
    public String buyForWorkOrder(@RequestParam("marketorderIdx") BigInteger marketorderIdx, @RequestParam("app") String app, @RequestParam("params") String params) {
        String woid = null;
        String workerPoolAddress = configuration.getContractConfig().getWorkerPoolConfig().getAddress();
        String dataset = "0x0000000000000000000000000000000000000000";
        String callback = "0x0000000000000000000000000000000000000000";
        String beneficiary = CredentialsService.getInstance().getCredentials().getAddress();
        try {
            TransactionReceipt buyForWorkOrderReceipt = iexecHubService.getIexecHub().buyForWorkOrder(
                    marketorderIdx,
                    workerPoolAddress,
                    app,
                    dataset,
                    params,
                    callback,
                    beneficiary
            ).send();
            List<IexecHub.WorkOrderActivatedEventResponse> workOrderActivatedEvents = iexecHubService.getIexecHub().getWorkOrderActivatedEvents(buyForWorkOrderReceipt);
            log.info("BuyForWorkOrder [marketorderIdx:{}, workerPoolAddress:{}, app:{}, dataset:{}, " +
                            "params:{}, callback:{}, beneficiary:{}, transactionStatus:{}] ",
                    marketorderIdx,
                    workerPoolAddress,
                    app,
                    dataset,
                    params,
                    callback,
                    beneficiary,
                    getTransactionStatusFromEvents(workOrderActivatedEvents)
            );
            if (workOrderActivatedEvents.size() > 0) {
                woid = workOrderActivatedEvents.get(0).woid;
                log.info("WorkOrder created [woid:{}] ", woid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return woid;
    }

}