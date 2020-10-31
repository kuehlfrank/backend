package kuehlfrank.backend.restapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.IAuthenticationFacade;
import kuehlfrank.backend.model.Message;
import kuehlfrank.backend.model.ServerInfo;
import kuehlfrank.backend.model.User;
import kuehlfrank.backend.repositories.UserRepository;

@RestController
@RequestMapping(path = "test", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Value("${spring.profiles.active:Unknown}")
    private String activeProfile;

    @GetMapping(value = "/serverInfo")
    public ServerInfo version() {
        return new ServerInfo("v8", activeProfile);
    }

    @GetMapping(value = "/public")
    public Message publicEndpoint() {
        return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @Autowired
    UserRepository userRepository;
    @GetMapping(value ="/currentUser")
    public Optional<User> currentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return userRepository.findById(authentication.getName());
    }

    @GetMapping(value = "/private")
    public Message privateEndpoint() {
        return new Message("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    public Message privateScopedEndpoint() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }
}
