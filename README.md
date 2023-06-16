## Welcome
This project is intended only for personal use.
A simple rest API built with SpringBoot, H2, JDK 17, and Maven.
A simple web app built with Node 18 and React.

### Requirements 
Docker engine, please see this if you don't have docker installed -> https://docs.docker.com/engine/install/

## How to run the project?
The project has two folders
1. demo
2. webb-app2

Inside each folder, you can find a docker file.  
For the SpringBoot project, you can follow the next command steps:

###SpringBoot project

```
 docker build --platform linux/amd64 -t spring-helloworld .
```
After the build process, the application exposes services through 8080 port, so you need to bind that port when running the process.

```
 docker run -it -d -p 8080:8080 spring-helloworld
```

###React project

```
docker build --platform linux/amd64 -t node-webapp .
```
After the build process, the application exposes services through 8095 port, so you need to bind that port when running the process.

```
docker run -it -d -p 8095:8095 node-webapp

```