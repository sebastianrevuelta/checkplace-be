/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'returntocorp/semgrep' } }
    stages {
        stage('semgrep') {
            steps {
                sh 'semgrep ci'
            }
        }
    }
}
