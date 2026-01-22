FROM eclipse-temurin:17-jdk

WORKDIR /app

# Install gradle
RUN apt-get update && apt-get install -y gradle

COPY . .

# Build using system gradle (NOT gradlew)
RUN gradle build

EXPOSE 4567

CMD ["java", "-cp", "build/classes/java/main:build/resources/main", "org.hotel.WebServer"]
