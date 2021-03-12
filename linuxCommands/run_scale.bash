#!/bin/bash
java -jar product-service/target/product-service-0.0.1-SNAPSHOT.jar --server.port=7001 > /dev/null &
java -jar product-service/target/product-service-0.0.1-SNAPSHOT.jar --server.port=7002 > /dev/null &

java -jar review-service/target/review-service-0.0.1-SNAPSHOT.jar --server.port=7003 > /dev/null &
java -jar review-service/target/review-service-0.0.1-SNAPSHOT.jar --server.port=7004 > /dev/null &
java -jar review-service/target/review-service-0.0.1-SNAPSHOT.jar --server.port=7005 > /dev/null &

java -jar recommendation-service/target/recommendation-service-0.0.1-SNAPSHOT.jar --server.port=7006 > /dev/null &
java -jar recommendation-service/target/recommendation-service-0.0.1-SNAPSHOT.jar --server.port=7007 > /dev/null &

java -jar product-composite-service/target/product-composite-service-0.0.1-SNAPSHOT.jar > /dev/null &
java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar > /dev/null &