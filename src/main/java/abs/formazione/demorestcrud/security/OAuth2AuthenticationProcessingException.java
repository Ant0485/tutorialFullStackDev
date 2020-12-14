package abs.formazione.demorestcrud.security;

import org.springframework.security.core.AuthenticationException;

/*
Definition of a new exception to describe an error that happened during the processing of the
OAuth2User and may be outside of the OAuth2 provider and inside our applicatio.ìn.
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    public OAuth2AuthenticationProcessingException(String msg, Throwable t){
        super(msg, t);
    }

    public OAuth2AuthenticationProcessingException(String msg){
        super(msg);
    }
}
