package kuehlfrank.backend.restapi;

import kuehlfrank.backend.dto.EnsureUserRegisteredDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import kuehlfrank.backend.dto.RegisterUserDto;
import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.User;
import kuehlfrank.backend.repositories.InventoryRepository;
import kuehlfrank.backend.repositories.UserRepository;

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
