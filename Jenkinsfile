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
	            bat 'mvn clean compile'
	        }
        }
        stage('List Images') {
        	steps {
	            bat 'docker images'
	        }
        }
		stage('List Container') {
        	steps {
	            bat 'docker ps -a'
	        }
        }
		stage('Create Image') {
        	steps {
	            bat 'docker build -t ${APP} .'
	        }
        }
    }
}