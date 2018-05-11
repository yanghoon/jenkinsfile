# Overview

# Usage


### Setting Your Project
#### Pipeline Config - pipeline.yaml
[sample pipeline.yaml](https://github.com/yanghoon/jenkinsfile-test/blob/master/pipeline.yaml)
```yaml
build:
- maven: mvn --version
- docker: docker version

deploy:
- k8s: k8s/*.yaml

agent:
  containers:
  - name: maven
    image: maven:3.3.9-jdk-8-alpine
    ttyEnabled: true
    command: cat
  - name: docker
    image: docker
    ttyEnabled: true
    command: cat
  volumes:
  - hostPathVolume:
      hostPath: '/var/run/docker.sock'
      mountPath: '/var/run/docker.sock'

stage:
- build
- deploy
```

#### Scratch Jenkinsfile
[sample Jenkinsfile](https://github.com/yanghoon/jenkinsfile-test/blob/master/Jenkinsfile)
```groovy
@Library('sail-lib') _
sail()
```

### Create Pipeline Job (with SCM)
![](https://user-images.githubusercontent.com/21324361/39913559-40b52ed2-553d-11e8-85a1-fd6e7ed4a4cd.png)


### Execute Your Job
