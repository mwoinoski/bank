#!/bin/sh

docker tag bank:v1.0 us.gcr.io/{project-id}/bank:v1.0

docker login -u oauth2accesstoken -p "$(gcloud auth print-access-token)" https://us.gcr.io

docker push us.gcr.io/{project-id}/bank:v1.0