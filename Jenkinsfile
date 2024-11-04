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
          
       
         stage('SonarQube') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Azerty12345.';
            }
        }
          stage('Test Stage') {
    steps {
        echo 'Running unit tests...'
        sh 'mvn test'
                }
            }
        stage('nexus') {
            steps {
                echo 'Création du livrable : ';
                sh 'mvn package -DskipTests';
            }
        }
        stage('Deploy Nexus') {
            steps {
                echo 'Déploiement vers Nexus : ';
                sh 'mvn deploy -DskipTests'
            }
        }
       stage('Build Image') {
            steps {
                echo 'Building Docker Image:'
                // Build the Docker image
                sh 'docker build -t emnanamouchi/tp-foyer:1.0.0 .'
            }
        }
  

       stage('Dockerhub') {
    steps {
        echo 'Pushing Image to Docker Hub:'
        // Login to Docker Hub with your account credentials
        sh 'docker login -u emnanamouchi -p dckr_pat_wluyMwwVKfNnOjd2bDxnzFMVftU'
        // Push the built image to Docker Hub
        sh 'docker push emnanamouchi/tp-foyer:1.0.0'
    }
}

        stage('Docker Compose') {
            steps {
                echo 'Starting Backend + DB using Docker Compose:'
                // Start the services defined in docker-compose.yml
                sh 'docker compose up -d'
            }
        }
        
    }
}
