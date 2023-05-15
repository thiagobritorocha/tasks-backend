#!groovy
@Library('slack-helper')
import java.lang.Object

def startPipeline() {
    try {
        sh "java -version"

        incrementVersion()

    } catch (Exception e) {
        throw e
    }
}

def incrementVersion() {
    script {
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
return this