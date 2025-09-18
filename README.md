# Appium_Framework
This is an Android Automation framework with Java,Maven,TestNG
# 📱 Appium Automation Framework  

![Java](https://img.shields.io/badge/Java-1.8%2B-blue?logo=java&logoColor=white)  
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apache-maven&logoColor=white)  
![TestNG](https://img.shields.io/badge/TestNG-Framework-brightgreen?logo=testng&logoColor=white)  
![JUnit5](https://img.shields.io/badge/JUnit-5-green?logo=junit5&logoColor=white)  
![Appium](https://img.shields.io/badge/Appium-Mobile%20Testing-purple?logo=appium&logoColor=white)  
![Platform](https://img.shields.io/badge/Platform-Android-lightgrey?logo=android&logoColor=green)  

This project is a **Java + Appium + TestNG/JUnit** based automation framework designed for testing **Android applications**.  
It follows a modular structure with support for Page Object Model (POM), reporting, logging, and CI/CD integration.

---

## 📂 Project Structure

Appium_Framework
├── src
│   ├── main/java            # Reserved for future reusable libraries (currently unused)
│   ├── main/resources       # Reserved for configs (currently unused)
│   ├── test/java
│   │   └── Android_Automation
│   │       ├── Android_config   # Appium driver setup, capabilities, environment config
│   │       ├── PageObjects      # Page Object Model classes (UI mapping + actions)
│   │       ├── TestCases        # TestNG test classes
│   │       └── Utilities        # Common utilities (helpers, reusable functions, logs, waits, etc.)
│   └── test/resources           # Test resources like testng.xml/config files
│
├── AndroidBuilds            # APKs or app bundles used for testing
├── Logs                     # Execution logs
├── ReportGenerator           # Custom reporting utilities (ExtentReports / Allure)
├── screenshots              # Captured screenshots (on failure / reporting)
├── TestData                 # Test data files (Excel, JSON, CSV, etc.)
├── target                   # Maven build output (compiled classes, reports)
├── test-output              # TestNG default reports
├── pom.xml                  # Maven dependencies & build config
└── testng.xml               # TestNG suite configuration


---

## ⚙️ Tech Stack

- **Language**: Java  
- **Automation Tool**: Appium (UiAutomator2)  
- **Test Framework**: TestNG / JUnit 5  
- **Build Tool**: Maven  
- **Design Pattern**: Page Object Model (POM)  
- **Reporting**: TestNG reports + (custom report generator if enabled)  
- **Logging**: Log files stored in `/Logs`  
- **CI/CD**: Ready for integration with Jenkins, GitHub Actions, or BrowserStack/SauceLabs  

---

## 🚀 How to Run Tests

### 1️⃣ Prerequisites
- Install **Java JDK (>= 1.8)**  
- Install **Node.js** (required for Appium)  
- Install **Appium Server** (Desktop or via `npm install -g appium`)  
- Set up **Android SDK / Emulator / Physical Device**  
- Install **Maven**  

### 2️⃣ Clone the Repo
```bash
git clone https://github.com/your-username/Appium_Framework.git
cd Appium_Framework

3️⃣ Install Dependencies
mvn clean install

4️⃣ Run Tests
mvn test
mvn clean test -DsuiteXmlFile=testng.xml

📊 Reports & Logs
Test Reports → test-output/
Logs → Logs/
Screenshots → screenshots/

🌐 Cloud Execution (Optional)
To run tests on BrowserStack / Sauce Labs, update credentials in Android_config and pass capabilities accordingly.

👨‍💻 Author

Name Dinesh Lavate
Automation QA | Appium | Selenium | TestNG | CI/CD


