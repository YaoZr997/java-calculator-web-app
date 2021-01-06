FROM tomcat

LABEL maintainer="pinm@microsoft.com"

RUN rm -rf $CATALINA_HOME/webapps/ROOT
COPY target/java-calculator-web-app.war $CATALINA_HOME/webapps/ROOT.war