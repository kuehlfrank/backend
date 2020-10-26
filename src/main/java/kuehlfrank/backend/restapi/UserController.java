package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	@Value("${webhook.secret}")
	private String webhookSecret;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody RegisterUserDto registerUserDto){
		if (!webhookSecret.equals(registerUserDto.getWebhookSecret())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong secret");
		}
		if (userRepository.existsById(registerUserDto.getUserId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId already exists");
		}
		Inventory inventory = inventoryRepository.save(new Inventory());
		return userRepository.save(new User(registerUserDto.getUserId(), registerUserDto.getName(), inventory));
	}
}
