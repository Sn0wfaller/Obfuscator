pipeline {
    agent any
    
    stages {
        stage("Compile code") {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage("Tests") {
            when {
                expression {
                    return env.GIT_BRANCH == "origin/feature/*"
                }
            }
            steps {
                bat 'mvn test'
            }
        }
        stage("Static analyse") {
            when {
                expression {
                    return env.GIT_BRANCH == "origin/develop"
                }
            }
            steps {
                bat 'mvn checkstyle:check'
            }
        }
        stage("Report") {
            when {
                expression {
                    return env.GIT_BRANCH == "origin/feature/*"
                }
            }
            steps {
                junit testResults: '**/surefire-reports/*.xml'
                jacoco()
            }
        }
        stage("Install") {
            steps {
                bat 'mvn install'
            }
        }
        stage("Publish") {
            steps {
                bat 'copy "main\\target\\main-1.0-SNAPSHOT-jar-with-dependencies.jar" "C:\\main-1.0.jar"'
            }
        }
    }
}