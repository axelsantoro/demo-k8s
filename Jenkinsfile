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
            steps('Create Version'){

                step{
                    script{
                        def currentVersion = sh(script: 'helm search local/demo-k8s | cut -f2 | sed -n 2p',returnStdout: true).trim()
                        def splittedVersion = currentVersion.split('\\.')
                        env.VERSION = splittedVersion[0] + '.' + (splittedVersion[1].toInteger() + 1) + '.' + splittedVersion[2]
                        printn "nueva version[${env.VERSION}]"
                    }
                }
            }

            steps {
                script {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "d-hub",
                                              usernameVariable: 'HUB_USER', passwordVariable: 'HUB_PASSWDORD']]) {

                         sh "gradle pushImage -DappVersion=${env.VERSION} -DdockerRegistyUsername=${HUB_USER} -DdockerRegistyPassword='$HUB_PASSWDORD'"

                    }
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