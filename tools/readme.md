## Create and generate infrastructure instances

- pre-requisite: install docker desktop on your machine (from docs.docker.com/desktop)

- Launch docker desktop

- Build docker image (including tag version and implicit push) from parent folder : 
````shell
    docker build --tag=<package_name>:<package_version> .
````

- Create a network

````shell
  docker network create applications-network
````

- Generate instances from tools/ folder :

````shell
  cd tools
  docker compose up -d
````

- Stop all instances
````shell
  docker compose stop && docker compose rm -f
````

- Connect to the docker container logs folder
````shell
  docker logs -f <container_name>
````

- Connect to the docker container instance 
````shell
  docker exec -it <container_name> /bin/sh
````

- Mongo Express UI :
  - http://localhost:8081 --> admin/pass

- Grafana UI : 
  - http://localhost:29000 --> admin/admin

- localhost UI (with all containers up) :
  - http://localhost:8082/api --> devops/devops
    (feel free to change port number from tools/compose.yaml)

- Functional testing
  - 
  - Execute all requests from the file : src/main/resources/queries/shop.http
  - Refer to OpenAPI UI : http://localhost:8082/api/swagger-ui.html