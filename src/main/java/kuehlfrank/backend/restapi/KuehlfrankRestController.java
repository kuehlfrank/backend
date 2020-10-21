package kuehlfrank.backend.restapi;

import kuehlfrank.backend.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class KuehlfrankRestController {

	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}
	
	@GetMapping("/inventory")
	public String fridge(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
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
		return new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
	}
}
