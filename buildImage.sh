#!/bin/sh

mvn package
docker build target/ --tag=bank:v1.0 --build-arg JAR_FILE=bank-0.0.1-SNAPSHOT.jar