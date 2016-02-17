FROM java:8
MAINTAINER keryhu@hotmail.com
VOLUME /tmp
RUN mkdir /app
ENV MONGODB_PORT ${MONGO_PORT_27017_TCP_PORT}
ENV MONGODB_HOST ${MONGO_PORT_27017_TCP_ADDR}
EXPOSE 8001
ADD user-account-0.0.1-SNAPSHOT.jar /app/app.jar
COPY runboot.sh /app/
RUN bash -c 'touch  /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh