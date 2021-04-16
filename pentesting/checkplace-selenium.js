var By = Java.type('org.openqa.selenium.By');
var Thread = Java.type('java.lang.Thread');

var extSel = org.parosproxy.paros.control.Control.getSingleton().getExtensionLoader().getExtension(org.zaproxy.zap.extension.selenium.ExtensionSelenium.class)

var wd = extSel.getWebDriverProxyingViaZAP(1, "firefox");
wd.get("http://localhost:4200");
Thread.sleep(1000);
wd.get("http://localhost:4200/login");
wd.findElement(By.id("username")).sendKeys("fischer");
wd.findElement(By.id("password")).sendKeys("fischer");
Thread.sleep(1000);
wd.findElement(By.id("buttonLogin")).click();
Thread.sleep(1000);
wd.findElement(By.id("play")
wd.findElement(By.id("play")
wd.findElement(By.id("play")
wd.findElement(By.id("play")
