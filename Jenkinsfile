pipeline {
    tools {
        maven 'Maven_3.6.3'
        jdk 'JDK8'
    }
    stages {
        stage('Build') {
           bat 'mvn clean compile'
        }
    }
}