package abs.formazione.demorestcrud.security;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import abs.formazione.demorestcrud.security.EmployeePrincipal;
import abs.formazione.demorestcrud.security.GoogleOAuth2UserInfo;
import abs.formazione.demorestcrud.security.OAuth2AuthenticationProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
This service will be invoked after the used has authenticated through the social login
and Spring Security has obtained an access_token for the current request.
The service will retrieve the authenticated user information and return an object implementing
the OAuth2User and UserDetails interfaces.
 */
@Service
public class EmployeeOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        /*
         * Using the default implementation to load the User from the UserRequest.
         * The OAuth2User obtained from the request is a Spring representation of the authenticated user.
         */
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        // Retrieving the attributes from the OAuth2User object, following a Google social login.
        GoogleOAuth2UserInfo oAuth2UserInfo = new GoogleOAuth2UserInfo(oAuth2User.getAttributes());

        // Getting the email of the authenticated user.
        String oAuth2_email = oAuth2UserInfo.getEmail();

        try {
            if (oAuth2_email.isEmpty()) {
                // Checking if the OAuth2 provider has provided the email correctly.
                throw new OAuth2AuthenticationProcessingException("Email not found from the Google OAuth2 provider.");
            }

            Optional<Employee> employeeOptional = repository.findByEmail(oAuth2_email);
            Employee employee;
            // Checking if a user with the specified email is present in the database.
            if (employeeOptional.isPresent()) {
                employee = employeeOptional.get();
                // A custom extension of the Employee class implementing both OAuth2user and UserDetails is returned.
                return new EmployeePrincipal(employee, oAuth2User.getAttributes());
            } else {
                throw new OAuth2AuthenticationProcessingException("No user with the associated email saved " +
                        "in the current database.");
            }

        } catch (Exception ex) {
            // Throwing this exception will trigger our custom FailureHandler, as specified in the Config class.
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }


}
