package kuehlfrank.backend.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Inventory;

@RestController
public class KuehlfrankRestController {

	@GetMapping("/ping")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}
	
	@GetMapping("/inventory")
	public String getInventory(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello, %s", name);
	}
	
	
	@PutMapping("/inventory")
	public void setInventory(@RequestBody Inventory inventory /*TODO auth?*/){
		System.out.println(inventory);
	}
	
}
