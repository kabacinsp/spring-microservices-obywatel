package pl.kabacinsp.obywatel.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationProcessException extends AuthenticationException {
    public OAuth2AuthenticationProcessException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2AuthenticationProcessException(String msg) {
        super(msg);
    }
}