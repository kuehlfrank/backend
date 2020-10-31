package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.dto.EnsureUserRegisteredDto;
import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.User;
import kuehlfrank.backend.repositories.InventoryRepository;
import kuehlfrank.backend.repositories.UserRepository;
import lombok.var;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InventoryRepository inventoryRepository;

	@PostMapping("/ensureRegistered")
	public ResponseEntity<?> registerUser(@RequestBody EnsureUserRegisteredDto ensureUserRegisteredDto, Authentication authentication){
		var existingUser = userRepository.findById(authentication.getName());
		if (existingUser.isPresent()) {
			return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
		}

		Inventory inventory = inventoryRepository.save(new Inventory());
		var user = userRepository.save(new User(authentication.getName(), ensureUserRegisteredDto.getUsername(), inventory));
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
