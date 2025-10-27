pipeline {
    agent any

    tools {
        maven 'MavenSystem' // o el nombre configurado en Jenkins
    }

    environment {
        APP_NAME = 'greeting'
        DEPLOY_DIR = '/opt/app'
        JAR_NAME = 'greeting.jar'
        SERVICE = 'greeting.service'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Clonando el repositorio..."
                git branch: 'main', url: 'https://github.com/ivantica2127/greeting-upc.git'
            }
        }

        stage('Build') {
            steps {
                echo "Compilando el proyecto..."
                sh 'mvn clean package -DskipTests -B -ntp'
            }
        }

        stage('Deploy') {
            steps {
                echo "Desplegando nueva versión..."
                sh '''
                    echo "Copiando nuevo JAR..."
                    sudo cp target/*.jar ${DEPLOY_DIR}/${JAR_NAME}

                    echo "Reiniciando servicio..."
                    sudo systemctl daemon-reload
                    sudo systemctl restart ${SERVICE}

                    echo "Verificando estado..."
                    sleep 5
                    sudo systemctl status ${SERVICE} --no-pager
                '''
            }
        }
    }

    post {
        success {
            echo "Despliegue exitoso en el puerto 8787"
        }
        failure {
            echo "Error durante el despliegue. Revisar logs en /opt/app/app.log"
        }
    }
}
