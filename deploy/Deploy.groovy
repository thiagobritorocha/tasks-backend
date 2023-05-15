#!groovy
pipeline {
    agent any
    stages {
        stage("One") {
            GroovyShell shell = new GroovyShell()
            def base = shell.parse(new File('./deploy/_Base.groovy'))
            base.startPipeline()
        }
    }
}