pipeline {
	agent any
    tools {
        maven 'Maven_3.6.3'
        jdk 'JDK8'
    }
    stages {
        stage('Build') {
        	steps {
	            bat 'clean compile'
	        }
        }
    }
}