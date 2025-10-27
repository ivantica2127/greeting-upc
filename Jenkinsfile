pipeline {
    agent any

    tools {
        maven 'MavenSystem' // nombre configurado en Jenkins → Global Tool Configuration
    }

    environment {
        APP_NAME = 'greeting'
        DEPLOY_DIR = '/opt/app'
        JAR_NAME = "${APP_NAME}.jar"
        LOG_FILE = "${DEPLOY_DIR}/app.log"
        PORT = '8787'
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

        stage('Stop Old Instance') {
            steps {
                echo "Deteniendo instancia anterior (si existe)..."
                sh """
                    PID=\$(lsof -t -i:${PORT}) || true
                    if [ ! -z "\$PID" ]; then
                        echo "Deteniendo proceso \$PID en puerto ${PORT}..."
                        kill -9 \$PID
                        sleep 3
                    else
                        echo "No se encontró proceso en el puerto ${PORT}"
                    fi
                """
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Desplegando nueva versión..."
                sh """
                    cp target/*.jar ${DEPLOY_DIR}/${JAR_NAME}
                    nohup java -jar ${DEPLOY_DIR}/${JAR_NAME} --server.port=${PORT} > ${LOG_FILE} 2>&1 &
                    echo "Nueva versión ejecutándose en puerto ${PORT}"
                """
            }
        }
    }

    post {
        success {
            echo "Despliegue exitoso de ${APP_NAME} en el puerto ${PORT}"
        }
        failure {
            echo "Error durante el pipeline"
        }
    }
}