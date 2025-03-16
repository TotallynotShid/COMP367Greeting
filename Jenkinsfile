pipeline {
    agent any

    stages {
        // 4a. Checkout Stage
        stage('Checkout') {
            steps {
                checkout scm  // Checks out code from the configured SCM (GitHub)
            }
        }

        // 4b. Build Maven Project Stage (with Docker agent)
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-21'  // Maven + Java 21
                    args '-v $HOME/.m2:/root/.m2'  // Cache Maven dependencies
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'  // Skip tests to match Dockerfile
            }
        }

        // 4d. Docker Login Stage
        stage('Docker Login') {
            steps {
                // 4c. Using Jenkins Credentials (must be configured in Jenkins)
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                }
            }
        }

        // 4e. Docker Build Stage
        stage('Docker Build') {
            steps {
                sh 'docker build -t yourdockerhubusername/my-spring-app:latest .'
            }
        }

        // 4f. Docker Push Stage
        stage('Docker Push') {
            steps {
                sh 'docker push yourdockerhubusername/my-spring-app:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully! Image pushed to Docker Hub.'
        }
        failure {
            echo 'Pipeline failed! Check logs for errors.'
        }
    }
}