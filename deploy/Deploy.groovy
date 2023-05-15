#!groovy
pipeline {
    agent any
    stages {
        stage any
        stage("One") {
            def base = load "./deploy/_Base.groovy"
            base.startPipeline()
        }
    }
}