pipeline {
    agent any

    stages {
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Execution des tests unitaires') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Analyse Sonarqube') {
            steps {
                withSonarQubeEnv('sq1') {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                // Déployer le projet sur Nexus en ignorant les tests
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
        stage('Build Image') {
            steps {
                echo 'Building Docker Image:'
                // Build the Docker image
                sh 'docker build -t arwarebhi/tp-foyer:1.0.0 .'
                sh 'docker tag arwarebhi/tp-foyer-app:1.0.0 arwarebhi/tp-foyer-app:latest'
            }
        }
    }
}
