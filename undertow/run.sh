#!/bin/bash

./gradlew shadowJar && java -jar build/libs/undertow-1.0-SNAPSHOT-all.jar