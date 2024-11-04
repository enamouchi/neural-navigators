pipeline {
  agent any


   stages {
        stage('GIT') {
            steps {
                git branch: 'rebhiarwa_5WIN_neuralnavigators',
                    url: 'https://github.com/enamouchi/5-win-neural-navigators.git'
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
        stage('Docker image') {
            steps {
                echo 'Building Docker Image:'
                // Build the Docker image
                sh 'docker build -t arwarebhi/tp-foyer:1.0.0 .'
                sh 'docker tag arwarebhi/tp-foyer:1.0.0 arwarebhi/tp-foyer:latest'
            }
        }
    }
}
