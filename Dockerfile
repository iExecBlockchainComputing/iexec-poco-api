FROM openjdk:8
WORKDIR /
ADD build/libs/iexec-poco-api.jar /iexec-poco-api.jar
ADD src/main/resources/application.yml /application.yml
ADD src/main/resources/iexec-scheduler.yml /src/main/resources/iexec-scheduler.yml
ADD start.sh /start.sh
EXPOSE 3030

ENTRYPOINT ["/start.sh"]

#docker run -it -v $(pwd)/src/main/resources/wallet/wallet_visualizer.json:/wallet/wallet_visualizer.json -e WALLETPASSWORD='whatever' -e ETHNODE='http:\/\/172.17.0.2:8545' -e RLCCONTRACT='0x091233035dcb12ae5a4a4b7fb144d3c5189892e1' -e IEXECHUBCONTRACT='0xc4e4a08bf4c6fd11028b714038846006e27d7be8' -e WORKERPOOLADDRESS='0x597fa45586a1f4879605c0b8c04c4100a918ee0d' --net docker_iexec-net -p 3030:3030 --name iexec-poco-api iexec-poco-api
 