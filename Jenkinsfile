pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository from the specified branch
                git url: 'https://github.com/enamouchi/5-win-neural-navigators.git', branch: 'hamrounif'
            }
        }
        stage('Clean and Compile') {
            steps {
                // Clean and compile the project using Maven
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                // Run tests using Maven
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Perform SonarQube analysis with SonarQube server environment
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'SonarQubeToken2', variable: 'SONAR_TOKEN')]) {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=neural_navigators_project -Dsonar.login=$SONAR_TOKEN'
                    }
                }
            }
        }
    }
}
