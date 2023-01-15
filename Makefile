docker-build-run:
	docker build -t imedia-joelpatel-take-home-assignment . && docker run -p 8080:8080 --name imedia-joelpatel-take-home-assignment imedia-joelpatel-take-home-assignment

docker-resume:
	docker start imedia-joelpatel-take-home-assignment

docker-stop:
	docker stop imedia-joelpatel-take-home-assignment

docker-clean:
	docker stop imedia-joelpatel-take-home-assignment && docker container rm imedia-joelpatel-take-home-assignment && docker rmi imedia-joelpatel-take-home-assignment

test:
	./mvnw clean test

.PHONY: docker-build-run docker-resume docker-stop docker-clean test