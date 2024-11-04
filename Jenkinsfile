pipeline {
    agent any

    stages {

    stage('Git') {
                steps {
                    echo 'Recup Code de Git : ';
                    git branch : 'hamrounif',
                    url :'https://github.com/enamouchi/5-win-neural-navigators.git';
                }
            }


       stage('Maven Clean') {
                   steps {
                       echo 'Nettoyage du Projet : ';
                       sh 'mvn clean';
                   }
               }

               stage('Maven Compile') {
                   steps {
                       echo 'Construction du Projet : ';
                       sh 'mvn compile';
                   }
               }

        stage('Run Tests') {
            steps {
                // Run tests using Maven
                sh 'mvn test'
            }
        }
        stage('SonarQue') {
                    steps {
                        echo 'Analyse de la Qualité du Code : ';
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=@Esprit19981998';
                    }
                }

                 stage('Maven Package') {
                            steps {
                                echo 'Création du livrable : ';
                                sh 'mvn package -DskipTests';
                            }
                        }
/*
                        stage('Maven Deploy') {
                                                    steps {
                                                        echo 'Création du livrable : ';
                                                        sh 'mvn deploy -DskipTests';
                                                    }
                                                }*/

        stage('Image') {
            steps {
                echo 'Création Image : ';
                sh 'docker build -t hamrounifiras/tp-foyer-image:1.0.0 .';
            }
        }

        stage('Dockerhub') {
            steps {
                echo 'Push Image to dockerhub : ';
                sh 'docker login -u hamrounifiras -p @Esprit19981998';
                sh 'docker push hamrounifiras/tp-foyer-image:1.0.0';
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Staet Backend + DB : ';
                sh 'docker compose up -d';
            }
        }
    }
}
