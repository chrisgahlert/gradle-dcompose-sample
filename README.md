# gradle-dcompose-sample

This sample project demonstrates the usage of the [gradle-dcompose-plugin](https://github.com/chrisgahlert/gradle-dcompose-plugin).

It is a simple counter that uses a MongoDB server to count how many times it has been called.

**See the [build.gradle](/chrisgahlert/gradle-dcompose-sample/blob/master/build.gradle) file for more details!**

## Usage

Checkout the project and run:
* `./gradlew run` to start the Java app inside a docker container
* `./gradlew test` to start the integration test using a MongoDB container
* `./gradlew createComposeFile` to create a `docker-compose.yml` file that can be used for deploying the app.
* `./graldew removeContainers removeNetworks removeVolumes removeImages` to clean everything up 

## Sample output: run

First run:
```shell
macbook:gradle-dcompose-sample $ ./gradlew run
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE
:copyFilesToDockerBuildDir UP-TO-DATE
:buildAppImage
Built Docker image with id 1c788eb2fda0 and tagged as dcompose7f00db37/app
:createBackendNetwork
:createFrontendNetwork
:pullMongoImage
Successfully pulled image mongo:3.3.9
:createMongoContainer
Created new container with id 857174b25e141862da1e5908cce66d456151dcb5829e9f38bc6f7ba897f24e0c (dcompose_7f00db37_mongo)
Connected container dcompose_7f00db37_mongo to network dcompose_7f00db37_backend
:createAppContainer
Created new container with id 12ba792201870112223e55c4625520109a65f8945f156d030be838e0135be361 (dcompose_7f00db37_app)
Connected container dcompose_7f00db37_app to network dcompose_7f00db37_backend
Connected container dcompose_7f00db37_app to network dcompose_7f00db37_frontend
:startMongoContainer
Starting Docker container with name dcompose_7f00db37_mongo
:startAppContainer
Starting Docker container with name dcompose_7f00db37_app

##################################
times invoked: 40 (last invocation was at Tue Sep 20 06:52:42 UTC 2016)
##################################

:run
Java program successfully executed in docker container. Received exit code: 0

BUILD SUCCESSFUL

Total time: 32.334 secs
```

Second run:
```shell
macbook:gradle-dcompose-sample $ ./gradlew run
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE
:copyFilesToDockerBuildDir UP-TO-DATE
:buildAppImage UP-TO-DATE
:createBackendNetwork UP-TO-DATE
:createFrontendNetwork UP-TO-DATE
:pullMongoImage SKIPPED
:createMongoContainer UP-TO-DATE
:createAppContainer UP-TO-DATE
:startMongoContainer UP-TO-DATE
:startAppContainer
Starting Docker container with name dcompose_7f00db37_app

##################################
times invoked: 41 (last invocation was at Tue Sep 20 06:53:42 UTC 2016)
##################################

:run
Java program successfully executed in docker container. Received exit code: 0

BUILD SUCCESSFUL

Total time: 2.878 secs
```

## Sample output: cleanTest test

First run:
```shell
macbook:gradle-dcompose-sample $ ./gradlew cleanTest test
:cleanTest UP-TO-DATE
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:createDefaultNetwork
:pullMongoTestImage
Successfully pulled image mongo:3.3.9
:createMongoTestContainer
Created new container with id c991608b8cb2a359e9be3e160fae2302c055ce38b30f87275ea3d407e484cf26 (dcompose_7f00db37_mongoTest)
Connected container dcompose_7f00db37_mongoTest to network dcompose_7f00db37_default
:startMongoTestContainer
Starting Docker container with name dcompose_7f00db37_mongoTest
:compileTestJava
warning: [options] bootstrap class path not set in conjunction with -source 1.6
1 warning
:processTestResources UP-TO-DATE
:testClasses
:test
:stopMongoTestContainer
Stopped Docker container named dcompose_7f00db37_mongoTest
:removeMongoTestContainer
Removed Docker container with name dcompose_7f00db37_mongoTest

BUILD SUCCESSFUL

Total time: 26.278 secs
```

Second run:
```shell
macbook:gradle-dcompose-sample $ ./gradlew cleanTest test
:cleanTest
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:createDefaultNetwork UP-TO-DATE
:pullMongoTestImage SKIPPED
:createMongoTestContainer
Created new container with id 533f3438e415bb4dde283b1175e6ed1ee79ca7f3ca9e1b5e2d5093b298f47957 (dcompose_7f00db37_mongoTest)
Connected container dcompose_7f00db37_mongoTest to network dcompose_7f00db37_default
:startMongoTestContainer
Starting Docker container with name dcompose_7f00db37_mongoTest
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test
:stopMongoTestContainer
Stopped Docker container named dcompose_7f00db37_mongoTest
:removeMongoTestContainer
Removed Docker container with name dcompose_7f00db37_mongoTest

BUILD SUCCESSFUL

Total time: 3.095 secs
```

## Sample output: createComposeFile

```shell
macbook:gradle-dcompose-sample $ ./gradlew createComposeFile
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE
:copyFilesToDockerBuildDir UP-TO-DATE
:buildAppImage UP-TO-DATE
:pullMongoImage SKIPPED
:createComposeFile
Warning: attachStdout is not supported by docker-compose

BUILD SUCCESSFUL

Total time: 1.452 secs
```

And the resulting file `build/docker-compose.yml`:
```yaml
version: '2'
services:
  app:
    image: sha256:2b0bdcc7a253a655cc3645ac0784061c8536c8dbc8018d91d9c6e908a32df55b
    environment:
    - MONGO_HOST=mongoalias
    - MONGO_PORT=27017
    networks:
      backend:
        aliases: []
      frontend:
        aliases: []
  mongo:
    image: sha256:c8b483f4eb50667cb91ee50369fef37d1f1c9b8b57e3fb261c24d9b4cb5fbbe9
    volumes:
    - mongo__dataDb:/data/db:rw
    - mongo__dataConfigdb:/data/configdb:rw
    ports:
    - '27017'
    networks:
      backend:
        aliases:
        - mongoalias
networks:
  backend: {}
  frontend: {}
volumes:
  mongo__dataDb: {}
  mongo__dataConfigdb: {}
```

## Sample output: removeContainers removeNetworks removeVolumes removeImages

First run:

```shell
macbook:gradle-dcompose-sample $ ./gradlew removeContainers removeNetworks removeVolumes removeImages
:stopAppContainer SKIPPED
:removeAppContainer SKIPPED
:stopMongoContainer SKIPPED
:removeMongoContainer SKIPPED
:stopMongoTestContainer SKIPPED
:removeMongoTestContainer SKIPPED
:removeContainers UP-TO-DATE
:removeBackendNetwork
:removeDefaultNetwork
:removeFrontendNetwork
:removeNetworks
:removeLogdataVolume
:removeVolumes
:removeAppImage
Successfully removed image dcomposec10023c3/app:latest
:removeMongoImage
Successfully removed image mongo:3.3.9
:removeMongoTestImage SKIPPED
:removeImages

BUILD SUCCESSFUL in 3s
6 actionable tasks: 6 executed
```

Second run:

```shell
macbook:gradle-dcompose-sample $ ./gradlew removeContainers removeNetworks removeVolumes removeImages
:stopAppContainer SKIPPED
:removeAppContainer SKIPPED
:stopMongoContainer SKIPPED
:removeMongoContainer SKIPPED
:stopMongoTestContainer SKIPPED
:removeMongoTestContainer SKIPPED
:removeContainers UP-TO-DATE
:removeBackendNetwork SKIPPED
:removeDefaultNetwork SKIPPED
:removeFrontendNetwork SKIPPED
:removeNetworks UP-TO-DATE
:removeLogdataVolume SKIPPED
:removeVolumes UP-TO-DATE
:removeAppImage SKIPPED
:removeMongoImage SKIPPED
:removeMongoTestImage SKIPPED
:removeImages UP-TO-DATE

BUILD SUCCESSFUL in 2s
```
