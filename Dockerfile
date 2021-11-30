FROM openjdk:11
VOLUME /tmp
ADD build/libs/*.jar app1.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app1.jar"]