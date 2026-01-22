FROM eclipse-temurin:17-jdk

WORKDIR /app

# Install bash (gradlew needs it)
RUN apt-get update && apt-get install -y bash

COPY . .

# Fix Windows line endings + permissions
RUN sed -i 's/\r$//' gradlew
RUN chmod +x gradlew

# Build the app
RUN bash ./gradlew build

EXPOSE 4567

CMD ["java", "-cp", "build/libs/*", "org.hotel.WebServer"]
