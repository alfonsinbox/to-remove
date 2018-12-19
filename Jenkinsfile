pipeline {
    agent none
    stages() {
        stage('Build') {
            parallel {
                stage('Build Frontend') { 
                    agent {
                        docker {
                            image 'bare-angular:alpine'
                        }
                    }
                    stages {
                        stage('build'){
                            steps {
                                dir('seagul') {
                                    sh 'npm i --verbose'
                                    sh 'ng build --prod'
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
                stage('Backend') {
                    agent {
                        docker {
                            image 'maven:3-alpine'
                            args '-v $HOME/.m2:/root/.m2'
                        }
                    }
                    stages {
                        stage('Build Backend') { 
                            steps {
                                sh 'mvn -B -DskipTests=true clean package'
                            }
                            post {
                                success {
                                    archiveArtifacts artifacts: 'target/**/*.jar', fingerprint: true
                                }
                            }
                        }
                        stage('Test Backend') {
                            steps {
                                sh 'mvn test'
                            }
                            post {
                                success {
                                    archiveArtifacts artifacts: 'target/**/surefire-reports/**/*.xml', fingerprint: true
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Deploy for Development') {
            // when {
            //     branch 'master'
            // }
            steps {
                sh 'pwd'
                sh 'chmod +x deploy.sh'
                sh './deploy.sh development'
            }
        }
    }
}