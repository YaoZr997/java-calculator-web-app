# A Java Calculator Web Application
[![Build Status](https://dev.azure.com/maping930883/java-helloworld-web-app/_apis/build/status/java-helloworld-web-app-Maven-CI?branchName=master)](https://dev.azure.com/maping930883/java-helloworld-web-app/_build/latest?definitionId=26&branchName=master)

## 1. Create a repo manually

### 1.1 Create a repo on GitHub
Click "Repositories",then click "New" button,input "java-calculator-web-app", leave all other input as default, click "Create repository".

### 1.2 Create a Java calculator web app by Maven
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
## 2. Create a Maven CI pipeline

## 3. Create a Maven CI pipeline

## 4. Create a Self-Hosted VM CI pipeline

## 5. Create a Self-Hosted Docker CI pipeline
### 5.1 Run as a standalone Docker container
### 5.2 Deploy and run on Kubernetes

## 6. Create a Maven CI&CD pipeline (Develop Environment)
### 6.1 Install a Tomcat VM manually
### 6.2 Install a Tomcat VM automatically（Infrastructure as Code）

## 7. Create a Multi-stage Release pipeline (Test -> Product Envirnonment） 
### 7.1 Install two Tomcat VMs manually
### 7.2 Install two Tomcat VMs automatically（Infrastructure as Code）

## 8. Delete repo
Click repo "maping/java-calculator-web-app", then click "Settings", then drop down to "Danger Zone", click "Delete this repository".

## Reference
1. [REST with Java (JAX-RS) using Jersey - Tutorial](https://www.vogella.com/tutorials/REST/article.html)
