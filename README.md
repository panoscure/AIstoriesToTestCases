Skeleton project for running API/desktop/mobile tests in various flavors (DesktopWeb,DesktoSimulatedMobileWeb, mobile Android/iOS, real devices simulatos and also native apps and mobile web)

1. In order to define the platform to run edit/choose the relevant env variable in pom.xml file e.g.:

   <TARGET_PLATFORM>iOSApp</TARGET_PLATFORM>
   <TARGET_PLATFORM>AndroidApp</TARGET_PLATFORM
   <TARGET_PLATFORM>DesktopWeb</TARGET_PLATFORM>

   This can be overriden when running from command line or from jenkins e.g. :
   mvn -DxmlFilePath=target/test-classes/suites.mobile/LoginSuite.xml -DtargetPlatform=AndroidApp clean install

2. Before running mobile tests it is mandatory to launch appium server from cmd:
   appium -a 127.0.0.1 -p 4723 --base-path /wd/hub --session-override --use-plugins=relaxed-caps --relaxed-security

   Specifically for iOS we need to launch also the webdriveragent service. From command line run:
   xcodebuild clean -project /Users/automation/.appium/node_modules/appium-xcuitest-driver/node_modules/appium-webdriveragent/WebDriverAgent.xcodeproj -scheme WebDriverAgentRunner -allowProvisioningUpdates -destination 'id=00008110-000518122105401E' test
   and then we need to unlock the iPhone with passphrase manually (cannot be done with automation)

3. Framework supports automatic detection of mobile devices (Android and iOS) so provided that on the running machine there is one Android and one iPhone,
   there is no reason to provide specific device information. In a different case if we have more and we want to explicit define a device we can provide the relevant
   info in the property files.

4. After running any TestNG XML Suite we can generate the Allure report by running in maven command line:
   "mvn:allure report" -> Generates the /target/site and we can open the index.html manually to see the report
   "mvn:allure serve" -> Generates the report and launhes it automatically

   Console logs can be found also under /test-output/logs


