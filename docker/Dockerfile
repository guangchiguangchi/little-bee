FROM tomcat:8-jre8

MAINTAINER allview <allviewit@sina.com>

# Set locales
ENV locale-gen zh_CN.UTF-8
ENV LANG zh_CN.UTF-8
ENV LC_CTYPE zh_CN.UTF-8

COPY little-bee.war /usr/local/tomcat/webapps/

EXPOSE 8080
#
# Launch Tomcat
CMD ["catalina.sh", "run"]
