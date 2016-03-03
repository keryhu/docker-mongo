# spirng-mongo-docker

this is just a spring boot docker demo .

It has update to docker-compose 1.6,and use docker compose file version2 .

it works fine .  

**docker-compose.yml**

	version: '2'
	services:
	
	  mongo:
	    image: mongo
	    container_name: mongo
	    restart: always
	    ports:
	      - "27017:27017"
	    volumes_from:
	      -  mongodata
	    networks:
	      - wfij
	
	  mongodata:
	    image: mongo
	    volumes:
	      - /home/keryhu/dbdata/mongodb:/data/db
	    command: --break-mongo
