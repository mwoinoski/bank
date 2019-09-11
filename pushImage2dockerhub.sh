#!/bin/sh

docker tag bank:v1.0 volenin/bank:v1.0

docker login -u volenin

docker push volenin/bank:v1.0