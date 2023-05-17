@Library('pipeline-utility-steps') _

pipeline {
    agent any

    stages {
        stage('Increment Version') {
            steps {
                script {
                    def gradleProperties = readProperties file: 'gradle.properties'
                    def currentVersion = gradleProperties['versão']
                    def versionParts = currentVersion.split('\\.')
                    def majorVersion = Integer.parseInt(versionParts[0])
                    def minorVersion = Integer.parseInt(versionParts[1])
                    def patchVersion = Integer.parseInt(versionParts[2])
                    patchVersion++
                    def newVersion = "${majorVersion}.${minorVersion}.${patchVersion}"
                    gradleProperties['versão'] = newVersion
                    writeProperties file: 'gradle.properties', properties: gradleProperties
                }
            }
        }

        // Outros estágios do pipeline...

        stage('Build') {
            steps {
                // Etapas de construção do seu projeto
            }
        }

        // Mais estágios do pipeline...

    }

    post {
        always {
            cleanWs() // Limpa o workspace após a execução do pipeline
        }
    }
}