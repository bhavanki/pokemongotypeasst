# Pokémon Go Type Assistant

A Java [Twilio](https://twilio.com) application that tells you about type strengths and weaknesses in Pokémon Go.

## Building

Prerequisites:

* Java 11 or higher
* Apache Maven 3

```sh
mvn package
```

## Running

The application uses HTTPS. Create a server.properties file with keystore information:

```properties
keystorePath=/path/to/keystore.p12
keystorePassword=supersekrit
```

Then:

```sh
java -jar target/pokemongotypeasst.jar /path/to/server.properties
```

The server listens on port 4567.

## Usage

This application is designed to serve as a messaging webhook from a Twilio phone number. Once the application is running and listening, go to the Twilio console and enter the application URL, for example:

```
https://example.com:4567/pokemongotypeasst
```

Then, send a text message to your number to use the application.

## License

[MIT](LICENSE)
