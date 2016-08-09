FROM java:openjdk-6-jre
COPY gradle-dcompose-sample.jar /app/
WORKDIR /app/
ENV MONGO_HOST mongodb
ENV MONGO_PORT 27017
CMD ["sh", "-c", "java -Dmongo.host=$MONGO_HOST -Dmongo.port=$MONGO_PORT -jar gradle-dcompose-sample.jar "]
