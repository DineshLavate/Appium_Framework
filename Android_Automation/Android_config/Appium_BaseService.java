package Android_config;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Appium_BaseService {

protected static AppiumDriverLocalService service;
	
    static File mainjs_filepath = new File("C:\\Users\\dinesh\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js");
	
    public static void start_AppiumServer(String mainjs_filepath, String ipAddress, int port) {
        try {
            File mainjsFile = new File(mainjs_filepath);

            if (service == null || !service.isRunning()) {
                service = new AppiumServiceBuilder()
                        .withAppiumJS(mainjsFile)   // File instead of String
                        .withIPAddress(ipAddress)   // Use function parameter
                        .usingPort(port)            // Use function parameter
                        .build();
                service.start();
                System.out.println("✅ Appium Server started at: " + ipAddress + ":" + port);
            } else {
                System.out.println("⚠️ Appium Server is already running.");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to start Appium Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void stop_AppiumServer() {
        try {
            if (service != null && service.isRunning()) {
                service.stop();
                System.out.println("🛑 Appium Server stopped.");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to stop Appium Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
