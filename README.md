Requirements:

Java 17
Gradle
Postman
Docker

Run these commands to start the project:

docker compose
./gradlew clean build
./gradlew bootRun

Your project should now have created a Docker image, a PostgreSQL database, tables, and populated the tables with predefined data. Additionally, your project should now be ready to accept requests.

Open your Postman app and create a new workspace. You can then import the CodeVibe.postman_collection.json from this repository, and all the test routes should be loaded into your Postman workspace.

Basic Auth routes will work once the collection is loaded.

To enable the JWT bearer token, go to "Environments" and add a new environment, then add a variable called "jwtToken." Next, under "Collections," select the new environment for storing variables. This should automate the JWT signing process. If this doesn’t work, you can copy and paste the token you get from registering/logging in into the "Token" input under Authorization.