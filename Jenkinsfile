pipeline {
    agent none
    tools {
        maven 'maven360'
        jdk 'jdk8'
    }
    stages() {
        stage('Test') { 
        agent any
        steps {
                sh 'cat docker-compose.yml'
                // sh 'mvn -B test'
            }
        }
        stage('Build Frontend') { 
            when {
                anyOf {
                    changeset 'seagul/**/*'
                }
            }
            agent {
                docker {
                    image 'bare-angular:alpine'
                }
            }
            steps {
                sh 'ng build --prod'
            }
        }
        stage('Done') {
            steps {
                echo 'heysan'
            }
        }
        // stage('Build') { 
        //     steps {
        //         sh 'mvn -B -DskipTests clean package'
        //     }
        // }
        // stage('Ask user for input') {
        //     options {
        //         timeout(time: 10, unit: 'SECONDS')
        //     }    
        //     input {
        //         message 'do we do it?'
        //         ok 'yes we do'
        //         parameters {
        //             string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who is we hello to?')
        //         }               
        //     }
        //     steps {
        //         echo "Hello, ${PERSON} Sir"
        //         sh 'pwd'

        //         sh 'docker-compose up --build'
        //     }
        // }
    }
}