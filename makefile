.PHONY: help

dockerhelp:
	@LC_ALL=C $(MAKE) -pRrq -f $(firstword $(MAKEFILE_LIST)) : 2>/dev/null | awk -v RS= -F: '/(^|\n)# Files(\n|$$)/,/(^|\n)# Finished Make data base/ {if ($$1 !~ "^[#.]") {print $$1}}' | sort | grep -E -v -e '^[^[:alnum:]]' -e '^$@$$'

build:
	cd consumer/; mvn package
	cd producer; mvn package
	docker compose -f docker-compose.yml build

start:
	docker compose -f docker-compose.yml start

stop:
	docker compose -f docker-compose.yml stop

clean: stop
	docker compose -f docker-compose.yml rm -f

dependencies:
	docker compose -f docker-compose.yml up -d db kafka zookeeper schema-registry

up: build
	docker compose -f docker-compose.yml up -d 

down:
	docker compose -f docker-compose.yml down



