pipeline {
    agent any
    tools {
        maven 'Maven 4.0.0'
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