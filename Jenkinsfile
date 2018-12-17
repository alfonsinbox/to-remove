pipeline {
    agent any
    tools {
        maven 'maven360'
        jdk 'jdk8'
    }
    stages() {
        stage('Test') { 
            steps {
                sh 'mvn -B test'
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Ask user for input') {
            input {
                message 'do we do it?'
            }
            steps {
                sh 'echo'
            }
        }
    }
}