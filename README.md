# spirng-mongo-docker

this is just a spring boot docker demo .

It has update to docker-compose 1.6,and use docker compose file version2 .

first i create a docker volume mongo-data . 
and i copy some user data to the docker volume .

and use network  wfij-net.

	networks:
	  wfij:
	    external:
	      name: wfij-net
	
	volumes:
	  mongo-data:
	    external: true