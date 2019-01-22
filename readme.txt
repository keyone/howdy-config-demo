docker build -t keyone01/howdy-config-demo:0.1 .
docker run --rm -v $(pwd)/config:/app/config -p 8080:8080 keyone01/howdy-config-demo:0.1