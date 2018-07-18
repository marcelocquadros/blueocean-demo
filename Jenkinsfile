pipeline {
  agent {
    docker {
      image 'maven:alpine'
      reuseNode true
      args '-v $HOME/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Unit Tests') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Publish Reports') {
      steps {
        junit 'target/surefire-reports/**/*.xml'
      }
    }
    stage('Build Docker Img') {
      steps {
        sh 'mvn dockerfile:build'
      }
    }
    stage('Test Image') {
      steps {
        sh 'docker exec -it `docker ps -a --no-trunc -q | head -n 1` bash;'
      }
    }
  }
}