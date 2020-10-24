package kuehlfrank.backend.restapi;

import java.util.Objects;

import kuehlfrank.backend.dto.AddItemDto;
import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.InventoryEntry;
import kuehlfrank.backend.model.Unit;
import kuehlfrank.backend.repositories.IngredientRepository;
import kuehlfrank.backend.repositories.InventoryEntryRepository;
import kuehlfrank.backend.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.repositories.InventoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Log4j2
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private InventoryEntryRepository inventoryEntryRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private UnitRepository unitRepository;

	@GetMapping("/inventory")
	public Inventory getInventory(Authentication authentication, @RequestParam(value = "userId", required = false) String userId) {

		if(userId == null) {
			userId = authentication.getName(); // get users own inventory if not specified otherwise
		}
		else if(!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory for now
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other users inventory");
		}

		return inventoryRepository.findByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));
	}

	@PostMapping("/inventory/{userId}/inventoryEntry")
	public InventoryEntry addInventoryEntry(Authentication authentication, @PathVariable("userId") String userId, @RequestBody AddItemDto addItemDto) {
		if(userId == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId can't be empty");
		}
		else if(!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory for now
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other users inventory");
		}

		Inventory inventory = inventoryRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));
		Ingredient ingredient = ingredientRepository.findById(addItemDto.getIngredientId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find ingredient"));
		Unit unit = unitRepository.findById(addItemDto.getUnitId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find unit"));

		InventoryEntry inventoryEntry = new InventoryEntry(inventory, ingredient, addItemDto.getAmount(), unit);

		return inventoryEntryRepository.save(inventoryEntry);
	}

//	@GetMapping(value = "/recipes")
//	public Collection<Recipe> recipes(@RequestParam Long userId) { //TODO user auth
//		return recipeRepository.findPossibleRecipes(userId);
//	}
}
