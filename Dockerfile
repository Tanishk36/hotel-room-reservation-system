FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./gradlew build

EXPOSE 4567

CMD ["java", "-cp", "build/libs/*", "org.hotel.WebServer"]
