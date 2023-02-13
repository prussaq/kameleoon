build:
	sudo -S docker build --tag=kameleoon-test:latest .

run:
	sudo -S docker run -d --name kameleoon -p8080:8080 kameleoon-test:latest

stop:
	sudo -S docker stop kameleoon

remove:
	sudo -S docker rm kameleoon

compose:
	sudo -S docker-compose up -d

down:
	sudo -S docker-compose down