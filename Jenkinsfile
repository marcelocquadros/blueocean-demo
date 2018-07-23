pipeline {
  agent {
    node {
      label 'teste'
    }

  }
  stages {
    stage('build') {
      steps {
        readMavenPom(file: 'pom.xml')
        sh 'echo $artifactId'
      }
    }
  }
}