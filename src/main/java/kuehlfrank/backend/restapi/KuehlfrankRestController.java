package kuehlfrank.backend.restapi;

import org.springframework.web.bind.annotation.*;

@RestController
public class KuehlfrankRestController {

	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}
	
	@GetMapping("/inventory")
	public String fridge(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "That's pretty basic!";
	}
	
}
