[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fkuehlfrank%2Fbackend%2Fbadge%3Fref%3Dmain&style=flat)](https://actions-badge.atrox.dev/kuehlfrank/backend/goto?ref=main)
# Kühlfrank backend
The backend of Kühlfrank is built as a REST API with Spring boot, Hibernate JPA and Auth0. It offers CRUD endpoints for inventory management and reicpe suggestion. 

## Running the backend
### Running locally
```
mvn clean install
mvn spring-boot:run
```
the app will start at [localhost:8080](http://localhost:8080)

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

## Endpoints
### Inventory
|Method | URL | Description | Example response |
|-------|-----|-------------|------|
|GET | `/inventory`| Lists all Inventoryentires of current users Inventory | [200 Ok](./docs/responses/get_inventory_response.json)
|POST | `/inventory/inventoryEntry` | Adds a new inventoryEntry (Item) |
|DELETE | `/inventory/inventoryEntry/{inventoryEntryId}` | Deltes the inventoryEntry (Item) |
|PUT | `/inventory/inventoryEntry/{inventoryEntryId}` | (selective) Updates the InventoryEntry (Item) | 

### Recipes
|Method | URL | Description | Example response |
|-------|-----|-------------|------|
|GET | `/recipes/suggestions` | Get (overview) recipe suggestions bases on current users Inventory | [200 Ok](./docs/responses/get_recipes_suggestions_response.json)
|GET | `/recipes/suggestions/suggestion/{recipeId}` | Get a user specific detailed suggestion for the recipe | [200 Ok](./docs/responses/get_detailed_recipes_suggestions_response.json)
|GET | `/recipes/random` | Get a random recipe suggestion | [200 Ok](./docs/responses/get_random_recipes_suggestions_response.json)

### Units
|Method | URL | Description | Example response |
|-------|-----|-------------|------|
|GET | `/units` | Get all units ordered by name | [200 Ok](./docs/responses/get_units_response.json)

### User
|Method | URL | Description | Example response |
|-------|-----|-------------|------|
|POST | `/ensureRegistered` | ensures the current authenticated user is registerd in kuehlfranks database and has an inventory assigned | 200 Ok or 201 Created


### Ingredients
|Method | URL | Description | Example response |
|-------|-----|-------------|------|
|POST | `/ingredients/find?q={query}&limit={limit}` | suggests one or multiple ingredient names which best match the given user input string  | [200 Ok](./docs/responses/get_ingredients_response.json)
