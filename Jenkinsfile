pipeline {
	agent any
	environment {
        APP = 'booklog'
		DB_HOST = '192.168.1.66'
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
		stage('SonarQube analysis') {
			steps {
				withSonarQubeEnv('SonarQube') {
				  bat 'C:/Users/Ashwin/dev_tools/sonar-scanner-4.6.0.2311-windows/bin/sonar-scanner'
				} 
			}
		}
		stage('Test') {
        	steps {
	            bat 'mvn -Dtest=BookLogIntegrationTest test'
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
	            bat 'docker run -e DB_HOST=%DB_HOST% -v C:/Users/Ashwin/dev_tools/app_logs/prod:/var/app_logs/booklog -d --name %APP%  -p 8080:8080 -t %APP%'
	        }
        }
    }
}