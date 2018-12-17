pipeline {
    agent any
    tools {
        maven 'maven 3.6.0'
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
    }
}