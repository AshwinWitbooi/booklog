pipeline {
	agent any
    tools {
        maven 'Maven_3.6.3'
        jdk 'JDK8'
    }
    stages {
        stage('Clean Build') {
        	steps {
	            bat 'mvn clean compile'
	        }
        }
        stage('Test') {
        	steps {
	            bat 'mvn test'
	        }
        }
    }
}