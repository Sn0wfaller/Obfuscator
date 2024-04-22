pipeline {
    agent any
    
    stages {
        stage("Checkout") {
            steps {
                checkout scmGit(branches: [[name: '*/feature/*'], [name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Sn0wfaller/Obfuscator/']])
            }
        }
        stage("Compile code") {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage("Tests") {
        	when {
        		branch 'feature/*'
        	}
            steps {
                bat 'mvn test'
            }
        }
        stage("Static analyse") {
        	when {
        		branch 'develop'
        	}
            steps {
                bat 'mvn checkstyle:check'
            }
        }
        stage("Report") {
            when {
        		branch 'feature/*'
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