FROM openjdk:11-jdk

VOLUME /tmp

#COPY gradlew .
#COPY gradle gradle
#COPY /
#RUN chmod +x gradlew
#RUN ./gradlew :apps:apps-member:build -x test

EXPOSE 8000

ARG JAR_FILE=./apps/member/build/libs/apps-member-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} apps-member.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=real","/apps-member.jar"]