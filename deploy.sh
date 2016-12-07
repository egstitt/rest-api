#! /bin/bash

echo "Deploying to EC2"

scp target/rest-api-0.1.0.jar ubuntu@54.201.237.229:/var/rest-api/
ssh ubuntu@54.201.237.229 "sudo service rest-api restart"