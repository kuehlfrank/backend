# Kühlfrank backend

### Running locally
```
mvn clean install
mvn spring-boot:run
```
the app will start at [localhost:8080](http://localhost:8080)

## Docker
### Building and running the docker image
```
mvn clean install
mvn spring-boot:build-image
docker run -p 8080:8080 -it backend:0.0.1-SNAPSHOT
```

### Running the docker image form registry
You might have to login to the registry first. See [this](https://docs.github.com/en/free-pro-team@latest/packages/getting-started-with-github-container-registry/migrating-to-github-container-registry-for-docker-images#authenticating-with-the-container-registry) guide here.
```
docker pull ghcr.io/kuehlfrank/backend:latest
docker run -p 8080:8080 -it ghcr.io/kuehlfrank/backend:latest
```

### Building and pushing the docker image
You might have to login to the registry first. See [this](https://docs.github.com/en/free-pro-team@latest/packages/getting-started-with-github-container-registry/migrating-to-github-container-registry-for-docker-images#authenticating-with-the-container-registry) guide here.
```
mvn clean install
mvn spring-boot:build-image
docker tag backend:0.0.1-SNAPSHOT ghcr.io/kuehlfrank/backend:latest
docker push ghcr.io/kuehlfrank/backend:latest
```
