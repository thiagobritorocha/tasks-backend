#!groovy
@Library('slack-helper')
import java.lang.Object

node("java14") {

    checkout scm
    def base = load "./deploy/_Base.groovy"

    base.startPipeline()
}