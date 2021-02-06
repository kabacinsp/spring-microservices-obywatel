package pl.kabacinsp.obywatel.security.oauth.users;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuth2UserInfo {

    public GoogleOAuthUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}
