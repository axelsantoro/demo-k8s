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

                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "d-hub",
                                          usernameVariable: 'HUB_USER', passwordVariable: 'HUB_PASSWDORD']]) {

                     sh "gradle pushImage -DappVersion=0.11.0 -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword='$HUB_PASSWDORD'"

                }
            }
        }
        stage('Helm package'){

            steps{
                sh "helm package k8s/demo-k8s --version 0.12.0"
            }
        }
    }
}