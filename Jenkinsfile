pipeline {
    agent any
    
    stages {
        stage("Checkout") {
            steps {
                git branch: 'develop', url: 'https://github.com/Sn0wfaller/Obfuscator.git'
            }
        }
        stage("Build") {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage("Static analyse") {
            steps {
                bat 'mvn checkstyle:check'
            }
        }
        stage("Test coverage") {
            steps {
                bat 'mvn test jacoco:report jacoco:check@default-check'
            }
        }
    }
}