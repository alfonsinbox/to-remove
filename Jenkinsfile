pipeline {
    agent none
    stages() {
        stage('Build Frontend') { 
            //when {
                //anyOf {
                    //changeset 'seagul/**/*'
                //}
            //}
            agent {
                docker {
                    image 'bare-angular:alpine'
                }
            }
            steps {
                dir('seagul') {
                    sh 'pwd'
                    sh 'npm i'
                    sh 'ng build --prod'
                }
            }
        }
        stage('Build Backend') { 
            //when {
                //anyOf {
                    //changeset 'src/**/*'
                //}
            //}
            agent {
                docker {
                    image 'maven:3-alpine'
                }
            }
            steps {
                dir('fb-api') {
                    sh 'pwd'
                    sh 'mvn -B -DskipTests=true clean package'
                }
            }
        }
    }
}