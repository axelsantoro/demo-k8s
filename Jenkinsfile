pipeline {

    agent any
    stages {
        stage('Build Image') {
            agent {
                docker {
                    image 'gradle:5.0.0-jdk11'
                    args '-v $HOME/.gradle:/home/gradle/.gradle'
                }
            }
            steps {
                sh 'gradle buildImage'
            }
        }
        stage ('Push Image'){
            steps{

                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "dockerhub",
                          usernameVariable: 'HUB_USER', passwordVariable: 'HUB_PASSWDORD']]) {

                     sh "docker login -u $HUB_USER -p $HUB_PASSWDORD"
                     sh "docker push matitanio/demo-k8s:0.0.1-SNAPSHOT"

                }
            }

        }

    }
}