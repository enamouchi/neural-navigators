pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                git branch: 'emna',
                    url: 'https://github.com/enamouchi/5-win-neural-navigators.git'
            }
        }
        
        stage('Maven Clean') {
            steps {
                echo 'Nettoyage du Projet : ';
                sh 'mvn clean';
            }
        }

        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }
}
