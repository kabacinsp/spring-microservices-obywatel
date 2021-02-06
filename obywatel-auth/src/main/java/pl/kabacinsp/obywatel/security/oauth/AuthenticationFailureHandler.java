package pl.kabacinsp.obywatel.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kabacinsp.obywatel.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static pl.kabacinsp.obywatel.security.oauth.AuthorizationOAuth2Request.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Autowired AuthorizationOAuth2Request authorizationOAuth2Request;

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    String targetUrl =
        CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue)
            .orElse(("/"));

    targetUrl =
        UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("error", exception.getLocalizedMessage())
            .build()
            .toUriString();

    authorizationOAuth2Request.removeAuthorizationRequestCookies(request, response);

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
