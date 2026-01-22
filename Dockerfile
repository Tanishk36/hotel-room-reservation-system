FROM eclipse-temurin:17-jre

WORKDIR /app

COPY build/libs/HotelRoomReservationSystem-1.0-SNAPSHOT.jar app.jar

EXPOSE 4567

CMD ["java", "-jar", "/app/app.jar"]
