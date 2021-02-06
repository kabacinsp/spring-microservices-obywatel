package pl.kabacinsp.obywatel.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.kabacinsp.obywatel.exception.OAuth2AuthenticationProcessException;
import pl.kabacinsp.obywatel.model.AuthProvider;
import pl.kabacinsp.obywatel.model.UserDTO;
import pl.kabacinsp.obywatel.repository.UserRepository;
import pl.kabacinsp.obywatel.security.UserPrincipal;
import pl.kabacinsp.obywatel.security.oauth.users.OAuth2UserInfo;
import pl.kabacinsp.obywatel.security.oauth.users.OAuth2UserInfoFactory;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    try {
      return processOAuth2User(oAuth2UserRequest, oAuth2User);
    } catch (AuthenticationException ex) {
      throw ex;
    } catch (Exception ex) {
      // Throwing an instance of AuthenticationException will trigger the
      // OAuth2AuthenticationFailureHandler
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    OAuth2UserInfo oAuth2UserInfo =
        OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.getClientRegistration().getRegistrationId(),
            oAuth2User.getAttributes());
    if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessException("Email not found from OAuth2 provider");
    }

    Optional<UserDTO> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
    UserDTO user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      if (!user.getProvider()
          .equals(
              AuthProvider.valueOf(
                  oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
        throw new OAuth2AuthenticationProcessException(
            "Looks like you're signed up with "
                + user.getProvider()
                + " account. Please use your "
                + user.getProvider()
                + " account to login.");
      }
      user = updateExistingUser(user, oAuth2UserInfo);
    } else {
      user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
    }

    return UserPrincipal.create(user, oAuth2User.getAttributes());
  }

  private UserDTO registerNewUser(
      OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    UserDTO user = new UserDTO();

    user.setProvider(
        AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oAuth2UserInfo.getId());
    user.setFirstName(oAuth2UserInfo.getName());
    user.setEmail(oAuth2UserInfo.getEmail());
    return userRepository.save(user);
  }

  private UserDTO updateExistingUser(UserDTO existingUser, OAuth2UserInfo oAuth2UserInfo) {
    existingUser.setFirstName((oAuth2UserInfo.getName()));
    return userRepository.save(existingUser);
  }
}
