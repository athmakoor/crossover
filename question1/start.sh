#!/bin/bash
cd ./client/image-upload
ng build --prod
cd ../../server/image-upload
mvn clean install -P production-deployment
java -jar ./target/image-upload-0.0.1-SNAPSHOT.jar

