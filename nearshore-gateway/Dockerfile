FROM java:8
EXPOSE 8080
ADD nearshore-gateway-0.0.1-SNAPSHOT.jar nearshore-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xmx312m","-Dspring.profiles.active=dev","-jar","nearshore-gateway-0.0.1-SNAPSHOT.jar"]