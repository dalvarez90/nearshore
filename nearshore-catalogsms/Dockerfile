FROM java:8
EXPOSE 7701
ADD nearshore-catalogsms-0.0.1-SNAPSHOT.jar nearshore-catalogsms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xmx312m","-Dspring.profiles.active=dev","-jar","nearshore-catalogsms-0.0.1-SNAPSHOT.jar"]