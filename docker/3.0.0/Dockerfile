FROM tomcat:8-jre8

MAINTAINER DesignMyApp <designmyapp@ebusinessinformation.fr>

RUN apt-get update && apt-get install -y zip
RUN rm -r /usr/local/tomcat/webapps/*

COPY mygallery.war /usr/local/tomcat/webapps/ROOT.war
COPY data /data
COPY dump.sh /dma/migration/