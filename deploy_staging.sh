#! /bin/bash

echo "Deploying to staging"
ssh ubuntu@35.163.102.162 "sudo service ride-recorder-api stop"
scp target/ride-recorder-api-0.1.0.jar ubuntu@35.163.102.162:/var/ride-recorder-api/
ssh ubuntu@35.163.102.162 "sudo service ride-recorder-api start"