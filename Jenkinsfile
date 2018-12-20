pipeline {
    agent none
    stages() {
        stage('Build') {
            parallel {
                stage('Build Frontend') { 
                    agent {
                        dockerfile {
                            dir 'AngularImage'
                            args '-v /var/run/docker.sock:/var/run/docker.sock'
                        }
                    }
                    environment {
                        HOME="."
                    }
                    stages {
                        stage('build') {
                            steps {
                                dir('seagul') {
                                    //sh 'npm i --verbose'
                                    //sh 'ng build --prod --build-optimizer=false --aot=true'
                                    //sh 'ls'
                                    //
                                    sh 'which docker'
                                    sh 'docker run --rm -d alpine'
                                }
                            }
                        }
                    }
                    post {
                        success {
                            echo 'success'
                            // archiveArtifacts artifacts: 'seagul/dist/**/*', fingerprint: true
                        }
                    }
                }
                // stage('Backend') {
                //     agent {
                //         docker {
                //             image 'maven:3-alpine'
                //             args '-v $HOME/.m2:/root/.m2'
                //         }
                //     }
                //     stages {
                //         stage('Build Backend') { 
                //             steps {
                //                 sh 'mvn -B -DskipTests=true clean package'
                //             }
                //             post {
                //                 success {
                //                     archiveArtifacts artifacts: 'target/**/*.jar', fingerprint: true
                //                 }
                //             }
                //         }
                //         stage('Test Backend') {
                //             steps {
                //                 sh 'mvn test'
                //             }
                //             post {
                //                 success {
                //                     archiveArtifacts artifacts: 'target/**/surefire-reports/**/*.xml', fingerprint: true
                //                 }
                //             }
                //         }
                //     }
                // }
            }
        }
        // stage('Deploy for Development') {
        //     agent any
        //     steps {
        //         sh 'pwd'
        //         sh 'ls'
        //         sh 'docker stop seagul_dev || true'
        //         sh 'docker rm seagul_dev || true'
        //         sh 'docker run --name seagul_dev -d -p 80:80 cool-idea'
        //     }
        // }
    }
}