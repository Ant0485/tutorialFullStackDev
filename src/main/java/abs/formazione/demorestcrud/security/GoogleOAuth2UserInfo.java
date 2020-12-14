package abs.formazione.demorestcrud.security;

import lombok.Getter;

import java.util.Map;

/*
This class is used to fetch the authenticated user's details from the Google OAuth2 provider.
While the provider usually returns them in JSON form (with a series of specific field that may vary for
another provider), Spring Security converts them already to a generic Map<String, Object>.
Google JSON -> https://developers.google.com/identity/protocols/oauth2/openid-connect
 */
public class GoogleOAuth2UserInfo {

    @Getter
    private Map<String, Object> attributes;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

}
