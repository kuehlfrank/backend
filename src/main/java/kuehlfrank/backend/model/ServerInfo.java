package kuehlfrank.backend.model;

import lombok.Data;

@Data
public class ServerInfo {
    private final String version;
    private final String activeProfile;

    public ServerInfo(String version, String activeProfile) {
        this.version = version;
        this.activeProfile = activeProfile;
    }
}