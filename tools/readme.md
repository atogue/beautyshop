## Create and generate infrastructure instances

- Create a network

```` shell
    docker network create applications-network
````

- Generate instances

```` shell
    docker compose up -d
````

- Stop all instances
````shell
    docker compose stop && docker compose rm -f
````

- Mongo Express UI :
http://localhost:8081 --> admin/pass

- Grafana UI :
http://localhost:29000 --> admin/admin

- localhost UI :
- http://localhost:8080 --> devops/devops