pipeline {

    agent any
    stages {/*
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

                     sh "gradle pushImage -DappVersion=0.13.0 -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword='$HUB_PASSWDORD'"

                }
            }
        }*/
        stage('Helm package'){

            steps{
                script{

                    def valuesPath = "$WORKSPACE/k8s/demo-k8s/values.yaml"
                    def valuesFile = readFile(valuesPath)
                    def valuesFileWithVersion = valuesFile.replaceAll('tag: latest','tag: 0.13.0')
                    sh "rm $valuesPath"
                    println "Guardando el archivo values en el path en el path: valuesPath"
                    writeFile file: valuesPath, text: valuesFileWithVersion
                    sh "helm package k8s/demo-k8s --version 0.13.0"
                }
            }
        }
    }
}