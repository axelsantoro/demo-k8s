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

                     sh "gradle pushImage -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword=${HUB_PASSWDORD}"

                }
            }
        }
    }
}