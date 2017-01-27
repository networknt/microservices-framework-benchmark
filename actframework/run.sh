#!/bin/sh
mvn -U clean package
cd target/dist
unzip *
./start
