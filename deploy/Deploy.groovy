#!groovy
pipeline {
    agent any
    stages {
        stage("One") {
            def base = load "./deploy/_Base.groovy"
            base.startPipeline()
        }
    }
}