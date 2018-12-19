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
                        docker {
                            image 'bare-angular:alpine'
                            customWorkspace 'seagul'
                        }
                    }
                    steps {
                        sh 'pwd'
                        sh 'npm i --verbose'
                        sh 'ng build --prod'
                    }
                    post {
                        success {
                            echo 'success'
                            archiveArtifacts artifacts: 'dist', fingerprint: true
                        }
                    }
                }
                stage('Build Backend') { 
                    when {
                        anyOf {
                            branch 'master'
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
                    post {
                        success {
                            archiveArtifacts artifacts: 'target/**/*.jar', fingerprint: true
                        }
                    }
                }
            }
        }
    }
}