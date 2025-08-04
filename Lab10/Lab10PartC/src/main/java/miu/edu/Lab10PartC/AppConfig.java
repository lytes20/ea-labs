package miu.edu.Lab10PartC;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String name;
    private String version;
    private String serverUrl;
    private String serverName;
    private List<String> countries;

    // Getters and setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getServerUrl() { return serverUrl; }
    public void setServerUrl(String serverUrl) { this.serverUrl = serverUrl; }

    public String getServerName() { return serverName; }
    public void setServerName(String serverName) { this.serverName = serverName; }

    public List<String> getCountries() { return countries; }
    public void setCountries(List<String> countries) { this.countries = countries; }
}

