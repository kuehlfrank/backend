# Kühlfrank backend

### Running locally
```
mvn spring-boot:run
```
the app will start at [localhost:8080](http://localhost:8080)

## Docker
### Running the docker image
```
mvn spring-boot:build-image
docker run -p 8080:8080 -it backend:0.0.1-SNAPSHOT
```

### Building and pushing the docker image
You might have to sign up to the registry first. See [this](https://docs.github.com/en/free-pro-team@latest/packages/getting-started-with-github-container-registry/migrating-to-github-container-registry-for-docker-images#authenticating-with-the-container-registry) guide here.
```
mvn spring-boot:build-image
docker tag backend:0.0.1-SNAPSHOT ghcr.io/kuehlfrank/backend:latest
docker push ghcr.io/kuehlfrank/backend:latest
```
