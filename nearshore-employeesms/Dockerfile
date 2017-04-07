FROM java:8
EXPOSE 7703
ADD nearshore-employeesms-0.0.1-SNAPSHOT.jar nearshore-employeesms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xmx312m","-Dspring.profiles.active=dev","-jar","nearshore-employeesms-0.0.1-SNAPSHOT.jar"]