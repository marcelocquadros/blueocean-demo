node {
    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {
    	def mvnHome

    	stage('checkout'){

        mvnHome = tool 'M3'
        git url: 'https://github.com/marcelocquadros/blueocean-demo.git'

        sh "git rev-parse HEAD > .git/commit-id"
        def commit_id = readFile('.git/commit-id').trim()
        println commit_id
        }

    dir('${artifactId}'){
        stage('build'){
	        sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean install"
	        //sh 'docker login --username <userName> --password <password>'
	        sh ("docker build -t ${artifactId} .")
	        sh ("docker tag  ${artifactId} prakashg84/test:${artifactId}")
	        sh ("docker push <repo>/test:${artifactId}")
    	 }
    }
  }
}
