#!/bin/bash
kill -9 $(jps | grep SNAPSHOT.jar | awk '{print $1}')
