package pl.kabacinsp.obywatel.security.oauth.users;

import pl.kabacinsp.obywatel.exception.OAuth2AuthenticationProcessException;
import pl.kabacinsp.obywatel.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

  public static OAuth2UserInfo getOAuth2UserInfo(
      String registrationId, Map<String, Object> attributes) {
    if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
      return new GoogleOAuthUserInfo(attributes);
    } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
      return new FacebookOAuthUserInfo(attributes);
    } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
      return new GithubOAuthUserInfo(attributes);
    } else {
      throw new OAuth2AuthenticationProcessException(
          "Sorry! Login with " + registrationId + " is not supported yet.");
    }
  }
}
