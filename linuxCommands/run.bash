#!/bin/bash
java -jar product-service/target/product-service-0.0.1-SNAPSHOT.jar & > /dev/null
java -jar review-service/target/review-service-0.0.1-SNAPSHOT.jar & > /dev/null
java -jar recommendation-service/target/recommendation-service-0.0.1-SNAPSHOT.jar & > /dev/null
java -jar product-composite-service/target/product-composite-service-0.0.1-SNAPSHOT.jar & > /dev/null
