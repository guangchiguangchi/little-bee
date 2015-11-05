#!/bin/bash

echo "停止现有容器"
docker stop little-bee
echo "删除旧容器"
docker rm little-bee
echo "删除旧镜像"
docker rmi little-bee
echo "编译新镜像"
docker build -t little-bee .
echo "执行新容器"
docker run -d --name=little-bee -p 8000:8080 little-bee
