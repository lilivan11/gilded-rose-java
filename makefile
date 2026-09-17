buildTests: 
	sudo docker build -f TestDockerfile -t gilded-rose-test .

runTests: 
	sudo docker run --rm gilded-rose-test