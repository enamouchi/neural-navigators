pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository from the specified branch
                git url: 'https://github.com/enamouchi/5-win-neural-navigators.git', branch: 'hamrouni'
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
                // Perform SonarQube analysis with credentials
                withCredentials([string(credentialsId: 'SonarQubeToken', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=neural_navigators_project -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
       
    }
}
