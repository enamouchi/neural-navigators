pipeline {
    agent any
    stages {
        stage('Récupération du code') {
            steps {
                // Clone le dépôt de votre projet et sélectionne la branche.
                git branch: 'oumou', url: 'https://github.com/enamouchi/5-win-neural-navigators.git'
            }
        }
        stage('Nettoyage et compilation avec Maven') {
            steps {
                // Lancer Maven avec les commandes clean et compile
                sh 'mvn clean compile'
            }
        }
        stage('Test Unitaire') {
            steps {
                echo 'Exécution des tests unitaires :'
                sh 'mvn test'
            }
        }
        stage('SonarQube') {
            steps {
                echo 'Analyse de la Qualité du Code :'
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Goyou2001***'
            }
        }
        stage('Maven Package') {
            steps {
                echo 'Création du livrable :'
                sh 'mvn package -DskipTests'
            }
        }
        stage('Deploy to Nexus') {
            steps {
                echo 'Déploiement sur Nexus :'
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Image') {
            steps {
                echo 'Création Image :'
                sh 'docker build -t oumou2001/tp-foyer:1.0.0 .'
            }
        }
        stage('Dockerhub') {
            steps {
                echo 'Push Image to Docker Hub :'
                sh 'docker login -u oumou2001 -p 1234goyou'
                sh 'docker push oumou2001/tp-foyer:1.0.0'
            }
        }
        stage('Docker-Compose') {
            steps {
                echo 'Start Backend + DB :'
                sh 'docker compose up -d'
            }
        }
        stage('Lancer Prometheus') {
            steps {
                echo 'Lancement de Prometheus :'
                sh 'docker start prometheus'
            }
        }
        stage('Lancer Grafana') {
            steps {
                echo 'Lancement de Grafana :'
                sh 'docker start grafana'
            }
        }
    }
    post {
        success {
            mail to: 'goyou3708@gmail.com',
                 subject: "Pipeline Success: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                 body: "Le pipeline Jenkins s'est terminé avec succès.\n\nJob: ${env.JOB_NAME}\nBuild: ${env.BUILD_NUMBER}\nStatut: SUCCESS\nConsultez les détails ici: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'goyou3708@gmail.com',
                 subject: "Pipeline Failed: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                 body: "Le pipeline Jenkins a échoué.\n\nJob: ${env.JOB_NAME}\nBuild: ${env.BUILD_NUMBER}\nStatut: FAILED\nConsultez les détails ici: ${env.BUILD_URL}"
        }
    }
}
