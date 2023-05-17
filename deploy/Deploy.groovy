#!/usr/bin/env groovy

node {
    stage('Build') {
        steps {
            script {
                def propertiesFile = readFile 'gradle.properties'
                def versionProperty = findProperty(propertiesFile, 'version')

                // Incrementa a versão em 1
                def incrementedVersion = incrementVersion(versionProperty)

                // Atualiza a propriedade no arquivo gradle.properties
                propertiesFile = updateProperty(propertiesFile, 'version', incrementedVersion)

                // Salva o arquivo atualizado
                writeFile file: 'gradle.properties', text: propertiesFile
            }
        }
    }
}

def findProperty(fileContent, propertyName) {
    def properties = new Properties()
    properties.load(new ByteArrayInputStream(fileContent.bytes))
    properties.getProperty(propertyName)
}

def incrementVersion(version) {
    def versionParts = version.split('\\.')
    def lastPart = versionParts[-1].toInteger()
    versionParts[-1] = (lastPart + 1).toString()
    versionParts.join('.')
}

def updateProperty(fileContent, propertyName, newValue) {
    def properties = new Properties()
    properties.load(new ByteArrayInputStream(fileContent.bytes))
    properties.setProperty(propertyName, newValue)
    def outputStream = new ByteArrayOutputStream()
    properties.store(outputStream, null)
    outputStream.toString()
}