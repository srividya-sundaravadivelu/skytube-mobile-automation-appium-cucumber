pipeline {
    agent any

    tools {
        maven 'Maven_Home'
        jdk 'my JDK'
    }

    environment {
        ANDROID_HOME = "${env.ANDROID_HOME}"
        EMULATOR_NAME = "Medium_Phone_API_36.0"
        EMULATOR_PORT = "5554"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/srividya-sundaravadivelu/skytube-mobile-automation-appium-cucumber.git'
            }
        }

        stage('Print Env') {
              steps {
                bat 'set ANDROID_HOME'
                bat 'echo %USERPROFILE%'
                bat '%ANDROID_HOME%\\emulator\\emulator.exe -list-avds'
              }
        }


        stage('Start Android Emulator') {
            steps {
                script {
                    bat """
                    echo Starting Android emulator...
                    start /b %ANDROID_HOME%\\emulator\\emulator.exe -avd %EMULATOR_NAME% -no-window -no-audio -no-boot-anim -port %EMULATOR_PORT%
                    """
                    bat 'timeout /t 10 /nobreak'

                    timeout(time: 5, unit: 'MINUTES') {
                        waitUntil {
                            def booted = bat (
                                script: "%ANDROID_HOME%\\platform-tools\\adb.exe -s emulator-%EMULATOR_PORT% shell getprop sys.boot_completed",
                                returnStdout: true
                            ).trim().replaceAll("\\r|\\n", "")
                            echo "Boot status: ${booted}"
                            return booted == '1'
                        }
                    }
                    echo "Android emulator is booted and ready."
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                        bat 'mvn clean test'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'report.html', fingerprint: true

            script {
                echo "Stopping emulator..."
                bat "%ANDROID_HOME%\\platform-tools\\adb.exe -s emulator-%EMULATOR_PORT% emu kill"
            }
        }
    }
}
