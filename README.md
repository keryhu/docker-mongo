# need help for spring-boot-docker-mongo version 2

I met some problems when testing spring-boot mongoDB in docker-compose file version 2 .

The demo : https://github.com/keryhu/docker-mongo


This just a spring boot . It only contains a user model that saved in the mongo.

It works fine in compose file version 1 and local ,except version 2.

spring  application.yml :

	
	    mongodb:
	      uri: mongodb://test:123456@${MONGO_PORT_27017_TCP_ADDR:localhost}:${MONGO_PORT_27017_TCP_PORT:27017}/test
      
and  docker-compose.yml

	version: '2'
	services:
	
	  mongo:
	    image: mongo
	    container_name: mongo
	    ports:
	      - "27017:27017"
	    volumes:
	      -  mongo-data:/data/db:rw
	    networks:
	      - wfij
	
	  useraccount:
	    image: user-account:0.0.1-SNAPSHOT
	    container_name: useraccount
	    ports:
	      - "8001:8001"
	    networks:
	      - wfij
	
	networks:
	  wfij:
	    external:
	      name: wfij-net
	
	volumes:
	  mongo-data:
	    external: true
    
Before running , i **had created a docker volume mongo-data , and saved up the necessary test data** . 

After starting  . The spring code met the below errors :

	useraccount | 2016-03-01 08:07:12.959 ERROR 9 --- [ost-startStop-1] o.s.b.c.embedded.tomcat.TomcatStarter    : Error starting Tomcat context: org.springframework.beans.factory.BeanCreationException
	useraccount | 2016-03-01 08:07:13.002  WARN 9 --- [           main] ationConfigEmbeddedWebApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start embedded container; nested exception is org.springframework.boot.context.embedded.EmbeddedServletContainerException: Unable to start embedded Tomcat
	useraccount | 2016-03-01 08:07:13.014 ERROR 9 --- [           main] o.s.boot.SpringApplication               : Application startup failed
	useraccount | 
	useraccount | org.springframework.context.ApplicationContextException: Unable to start embedded container; nested exception is org.springframework.boot.context.embedded.EmbeddedServletContainerException: Unable to start embedded Tomcat
	useraccount | 	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.onRefresh(EmbeddedWebApplicationContext.java:133) ~[spring-boot-1.3.2.RELEASE.jar!/:1.3.2.RELEASE]
	useraccount | 	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:532) ~[spring-context-4.2.4.RELEASE.jar!/:4.2.4.RELEASE]
	useraccount | 	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:118) ~[spring-boot-1.3.2.RELEASE.jar!/:1.3.2.RE
	useraccount | Caused by: org.springframework.boot.context.embedded.EmbeddedServletContainerException: Unable to start embedded Tomcat
	useraccount | 	at org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer.initialize(TomcatEmbeddedServletContainer.java:99) ~[spring-boot-1.3.2.RELEASE.jar!/:1.3.2.RELEASE]
	useraccount | 	at org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer.<init>(TomcatEmbeddedServletContainer.java:76) ~[spring-boot-1.3.2.RELEASE.jar!/:1

the docker ls

	keryhu@keryhu-lap:~$ docker ps -a
	CONTAINER ID        IMAGE                         COMMAND                  CREATED             STATUS              PORTS                      NAMES
	8d4329516fe4        mongo                         "/entrypoint.sh mongo"   53 minutes ago      Up 53 minutes       0.0.0.0:27017->27017/tcp   mongo
	cedd77829c8c        user-account:0.0.1-SNAPSHOT   "/bin/sh -c /app/runb"   53 minutes ago      Up 11 seconds       0.0.0.0:8001->8001/tcp     useraccount
	
	
the used network 

	keryhu@keryhu-lap:~$ docker network inspect wfij-net
	[
	    {
	        "Name": "wfij-net",
	        "Id": "326137a5358a8c176e893ee0249f93225f736dc0c50b3b60c471c6d6f9984923",
	        "Scope": "local",
	        "Driver": "bridge",
	        "IPAM": {
	            "Driver": "default",
	            "Options": {},
	            "Config": [
	                {
	                    "Subnet": "172.18.0.0/16",
	                    "Gateway": "172.18.0.1/16"
	                }
	            ]
	        },
	        "Containers": {
	            "8d4329516fe499770d130c0aef53143873df1ef8f4e3a51c341a9a909d8c0a17": {
	                "Name": "mongo",
	                "EndpointID": "f721189f46b8ebb770282b5cb92906f99ef18c8e6039941e3f71fb175f012f51",
	                "MacAddress": "02:42:ac:12:00:03",
	                "IPv4Address": "172.18.0.3/16",
	                "IPv6Address": ""
	            },
	            "cedd77829c8c5e5338676cfa1b6ebe1a3e671538b40b5106fc9ede98c2859558": {
	                "Name": "useraccount",
	                "EndpointID": "4af525bb657ed57dcc58932a7331b490afe52888996000c76b10e8e7632cd963",
	                "MacAddress": "02:42:ac:12:00:02",
	                "IPv4Address": "172.18.0.2/16",
	                "IPv6Address": ""
	            }
	        },
	        "Options": {}
	    }
	]


> I test the mongo container , can see the saved user-data. And mongo container can ping useraccount container .

But i don'n know why the spring can't start successfully . maybe 

	 mongodb:
		      uri: mongodb://test:123456@${MONGO_PORT_27017_TCP_ADDR:localhost}:${MONGO_PORT_27017_TCP_PORT:27017}/test 
	      
not correct?

who can help me ?