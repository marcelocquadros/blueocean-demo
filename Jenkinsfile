pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        git 'https://github.com/marcelocquadros/blueocean-demo.git'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -DskipTests clean package'
      }
    }
    stage('Tests') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Publish Reports') {
      steps {
        junit '**/target/surefire-reports/**.xml'
      }
    }
    stage('Build docker Image') {
      steps {
        sh 'docker build -t $DOCKER_REPO/$ARTIFACT_ID:$VERSION . --build-arg JAR_FILE=target/$ARTIFACT_ID-$VERSION.jar'
      }
    }
    stage('Publish Image') {
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {
            sh 'docker push $DOCKER_REPO/$ARTIFACT_ID:$VERSION'
          }
        }

      }
    }
    stage('Deploy to k8s') {
      steps {
        sh 'kubectl delete deploy $ARTIFACT_ID || true'
        sh 'kubectl run $ARTIFACT_ID --image=$DOCKER_REPO/$ARTIFACT_ID:$VERSION'
        sh 'kubectl delete svc $ARTIFACT_ID || true'
        sh 'kubectl expose deploy $ARTIFACT_ID --port=8080 --target-port=8080 --type=NodePort'
      }
    }
  }
  environment {
    ARTIFACT_ID = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
    DOCKER_REPO = 'marceloquadros'
  }
}