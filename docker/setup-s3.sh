#!/usr/bin/env bash
set -e
export TERM=ansi
export AWS_ACCESS_KEY_ID=foobar
export AWS_SECRET_ACCESS_KEY=foobar
export AWS_DEFAULT_REGION=us-east-1
export PAGER=

echo "S3 Configuration started"

aws --endpoint-url=http://localhost:4566 s3 mb s3://users-pictures

aws --endpoint-url=http://localhost:4566 s3 mb s3://destinations-pictures

echo "S3 Configured"

echo "DynamoDB Configuration started"

awslocal dynamodb create-table \
   --table-name pictures \
   --attribute-definitions AttributeName=id,AttributeType=S \
   --key-schema AttributeName=id,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5


echo "DynamoDB Configured"