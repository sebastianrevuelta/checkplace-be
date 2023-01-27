/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'python:3.9-bullseye' } }
    stages {
        stage('semgrep-app') {
            steps {
                sh 'pip3 install semgrep'
                sh 'semgrep scan --config=auto --error src --json > semgrep-output.json'
            }
        }
    }
}
