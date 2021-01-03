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
        <!--load-on-startup>1</load-on-startup-->
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
```

## 7. Create a Multi-stage Release pipeline (Test -> Product Envirnonment） 

## 8. Delete repo
Click repo "maping/java-calculator-web-app", then click "Settings", then drop down to "Danger Zone", click "Delete this repository".

## Reference
1. [REST with Java (JAX-RS) using Jersey - Tutorial](https://www.vogella.com/tutorials/REST/article.html)
2. https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference
3. https://checkstyle.sourceforge.io/index.html
4. https://www.cnblogs.com/panie2015/p/5737468.html
5. https://www.cnblogs.com/fnlingnzb-learner/p/10637802.html
6. https://blog.csdn.net/iteye_7914/article/details/82450088
7. https://maven.apache.org/plugins/maven-site-plugin/examples/adding-deploy-protocol.html
