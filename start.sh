#!/bin/sh

iexecSchedulerYmlFile=/src/main/resources/iexec-scheduler.yml

sed -i "s/path:.*/path: \/wallet\/wallet_scheduler.json/g"   $iexecSchedulerYmlFile
sed -i "s/password:.*/password: \"$WALLETPASSWORD\"/g"              $iexecSchedulerYmlFile
sed -i "s/clientAddress:.*/clientAddress: $ETHNODE/g"               $iexecSchedulerYmlFile
sed -i "s/rlcAddress:.*/rlcAddress: $RLCCONTRACT/g"                 $iexecSchedulerYmlFile
sed -i "s/iexecHubAddress:.*/iexecHubAddress: $IEXECHUBCONTRACT/g"  $iexecSchedulerYmlFile
sed -i "s/address:.*/address: $WORKERPOOLADDRESS/g"  				        $iexecSchedulerYmlFile

java -jar iexec-poco-api.jar
