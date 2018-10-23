#!/bin/sh
mvn -U clean package
cd target/dist
tar xzf *.gz
./run
