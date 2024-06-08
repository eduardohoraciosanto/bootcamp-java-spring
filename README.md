# bootcamp-java-spring
The good old bootcamp done in Java

This is my attempt at making this [Challenge](https://github.com/eduardohoraciosanto/Golang-Challenge) in Java using Spring & Maven.

I'll try to also implement 100% coverage on unit testing and to dockerize the app so it can run with just a Makefile target.


The following commands have to be done inside the `bootcamp` directory.

```
cd bootcamp
```

# Running tests

Simply issue `make tests` and the tests will be run. Notice this step is done outside of the docker container

# Building the Image

Simply issue `make build` and the docker image will be built.

# Running the Service

Simply issue `make run` and the Service will run inside a docker container.