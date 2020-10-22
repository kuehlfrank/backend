package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Message;

@RestController
@RequestMapping(path = "test", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class TestController {

    @Value("${spring.profiles.active:Unknown}")
    private String activeProfile;

    @GetMapping(value = "/version")
    public Message version() {
        return new Message("v6");
    }

    @GetMapping(value = "/activeProfile")
    public Message activeProfile() {
        return new Message(activeProfile);
    }

    @GetMapping(value = "/public")
    public Message publicEndpoint() {
        return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Message privateEndpoint() {
        return new Message("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    public Message privateScopedEndpoint() {
        return new Message(
                "All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }
}
