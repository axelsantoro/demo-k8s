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

                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "axel-dh",
                                          usernameVariable: 'HUB_USER', passwordVariable: 'HUB_PASSWDORD']]) {

                     sh "gradle pushImage -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword='$HUB_PASSWDORD'"

                }
            }
        }
        stage('Deploy'){

            steps{
                sh "helm upgrade 0.1.0 k8s/demo-k8s --install --namespace dev"
            }
        }
    }
}