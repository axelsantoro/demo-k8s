pipeline {

    agent any
    stages {
        stage('Build Image'){
            agent {
                docker {
                    image 'gradle:5.0.0-jdk11'
                    args '-v $HOME/.gradle:/home/gradle/.gradle'
                }
            }
            steps {
                env.VERSION =  0.1.0
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "d-hub",
                                          usernameVariable: 'HUB_USER', passwordVariable: 'HUB_PASSWDORD']]) {

                     sh "gradle pushImage -DappVersion=${env.VERSION} -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword='$HUB_PASSWDORD'"

                }
            }
        }
        stage('Helm package'){

            steps{
                script{

                    def valuesPath = "$WORKSPACE/k8s/demo-k8s/values.yaml"
                    def valuesFile = readFile(valuesPath)
                    def tagVersion = "tag: ${env.VERSION}"
                    def valuesFileWithVersion = valuesFile.replaceAll('tag: latest',tagVersion)
                    sh "rm $valuesPath"
                    println "Guardando el archivo values en el path en el path: valuesPath"
                    writeFile file: valuesPath, text: valuesFileWithVersion
                    sh "helm package k8s/demo-k8s --version ${env.VERSION}"
                }
            }
        }
    }
}