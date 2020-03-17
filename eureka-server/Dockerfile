FROM 8skjtmtf.mirror.aliyuncs.com/library/openjdk:8-jre-slim
#FROM openjdk:8u222-jre
#FROM hub-mirror.c.163.com/library/openjdk:8-jre-slim
VOLUME /tmp
COPY build/libs/eureka-server-1.0.0-SNAPSHOT.jar app.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/app.jar"]