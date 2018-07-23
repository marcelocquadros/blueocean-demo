pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        readMavenPom(file: 'pom.xml')
        sh 'echo $artifactId'
      }
    }
  }
}