#!/bin/bash

env=$1
case $env in
    "development") 
        echo "dev environment" 
#        docker rmi $(docker images -f "dangling=true" -q) || true
        
        docker stop seagul_dev || true
        docker rm seagul_dev || true
        docker build -t dev-seagul:alpine -f seagul/Dockerfile seagul
        docker run --name seagul_dev -d dev-seagul:alpine

    ;;
    "production") 
        echo "prod" 
    ;;
    *) 
        echo "bad envo" 
    ;;
esac

echo "done"