#!groovy
pipeline {
    agent any
    stages {
        stage("build") {
            steps {
                step {
                    withGroovy(tool: '4.0.8') {
                        def gradleProperties = readProperties file: 'gradle.properties'
                        def currentVersion = gradleProperties['version']
                        def versionParts = currentVersion.split('\\.')
                        def majorVersion = Integer.parseInt(versionParts[0])
                        def minorVersion = Integer.parseInt(versionParts[1])
                        minorVersion++
                        def newVersion = "${majorVersion}.${minorVersion}"
                        echo "${newVersion}"
                        gradleProperties['version'] = newVersion
                        writeProperties file: 'gradle.properties', properties: gradleProperties
                    }
                }
            }
        }
        stage("test") {
            steps {
                echo 'test'
            }
        }
        stage("deploy") {
            steps {
                echo 'deploy'
            }
        }
    }
}