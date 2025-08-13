pipeline {
    agent any
    
    environment {
        ANDROID_HOME = "C:\\Users\\srivi\\AppData\\Local\\Android\\Sdk"
        ANDROID_AVD_HOME = "C:\\Users\\srivi\\.android\\avd"
        EMULATOR_NAME = "Medium_Phone_API_36.0"
        EMULATOR_PORT = "5554"
        PATH = "${env.PATH};${env.ANDROID_HOME}\\platform-tools;${env.ANDROID_HOME}\\emulator"
    }
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/srividya-sundaravadivelu/skytube-mobile-automation-appium-cucumber.git'
            }
        }

        stage('Start Android Emulator') {
            steps {
                script {
                    bat """
                    echo Starting Android emulator...
                    start /b ${ANDROID_HOME}\\emulator\\emulator.exe -avd ${EMULATOR_NAME} -no-window -no-audio -no-boot-anim -port ${EMULATOR_PORT}
                    timeout /t 60 /nobreak
                    """

                    timeout(time: 5, unit: 'MINUTES') {
                        waitUntil {
                            def booted = bat(
                                script: "${ANDROID_HOME}\\platform-tools\\adb.exe -s emulator-${EMULATOR_PORT} shell getprop sys.boot_completed",
                                returnStdout: true
                            ).trim()
                            def lines = booted.readLines()
                            def lastLine = lines[-1].trim()
                            echo "Boot status: ${lastLine}"
                            return lastLine == '1'
                        }
                    }
                    echo "Android emulator is booted and ready."
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            script {
                echo "Stopping emulator..."
                bat "${ANDROID_HOME}\\platform-tools\\adb.exe -s emulator-${EMULATOR_PORT} emu kill || echo Emulator already stopped."
            }
        }
    }
}
