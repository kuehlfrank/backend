package kuehlfrank.backend.restapi;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Inventory;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class KuehlfrankRestController {

	@GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
	
	
	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@GetMapping("/inventory")
	public String fridge(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/inventory")
	public void create(@RequestBody Inventory inventory) {
		log.info("Foo created");
	}
}
