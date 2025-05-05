# pdf-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/pdf-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- OpenPDF ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-itext/dev/)): Open and Free PDF library

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Testing in native mode locally :

```shell 
mvnw verify -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.container-image.name=pdf-with-quarkus -Dquarkus.docker.dockerfile-native-path=src/main/docker/Dockerfile.native-micro
```

## Rebuild docker image from test executable

```shell 
docker build -f C:\dev\project\pdf-with-quarkus\src\main\docker\Dockerfile.native-micro -t fef/pdf-with-quarkus:1.0.0-SNAPSHOT C:\dev\project\pdf-with-quarkus
```

## Run docker image from test native executable

```shell 
docker run --name quarkus-integration-test-pomDm -i --rm -p 8081:8081 -p 8444:8444 --env QUARKUS_LOG_CATEGORY__IO_QUARKUS__LEVEL=INFO --env QUARKUS_HTTP_PORT=8081 --env QUARKUS_HTTP_SSL_PORT=8444 --env TEST_URL=http://localhost:8081 --env QUARKUS_PROFILE=prod --env QUARKUS_DOCKER_DOCKERFILE_NATIVE_PATH=src/main/docker/Dockerfile.native-micro --env QUARKUS_CONTAINER_IMAGE_NAME=pdf-with-quarkus --env QUARKUS_CONTAINER_IMAGE_BUILD=true --env QUARKUS_NATIVE_CONTAINER_BUILD=true fef/pdf-with-quarkus:1.0.0-SNAPSHOT
```
