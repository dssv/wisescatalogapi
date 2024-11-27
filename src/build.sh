# Compile the App and move the JAR executable file to the root folder under runnable.jar
docker-compose --env-file ".env" build

echo "App Compiled Successfully"