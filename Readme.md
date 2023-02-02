### Raised Bed Planer Service

This GraphQL service manages the planning of a raised bed with Permaculture principles.
(Not an MVP yet and missing GraphQL testing)

#### Used Technology

- Java 15
- Gradle
- PostgreSQL
- GraphQL

#### Development preconditions

- Run elasticsearch 7.13.0
- Run postgresql database

##### Database Configuration

###### Create new Postgresql database

1. Start a general Postgresql Docker container or use the docker container in this project (then the next steps are not necessary).
2. Open DBeaver or a different tool of your choice to connect to the database instance.
3. Create the new database "permaculture-designer"
4. Establish a new connection to the database "permaculture-designer" for development.
