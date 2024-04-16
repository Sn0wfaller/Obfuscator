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
        stage("Run tests") {
            steps {
                bat 'mvn test'
            }
        }
    }
}