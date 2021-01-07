# A Java Calculator Web Application
[![Build status](https://dev.azure.com/maping930883/java-calculator-web-app/_apis/build/status/java-calculator-web-app-Maven-CI%26CD)](https://dev.azure.com/maping930883/java-calculator-web-app/_build/latest?definitionId=32)

Maven default lifecycle phases:
validate->compile->test->package->integration-test->verify->install->deploy

## 1. Create a repo manually

### 1.1 Create a repo on GitHub
Click "Repositories",then click "New" button,input "java-calculator-web-app", leave all other input as default, click "Create repository".

### 1.2 Create a Jersey REST web app by Maven archetype
```console
$ cd /mnt/c/Users/pinm/code/maven/
$ mvn archetype:generate 
.......
Choose archetype:
.......
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1720: jersey-quickstart
Choose archetype:
1: remote -> com.sun.jersey.archetypes:jersey-quickstart-ejb (An archetype which contains a simple Jersey based EJB project.)
2: remote -> com.sun.jersey.archetypes:jersey-quickstart-grizzly (An archetype which contains a quickstart Jersey project based on Grizzly container.)
3: remote -> com.sun.jersey.archetypes:jersey-quickstart-grizzly2 (An archetype which contains a quickstart Jersey project based on Grizzly2 container.)
4: remote -> com.sun.jersey.archetypes:jersey-quickstart-webapp (An archetype which contains a sample Jersey based Webapp project.)
5: remote -> org.glassfish.jersey.archetypes:jersey-quickstart-grizzly2 (An archetype which contains a quick start Jersey project based on Grizzly container.)
6: remote -> org.glassfish.jersey.archetypes:jersey-quickstart-webapp (An archetype which contains a quick start Jersey-based web application project.)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : org.glassfish.jersey.archetypes:jersey-quickstart-webapp

1: remote -> org.glassfish.jersey.archetypes:jersey-quickstart-webapp (An archetype which contains a quick start Jersey-based web application project.)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
......
92: 3.0.0
Choose a number: 92:
Define value for property 'groupId': com.mwit.calculator
Define value for property 'artifactId': java-calculator-web-app
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' com.mwit.calculator: :
Confirm properties configuration:
groupId: com.mwit.calculator
artifactId: java-calculator-web-app
version: 1.0-SNAPSHOT
package: com.mwit.calculator
 Y: :
```

### 1.3 Init repo 
```console
$ cd /mnt/c/Users/pinm/code/maven/java-calculator-web-app
$ code README.md
$ git init
$ git add -A
$ git commit -m "add java maven jersey archetype web app"
$ git remote add origin https://github.com/maping/java-calculator-web-app.git 
$ git push -u origin master
```

## 2. Build
```console
$ mvn clean package
```
>Explain: -DskipTests, not execute test case, but compile test case; -Dmaven.test.skip=true，not compile test case, neither execute test case.

## 3. Run Integration Test
```console
$ mvn clean integration-test
```

## 4. Create a Maven CI&CD pipeline (Develop Environment)

## 5. Generate Site
```console
$ mvn site
```

## 6. Deploy Site to a Tomcat Server
Create webdav application
```console
$ cd %CATALINA_HOME%\webapps\
$ mkdir webdav && cd webdav
$ vim web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>
    <servlet>
        <servlet-name>webdav</servlet-name>
        <servlet-class>org.apache.catalina.servlets.WebdavServlet</servlet-class>
        <init-param>
            <param-name>debug</param-name>
            <param-value>0</param-value>
        </init-param>
        <init-param>
            <param-name>listings</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>readonly</param-name>
            <param-value>false</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>webdav</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
```
Add server section in ~/.m2/settings.xml
```console
$ vim ~/.m2/settings.xml
    <server>
      <id>tomcatserver</id>
      <username>********</username>
      <password>********</password>
    </server>
```
Start local Tomcat server
```console
$ cd %CATALINA_HOME%\bin\
$ catalina.bat start
```
Deploy site
```console
$ cd /mnt/c/Users/pinm/code/maven/java-calculator-web-app
$ mvn site:deploy
[INFO] --- maven-site-plugin:3.9.1:deploy (default-cli) @ java-calculator-web-app ---
WAGON_VERSION: 1.0-beta-2
[INFO] Pushing C:\Users\pinm\code\github\java-calculator-web-app\target\site
[INFO]    >>> to http://127.0.0.1:8080/webdav/./
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.530 s
[INFO] Finished at: 2021-01-03T10:40:20+08:00
[INFO] ------------------------------------------------------------------------
```
Visit URL: http://localhost:8080/webdav/index.html

## 7. Create a Multi-stage Release pipeline (Test -> Product Envirnonment） 

## 8. Scan with SonarQube 8.6
Modify ~/.m2/setting.xml
```console
$ mvn clean verify sonar:sonar -Dsonar.login=*************************
.......
[INFO] ANALYSIS SUCCESSFUL, you can browse http://40.115.187.227:9000/dashboard?id=com.mwit.calculator%3Ajava-calculator-web-app
[INFO] Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
[INFO] More about the report processing at http://40.115.187.227:9000/api/ce/task?id=AXbLbS28QY5b3LULUBiZ
[INFO] Analysis total time: 13.814 s
......
```
>Attention: Add Sonar Tasks in your Azure Pipeline, need SonarQube Develop Edition.

## 9. Containerize
Create Dockerfile
```console
$ vim Dockerfile
FROM tomcat

LABEL maintainer="pinm@microsoft.com"

RUN rm -rf $CATALINA_HOME/webapps/ROOT
COPY target/java-calculator-web-app.war $CATALINA_HOME/webapps/ROOT.war
```
Build & Run Docker Image
```console
$ docker build -t java-calculator-web-app .
$ docker images
REPOSITORY                TAG       IMAGE ID       CREATED         SIZE
java-calculator-web-app   latest    32e00035171c   19 hours ago    654MB
$ docker run --rm -p 8181:8080 java-calculator-web-app
......
05-Jan-2021 07:47:30.999 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/usr/local/tomcat/webapps/ROOT.war] has finished in [1,714] ms
05-Jan-2021 07:47:31.014 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
05-Jan-2021 07:47:31.036 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [1844] milliseconds
```
Visist Container Application
```console
$ curl http://localhost:8181/rest/calculator/ping
Welcome to Java Calculator Web App!

Tue Jan 05 07:50:04 UTC 2021
```
>Explain: --rm means delete the container after stopping it.

Press Control-C to stop and remove the container.

Push Docker Image to Docker Hub
```console
$ docker tag java-calculator-web-app:latest maping930883/java-calculator-web-app:latest
$ docker login -u ******* -p *******
$ docker push maping930883/java-calculator-web-app:latest
```

## 10. Deploy to K8S
Create namespace
```console
$ kubectl create namespace dev
namespace/dev created
```
Create secret by command line
```console
$ kubectl create secret docker-registry maping930883secret --docker-username=********* --docker-password=********* --docker-email=********* -n dev
secret/maping930883secret created
```
Or you can create secret by YAML file
```console
$ cat ~/.docker/config.json | base64
$ kubectl apply -f registry-pull-secret.yml -n dev
secret/maping930883secret created
```
Create deployment
```console
$ kubectl apply -f deployment.yml -n dev
deployment.apps/java-calculator-web-app created
$ kubectl get deployment -n dev
NAME                      READY   UP-TO-DATE   AVAILABLE   AGE
java-calculator-web-app   1/1     1            1           45s
```
If deploy failed，you can delete deployment
```console
$ kubectl delete -f deployment.yml -n dev
deployment.apps "java-calculator-web-app" deleted
```
Debug pod
```console
$ kubectl get pod -n dev
NAME                                       READY   STATUS    RESTARTS   AGE
java-calculator-web-app-5747ddc86b-fpqdj   1/1     Running   0          80s
$ kubectl exec -it java-calculator-web-app-5747ddc86b-fpqdj -n dev -- bash 
root@java-calculator-web-app-5747ddc86b-fpqdj:/usr/local/tomcat# curl localhost:8080/rest/calculator/ping
Welcome to Java Calculator Web App!

Wed Jan 06 10:26:20 UTC 2021
root@java-calculator-web-app-5747ddc86b-fpqdj:/usr/local/tomcat# exit
$ kubectl logs java-calculator-web-app-5747ddc86b-fpqdj -n dev
```
Deploy service
```console
$ kubectl apply -f service.yml -n dev
service/java-calculator-web-app created
$ kubectl get svc -n dev
NAME                      TYPE           CLUSTER-IP     EXTERNAL-IP    PORT(S)        AGE
java-calculator-web-app   LoadBalancer   10.0.161.183   20.39.169.44   80:32326/TCP   46s
```
If deploy failed，you can delete service
```console
$ kubectl delete -f service.yml -n dev
service "java-calculator-web-app" deleted
```
Visit http://20.39.169.44/rest/calculator/ping

Delete namespace
```console
$ kubectl delete namespace dev
namespace "dev" deleted
```

## 11. Delete repo
Click repo "maping/java-calculator-web-app", then click "Settings", then drop down to "Danger Zone", click "Delete this repository".

## Reference
1. [REST with Java (JAX-RS) using Jersey - Tutorial](https://www.vogella.com/tutorials/REST/article.html)
2. https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference
3. https://checkstyle.sourceforge.io/index.html
4. https://www.cnblogs.com/panie2015/p/5737468.html
5. https://www.cnblogs.com/fnlingnzb-learner/p/10637802.html
6. https://blog.csdn.net/iteye_7914/article/details/82450088
7. https://maven.apache.org/plugins/maven-site-plugin/examples/adding-deploy-protocol.html
8. https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/
9. https://blog.csdn.net/alinyua/article/details/83244983
10. https://blog.csdn.net/alinyua/article/details/83267934
11. [Azure DevOps integrate with SonarQube](https://www.dazhuanlan.com/2019/11/14/5dccb299e8052/?__cf_chl_jschl_tk__=c53175aab4cc0af8ec9a91f745915bf0727370af-1609725695-0-AaRmYwsuupPLQMykoiqWBZVaMcpc17du_AQbah7jAzcCykb8eGGFmhv8vI9J3WxnKc9ZhW-BKZ2sUTw3brEYMB4DuBxQEEAPNGhnbJGmp-dSpjIzpTPIebcdFMl7ImmQGEBWq3CEn-gioECn00ikH9idpje8Hr6_RmnaDpzkFTSykpqk1CaRP62tDxEqLSh_cQDGmfHFUQQScKrbkkvWBtFHImG0bGaDqfTGAinuxA_eIXHh2xaBgVgwJPB3jLsFvimTWhDTwAfH6pSERAZrbvFW4sdA_tSlJaV6WqbIKhVsWqq9UHlLz52czwk_qO2w2cHjHm_CdjQ1L5b-L_9H7o8)
12. https://discuss.kubernetes.io/t/creating-docker-registry-secret-using-a-yaml-file/7042/2
13. https://blog.cloudhelix.io/using-a-private-docker-registry-with-kubernetes-f8d5f6b8f646
14. https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/

