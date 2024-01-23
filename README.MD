
# Spring Boot + Microservices with Containerization using Kubernetes Assessment

## What has been done?
1. Used [Spring Initializr](https://start.spring.io/) to create a Spring Boot project with dependencies below:
    1. Spring Web
    2. Lombok
    3. Spring Data JPA

2. Built APIs which follow this [specification](<Spring Boot Assessment Swagger v1.0.0.yaml>). The specification can be viewed at [Swagger Editor](https://editor-next.swagger.io/).

3. Added files below for containerization:
   1. Dockerfile
   2. deployment.yaml
   3. service.yaml

## How to build and start the application?
1. Run `kubectl apply -f deployment.yaml`
2. Run `kubectl apply -f service.yaml`
3. Run `kubectl get svc xian-springboot-k8s` to check the active service and get the __port number__
4. Run `kubectl get nodes -o wide` to get __internal IP__ of the Kubernetes cluster
5. [<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/29821433-2746aa77-f4c4-4ee0-94e4-12e220349e28?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D29821433-2746aa77-f4c4-4ee0-94e4-12e220349e28%26entityType%3Dcollection%26workspaceId%3D4835f262-cbd1-4914-a49b-7431cf9b8641)
6. In Postman Variables tab, replace the value of:
   1. `container_port` with __port number__ from step 3
   2. `k8s_internal_ip` with __internal IP__ from step 4
7. Run the API requests

## How to run on Docker
1. Run `docker run -p 8080:9090 xian0120/xian-springboot-k8s`