import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class UserAgent {
    String os;
    String osVersion;

    public String getBrowser() {
        return browser;
    }

    String browser;
    String browserVersion;

    public UserAgent(String userAgent) {
        if (userAgent != null) {
            UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
            ReadableUserAgent agent = parser.parse(userAgent);
            this.browser = agent.getName();
            VersionNumber browserVersionHelp = agent.getVersionNumber();
            this.browserVersion = browserVersionHelp.toVersionString();
            OperatingSystem osHelp = agent.getOperatingSystem();
            this.os = osHelp.getName();
            VersionNumber osVersionHelp = osHelp.getVersionNumber();
            this.osVersion = osVersionHelp.toVersionString();
        } else
            System.out.println("В строке не указан UserAgent");
    }

    public String toString(){

        return "ОС:"+this.os+"\n Браузер: "+this.browser;
    }
}
