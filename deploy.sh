#!/bin/bash

env=$1
case $env in
    "development") 
        echo "dev environment" 
#        docker rmi $(docker images -f "dangling=true" -q) || true
        
        docker stop seagul_dev || true
        docker rm seagul_dev || true
        cd seagul
        docker build -t dev-seagul:alpine .
        cd ..
        docker run --name seagul_dev -d -p 80:80 dev-seagul:alpine

    ;;
    "production") 
        echo "prod" 
    ;;
    *) 
        echo "bad envo" 
    ;;
esac

echo "done"