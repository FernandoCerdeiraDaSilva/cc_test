FROM tomcat:8.5.49-jdk8

LABEL maintainer="FernandoCerdeira@gmail.com"
LABEL version=1.1
LABEL description="Clear Channel - Tomcat8"

WORKDIR /usr/local/tomcat/webapps

COPY ./cc_rest_provider/target/cc_rest_provider-1.war /usr/local/tomcat/webapps
RUN chmod +x /usr/local/tomcat/webapps/cc_rest_provider-1.war
RUN mv /usr/local/tomcat/webapps/cc_rest_provider-1.war /usr/local/tomcat/webapps/cc_test.war

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
