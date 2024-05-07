pipeline {
    agent any
    stages {
        stage('Hello'){
            steps {
                echo 'Hello'
            }
        }
        stage('Cloning'){
            steps {
               git url: 'https://github.com/vaibhavdikha2206/swapiapp-spring'
            }
        }
        stage('Install'){
            steps {
               sh 'mvn install'
               sh 'docker build -t springapp .'
            }
        }
        stage('Deploy'){
            steps {
                catchError {
                    sh 'docker stop springb2'
                }
                echo currentBuild.result

                catchError {
                    sh 'docker rm springb2'
                }
                sh 'docker run --name springb2 -p 8080:8080 springapp'
            }
        }

    }
}