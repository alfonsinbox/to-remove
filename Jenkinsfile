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
            options {
                timeout(time: 10, unit: 'SECONDS')
            }    
            input {
                message 'do we do it?'
                ok 'yes we do'
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who is we hello to?')
                }               
            }
            steps {
                echo "Hello, ${PERSON} Sir"
                sh 'docker-compose up --build'
            }
        }
    }
}