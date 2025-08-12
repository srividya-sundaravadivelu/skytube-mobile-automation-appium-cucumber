pipeline {
    agent any

    environment {
        ANDROID_HOME = "${env.ANDROID_HOME}"
        ANDROID_AVD_HOME = "${env.ANDROID_AVD_HOME}"
        EMULATOR_NAME = "Medium_Phone_API_36.0"
        EMULATOR_PORT = "5554"
    }

    stages {
        stage('Start Android Emulator') {
            steps {
                script {
                    bat """
                    echo Starting Android emulator...
                    start /b %ANDROID_HOME%\\emulator\\emulator.exe -avd %EMULATOR_NAME% -no-window -no-audio -no-boot-anim -port %EMULATOR_PORT%
                    """
                    waitUntil {
                        def booted = bat(
                            script: "%ANDROID_HOME%\\platform-tools\\adb.exe -s emulator-%EMULATOR_PORT% shell getprop sys.boot_completed",
                            returnStdout: true
                        ).trim()
                        echo "Boot status: ${booted}"
                        return booted == '1'
                    }
                    sleep time: 10, unit: 'SECONDS'  // add some buffer after boot
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
                try {
                    bat "%ANDROID_HOME%\\platform-tools\\adb.exe -s emulator-%EMULATOR_PORT% emu kill"
                } catch (err) {
                    echo "Failed to stop emulator. It might already be stopped."
                }
            }
        }
    }
}
