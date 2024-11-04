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
    }
}
