pipeline {
    agent any
    stages {
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Execution des tests unitaires ') {
            steps {
                sh ' mvn test'
            }
        }
        stage(' Analyse Sonarqube ') {
            steps {
                withSonarQubeEnv('sq1') {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN'
                }
            }
        }
    }
}

