#!/bin/bash
sudo /usr/share/logstash/bin/logstash -f ./logstash.conf
cd ./client/image-search
ng build --prod
cd ../../server/image-search
mvn clean install -P production-deployment
java -jar ./target/image-search-0.0.1-SNAPSHOT.jar

