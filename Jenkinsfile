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
		stage('Test') {
        	steps {
	            bat 'mvn test'
	        }
        }
		stage('Package') {
        	steps {
	            bat 'mvn package -DskipTests=true'
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
		stage('Stop Container') {
        	steps {
	            bat 'docker stop %APP% || exit 0'
	        }
        }		
		stage('Remove Container') {
        	steps {
	            bat 'docker rm %APP% || exit 0'
	        }
        }
		stage('Remove Image') {
        	steps {
	            bat 'docker rmi %APP% || exit 0'
	        }
        }
		stage('Build Image') {
        	steps {
	            bat 'docker build -t %APP% .'
	        }
        }
		stage('Run Container') {
        	steps {
	            bat 'docker run -p 8080:8080 -t %APP%'
	        }
        }
    }
}