pipeline {
	agent any
	environment {
        APP = 'booklog'
    }
    tools {
        maven 'Maven_3.6.3'
        jdk 'JDK8'
    }
    stages {
        stage('Clean Build') {
        	steps {
	            sh 'mvn clean compile'
	        }
        }
        stage('List Images') {
        	steps {
	            sh 'docker images'
	        }
        }
		stage('List Container') {
        	steps {
	            sh 'docker ps -a'
	        }
        }
		stage('Stop Container') {
        	steps {
	            sh 'docker stop ${APP}'
	        }
        }
    }
}