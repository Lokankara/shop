FROM debian:buster-slim
RUN apt-get update && apt-get install -y nginx  && rm -rf /var/lib/apt/lists/*
EXPOSE 80
COPY custom.conf /etc/nginx/conf.d/default.conf
CMD ["nginx","-g","daemon off;"]

FROM tomcat:8-jre11
RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh
COPY shop-1.0-SNAPSHOT.jar /usr/local/tomcat/webapps/products.war
CMD ["catalina.sh", "run"]