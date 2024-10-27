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
    }
}

