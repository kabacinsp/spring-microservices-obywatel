package pl.kabacinsp.obywatel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class ConfigProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    public static class Auth {
        private String secretToken;
        private long expirationToken;

        public String getSecretToken() {
            return secretToken;
        }

        public void setSecretToken(String secretToken) {
            this.secretToken = secretToken;
        }

        public long getExpirationToken() {
            return expirationToken;
        }

        public void setExpirationToken(long expirationToken) {
            this.expirationToken = expirationToken;
        }
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 setAuthorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
