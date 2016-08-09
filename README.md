# gradle-dcompose-plugin

This sample project demonstrates the usage of the [gradle-dcompose-plugin](https://github.com/chrisgahlert/gradle-dcompose-plugin).

It is a simple counter that uses a MongoDB server to count how many times it has been called.

## Usage

Checkout the project and run:
* `./gradlew run` to start the Java app inside a docker container
* `./gradlew test` to start the integration test using a MongoDB container
* `./gradlew removeContainers` or `./gradlew removeImages` to clean everything up

## Sample output

First run:
```shell
macbook:gradle-dcompose-sample$ ./gradlew run
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE
:copyFilesToDockerBuildDir UP-TO-DATE
:buildAppImage
Built Docker image with id 4b445df7bf3d and tagged as dcompose7f00db37/app
:pullMongoImage SKIPPED
:createMongoContainer
Created new container with id a3922861c4bbc9146adce18c782e3f3e9d44b580ee8c9914a425974c05246f38 (dcompose_7f00db37_mongo)
:createAppContainer
Created new container with id 6c39dd4c3c4f6d6001b957c8d7c13979fb505df51a4c466c9a20130be567a943 (dcompose_7f00db37_app)
:startMongoContainer
Starting Docker container with name dcompose_7f00db37_mongo
:startAppContainer
Starting Docker container with name dcompose_7f00db37_app

##################################
times invoked: 66 (last invocation was at Tue Aug 09 22:28:46 UTC 2016)
##################################

:run
Java program successfully executed in docker container. Received exit code: 0

BUILD SUCCESSFUL

Total time: 2.919 secs
```

Second run:
```shell
macbook:gradle-dcompose-sample$ ./gradlew run
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE
:copyFilesToDockerBuildDir UP-TO-DATE
:buildAppImage UP-TO-DATE
:pullMongoImage SKIPPED
:createMongoContainer UP-TO-DATE
:createAppContainer UP-TO-DATE
:startMongoContainer UP-TO-DATE
:startAppContainer
Starting Docker container with name dcompose_7f00db37_app

##################################
times invoked: 67 (last invocation was at Tue Aug 09 22:29:06 UTC 2016)
##################################

:run
Java program successfully executed in docker container. Received exit code: 0

BUILD SUCCESSFUL

Total time: 2.258 secs
```