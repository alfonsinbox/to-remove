pipeline {
    agent none
    stages() {
        stage('Build') {
            parallel {
                stage('Build Frontend') { 
                    when {
                        anyOf {
                            branch 'master'
                            changeset 'seagul/**/*'
                        }
                    }
                    agent {
                        dockerfile {
                            dir 'seagul/build'
                        }
                    }
                    steps {
                        dir('seagul') {
                            sh 'pwd'
                            sh 'npm i --verbose'
                            sh 'ng build --prod'
                        }
                    }
                    post {
                        success {
                            echo 'success'
                        }
                    }
                }
                stage('Build Backend') { 
                    when {
                        anyOf {
                            changeset 'src/**/*'
                        }
                    }
                    agent {
                        docker {
                            image 'maven:3-alpine'
                            args '-v $HOME/.m2:/root/.m2'
                        }
                    }
                    steps {
                        sh 'pwd'
                        sh 'mvn -B -DskipTests=true clean package'
                    }
                }
            }
        }
    }
}