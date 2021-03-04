#!/bin/bash
kill -9 $(jps | grep .jar | awk '{print $1}')
