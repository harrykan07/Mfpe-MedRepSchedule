FROM openjdk:11
EXPOSE 8081
ADD target/rep-schedule.jar rep-schedule.jar
ENTRYPOINT ["java","-jar","/rep-schedule.jar"]
