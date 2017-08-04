#!/bin/bash

# Check we have the correct java version
java_version=$(java -version 2>&1 | sed -n 's/^java version *"\(.*\)"/\1/p')
if echo $java_version | grep -q "1\.8\..*" ; then
  echo Found Sun JDK: $java_version
else
  echo You have to build with Sun JDK 1.8.x, we found:
  java -version 2>&1
  exit 1
fi

# Check docker is working.
if docker ps >/dev/null 2>&1 ; then
  echo docker looks ok
else
  echo Could not run docker, check deployment is configured.
  exit 1
fi

MAVEN_OPTS="-Dmaven.test.skip=true"
export MAVEN_OPTS

#Clean old builds
rm -rf $(pwd)/build/docker/sakai/sakai-bin
#Compile the code
mvn -f src/pom.xml clean install directory:directory-of sakai:deploy 

#Build the docker image
docker build ./build/docker/sakai/ -t asicudl/campusvirtual:latest
