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