pipeline {
    agent any
    stages {

        stage('Récupération du code') {
            steps {
                // Clone le dépôt de ton projet et sélectionne la branche.
                git branch: 'oumou', url: 'https://github.com/enamouchi/5-win-neural-navigators.git'
            }
        }
        stage('Nettoyage et compilation avec Maven') {
            steps {
                // Lancer Maven avec les commandes clean et compile
                sh 'mvn clean compile'
            }
        }

            stage('SonarQube') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Goyou2001***';
            }
        }

          stage('Maven Package') {
            steps {
                echo 'Création du livrable : ';
                sh 'mvn package -DskipTests';
            }
        }

         stage('Deploy to Nexus') {
            steps {
                echo 'Déploiement sur Nexus : '
                sh 'mvn deploy -DskipTests'
            }
        }

         stage('Image') {
            steps {
                echo 'Création Image : ';
                sh 'docker build -t oumou/tp-foyer:1.0.0 .';
            }
        }

        stage('Dockerhub') {
            steps {
                echo 'Push Image to dockerhub : ';
               sh 'docker login -u oumou2001 -p 1234goyou';
              sh 'docker push oumou/tp-foyer:1.0.0';
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Start Backend + DB : ';
                sh 'docker compose up -d';
            }
        }

    }
}
