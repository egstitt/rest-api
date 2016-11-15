#! /bin/bash

echo "Deploying to staging"
scp -i "ride-recorder.pem" ~/git/ride-recorder-api/target/ride-recorder-api-0.1.0.jar ubuntu@35.163.102.162:
