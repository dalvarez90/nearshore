#!/bin/sh


echo "------------------------ Instalando contenedor redis redis "------------------------
docker stop  redis .
docker rm  redis .
docker run  --name   redis    -d -p 6379:6379 redis .


echo "------------------------ Instalando contenedor eureka "------------------------
cd /home/ec2-user/portalNearshore/microservicios/eureka
docker stop  eureka .
docker rm  eureka .
docker rmi  image_eureka .
docker build -t image_eureka .
docker run  --name   eureka  -d  -p 8761:8761 image_eureka .


echo "------------------------ Instalando contenedor zuul "------------------------
cd /home/ec2-user/portalNearshore/microservicios/zuul
docker stop  zuul .
docker rm  zuul .
docker rmi  image_zuul .
docker build -t image_zuul .
docker run   --link   eureka:hostEureka    --link   phpmyadmin:hostPhpMyadmin	  --name    zuul -d -p 80:8080 image_zuul . 


echo "------------------------ Instalando contenedor mcPremiumOpenSession "------------------------
cd /home/ec2-user/portalNearshore/microservicios/appEjemplo
docker stop  app .
docker rm  app .
docker rmi  image_app .
docker build -t image_app .
docker run    --link   eureka:hostEureka   --link   redis:hostRedis   --name app  -d -p 8013:8013 image_app .


echo "------------------------ Instalando contenedor MainDB "------------------------
cd /home/ec2-user/portalNearshore/microservicios/mcBaseDatos
docker stop  mcdb .
docker rm  mcdb .
docker rmi  image_mcdb .
docker build -t image_mcdb .
docker run    --link   eureka:hostEureka   --link   mysql:hostMysql   --name mcdb  -d -p 1007:1007 image_mcdb .


echo "------------------------ Instalando contenedor nearshore-catalogsms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-catalogsms #ruta
docker stop  nscatalogsmscont . # detener contenedor
docker rm  nscatalogsmscont . # eliminar contenedor
docker rmi  nscatalogsmsimg . # eliminar imagen
docker build -t nscatalogsmsimg . # construir imagen
docker run    --link   eureka:hostEureka  --name nscatalogsmscont  -d -p 7701:7701 nscatalogsmsimg .


echo "------------------------ Instalando contenedor nearshore-appsms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-appsms #ruta
docker stop  nsappsmscont . # detener contenedor
docker rm  nsappsmscont . # eliminar contenedor
docker rmi  nsappsmsimg . # eliminar imagen
docker build -t nsappsmsimg . # construir imagen
docker run    --link   eureka:hostEureka  --name nsappsmscont  -d -p 7702:7702 nsappsmsimg .


echo "------------------------ Instalando contenedor nearshore-employeesms "------------
cd /home/ec2-user/portalNearshore/microservicios/nearshore-employeesms #ruta
docker stop  nsemployeesmscont . # detener contenedor
docker rm  nsemployeesmscont . # eliminar contenedor
docker rmi  nsemployeesmsimg . # eliminar imagen
docker build -t nsemployeesmsimg . # construir imagen
docker run    --link   eureka:hostEureka  --name nsemployeesmscont  -d -p 7703:7703 nsemployeesmsimg .


|exec /bin/bash
---------------------------------------

docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password --volume=/root/docker/mysql/conf.d:/etc/mysql/conf.d -d mysql
docker run -d --link mysql:mysql --name phpmyadmin -e MYSQL_USERNAME=password  -p 8081:80 corbinu/docker-phpmyadmin
docker run  -p 8183:80 --name phpmyadmin  -e MYSQL_HOST=mysqlhost -e NGINX_SERVERNAME=localhost -d docker-phpmyadmin /sbin/my_init --enable-insecure-key