pipeline {
  agent {
    docker {
      image 'maven:alpine'
      args '-v $HOME/.m2:/root/.m2'
    }

  }
  stages {
    stage('build') {
      steps {
        sh '''mvn -version
kubectl get pods'''
      }
    }
  }
}