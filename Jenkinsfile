pipeline {
    agent any
    tools {
        maven 'maven360'
        jdk 'jdk8'
    }
    stages() {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}