#!/bin/bash
minikube start
kubectl apply -f deployment.yml
minikube service matrosov-ikbo-01-21-service