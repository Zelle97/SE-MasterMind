# build stage
FROM hseeberger/scala-sbt:graalvm-ce-20.0.0-java11_1.4.3_2.13.3
WORKDIR /mastermind
ADD . /mastermind
RUN sbt clean compile assembly

# production stage
FROM hseeberger/scala-sbt:graalvm-ce-20.0.0-java11_1.4.3_2.13.3
WORKDIR /mastermind
COPY --from=0 /mastermind/target/scala-2.13 /mastermind
CMD java -jar mastermind.jar 0

