FROM openjdk:8
WORKDIR /
ADD build/libs/iexec-poco-api.jar iexec-poco-api.jar
EXPOSE 3030
CMD [“java”,“-jar”,“iexec-poco-api.jar”]

#docker run -v `pwd`/src/main/resources/:/src/main/resources/ iexec-poco-api-image
