#!/bin/sh
mvn clean package
cd target/dist
unzip *
./start