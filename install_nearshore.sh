#!/bin/sh

echo "------------------------ Instalando contenedor redis "------------------------
docker stop nsrediscont .
docker rm nsrediscont .
docker run --name nsrediscont -d -p 6379:6379 redis:latest


echo "------------------------ Instalando contenedor eureka "------------------------
cd /home/ec2-user/portalNearshore/microservicios/eureka
docker stop eureka .
docker rm eureka .
docker rmi image_eureka .
docker build -t image_eureka .
docker run --name eureka -d -p 8761:8761 image_eureka .


echo "------------------------ Instalando contenedor nsgateway "------------------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-gateway
docker stop nsgatewaycont .
docker rm nsgatewaycont .
docker rmi nsgatewayimg .
docker build -t nsgatewayimg .
docker run --name nsgatewaycont --link eureka:hostEureka --link nsrediscont:hostRedis --link phpmyadmin:hostPhpMyAdmin -d -p 80:8080 nsgatewayimg .


echo "------------------------ Instalando contenedor MainDB "------------------------
cd /home/ec2-user/portalNearshore/microservicios/mcBaseDatos
docker stop mcdb .
docker rm mcdb .
docker rmi image_mcdb .
docker build -t image_mcdb .
docker run --link eureka:hostEureka --link mysql:hostMysql --name mcdb -d -p 1007:1007 image_mcdb .


echo "------------------------ Instalando contenedor nearshore-catalogsms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-catalogsms
docker stop nscatalogsmscont .
docker rm nscatalogsmscont .
docker rmi nscatalogsmsimg .
docker build -t nscatalogsmsimg .
docker run --link eureka:hostEureka --name nscatalogsmscont -d -p 7701:7701 nscatalogsmsimg .


echo "------------------------ Instalando contenedor nearshore-appsms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-appsms
docker stop nsappsmscont .
docker rm nsappsmscont .
docker rmi nsappsmsimg . 
docker build -t nsappsmsimg .
docker run --link eureka:hostEureka --name nsappsmscont -d -p 7702:7702 nsappsmsimg .


echo "------------------------ Instalando contenedor nearshore-employeesms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-employeesms
docker stop  nsemployeesmscont .
docker rm  nsemployeesmscont .
docker rmi  nsemployeesmsimg .
docker build -t nsemployeesmsimg .
docker run --link eureka:hostEureka --name nsemployeesmscont -d -p 7703:7703 nsemployeesmsimg .


exec /bin/bash
---------------------------------------

docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password --volume=/root/docker/mysql/conf.d:/etc/mysql/conf.d -d mysql
docker run -d --link mysql:mysql --name phpmyadmin -e MYSQL_USERNAME=password  -p 8081:80 corbinu/docker-phpmyadmin
docker run  -p 8183:80 --name phpmyadmin  -e MYSQL_HOST=mysqlhost -e NGINX_SERVERNAME=localhost -d docker-phpmyadmin /sbin/my_init --enable-insecure-key
