FROM openjdk:11-jdk

VOLUME /tmp

EXPOSE 8000

ARG JAR_FILE=apps/member/build/libs/*.jar

COPY ${JAR_FILE} apps-member.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=real","/apps-member.jar"]