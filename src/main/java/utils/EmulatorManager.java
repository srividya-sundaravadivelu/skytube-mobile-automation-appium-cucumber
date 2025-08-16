package utils;

import logger.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmulatorManager {

    @SuppressWarnings("deprecation")
	public static void startEmulator(String avdName) throws IOException, InterruptedException {
        Log.info("Starting Emulator: " + avdName);

        // Ensure adb server is running
        restartAdbServer();

        // Launch emulator in a visible window
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "emulator", "-avd", avdName);
        pb.redirectErrorStream(true);
        pb.start();

        // Wait for emulator to fully boot
        boolean booted = false;
        Log.info("Waiting for emulator to boot...");
        long startTime = System.currentTimeMillis();
        long timeout = 300_000; // 5 minutes

        while (!booted && System.currentTimeMillis() - startTime < timeout) {
            try {
                Process checkBoot = Runtime.getRuntime().exec("adb shell getprop sys.boot_completed");
                BufferedReader reader = new BufferedReader(new InputStreamReader(checkBoot.getInputStream()));
                String output = reader.readLine();
                reader.close();
                checkBoot.destroy();

                if ("1".equals(output)) {
                    booted = true;
                } else {
                    Log.info("Emulator not booted yet, waiting 5 seconds...");
                    Thread.sleep(5000);
                }
            } catch (IOException e) {
                Log.warn("ADB not ready yet, retrying in 5 seconds...");
                Thread.sleep(5000);
            }
        }

        if (!booted) {
            Log.error("Emulator failed to boot in time: " + avdName);
            throw new RuntimeException("Emulator boot timed out: " + avdName);
        }

        Log.info("Emulator booted successfully: " + avdName);
    }

    @SuppressWarnings("deprecation")
	private static void restartAdbServer() throws IOException, InterruptedException {
        Log.info("Restarting adb server...");
        Process kill = Runtime.getRuntime().exec("adb kill-server");
        kill.waitFor();

        Process start = Runtime.getRuntime().exec("adb start-server");
        start.waitFor();
        Log.info("adb server restarted successfully.");
    }
}