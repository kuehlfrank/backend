package kuehlfrank.backend.restapi;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kuehlfrank.backend.dto.AddItemDto;
import kuehlfrank.backend.dto.UpdateInventoryEntryDto;
import kuehlfrank.backend.model.Ingredient;
import kuehlfrank.backend.model.IngredientAlternativeName;
import kuehlfrank.backend.model.Inventory;
import kuehlfrank.backend.model.InventoryEntry;
import kuehlfrank.backend.model.Message;
import kuehlfrank.backend.repositories.IngredientAlternativeNameRepository;
import kuehlfrank.backend.repositories.IngredientRepository;
import kuehlfrank.backend.repositories.InventoryEntryRepository;
import kuehlfrank.backend.repositories.InventoryRepository;
import kuehlfrank.backend.repositories.UnitRepository;
import lombok.var;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private InventoryEntryRepository inventoryEntryRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private IngredientAlternativeNameRepository ingredientAlternativeNameRepository;
	@Autowired
	private UnitRepository unitRepository;

	@GetMapping("/inventory/{userId}")
	public Inventory getInventory(Authentication authentication, @PathVariable(required = false) String userId) {

		if (userId == null) {
			userId = authentication.getName(); // get users own inventory if not specified otherwise
		} else if (!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory for
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other users inventory");
		}

		return getInventoryForUser(userId);
	}
	
	@GetMapping("/inventory")
	public Inventory getInventory(Authentication authentication) {
		return getInventory(authentication, authentication.getName());
	}

	@PostMapping("/inventory/{userId}/inventoryEntry")
	public InventoryEntry addInventoryEntry(Authentication authentication, @PathVariable String userId,
			@RequestBody AddItemDto addItemDto) {
		checkUserId(authentication, userId);

		var existingIngredient = ingredientRepository.findByName(addItemDto.getIngredientName());
		var unit = unitRepository.findById(addItemDto.getUnitId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find unit"));
		// try to find existing InventoryEntry
		if(existingIngredient.isPresent() && inventoryEntryRepository.findByIngredientAndUnitId(existingIngredient.get().getIngredientId(), unit.getUnitId()).isPresent()) {
			var ie = inventoryEntryRepository.findByIngredientAndUnitId(existingIngredient.get().getIngredientId(), unit.getUnitId()).get();

			// TODO: Insert new alternative names

			ie.increaseAmount(addItemDto.getAmount());
			return inventoryEntryRepository.save(ie);
		} else {
			Inventory inventory = inventoryRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));

			// use existing ingredient if possible else create new one
			Ingredient ingredient = existingIngredient.orElseGet(() -> ingredientRepository.save(new Ingredient(addItemDto.getIngredientName(), addItemDto.isCommon())));

			// add all alternative names
			var names = Arrays.stream(addItemDto.getAlternative_names()).map(s -> new IngredientAlternativeName(s, ingredient)).collect(Collectors.toList());
			ingredientAlternativeNameRepository.saveAll(names);

			InventoryEntry inventoryEntry = new InventoryEntry(inventory, ingredient, addItemDto.getAmount(), addItemDto.getImgSrc(), unit);
			return inventoryEntryRepository.save(inventoryEntry);
		}
	}

	@DeleteMapping("/inventory/{userId}/inventoryEntry/{inventoryEntryId}")
	public Message deleteInventoryEntry(Authentication authentication, @PathVariable String userId,
			@PathVariable UUID inventoryEntryId) {
		checkUserId(authentication, userId);
		inventoryEntryRepository.deleteById(inventoryEntryId);
		return new Message("success");
	}

	@PutMapping("/inventory/{userId}/inventoryEntry/{inventoryEntryId}")
	public InventoryEntry updateInventoryEntry(Authentication authentication, @PathVariable String userId,
			@PathVariable UUID inventoryEntryId, @RequestBody UpdateInventoryEntryDto dto) {
		checkUserId(authentication, userId);
		InventoryEntry inventoryEntry = inventoryEntryRepository.findById(inventoryEntryId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventoryEntry for inventoryEntryId"));
		if (dto.getUnitId() != null) {
			inventoryEntry.setUnit(unitRepository.findById(dto.getUnitId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find unit")));
		}
		if (dto.getAmount() != null) {
			inventoryEntry.setAmount(dto.getAmount());
		}
		
		return inventoryEntryRepository.save(inventoryEntry);
	}

	private Inventory getInventoryForUser(String userId) {
		return inventoryRepository.findByUserId(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));
	}

	private void checkUserId(Authentication authentication, String userId) {
		if (userId == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId can't be empty");
		} else if (!Objects.equals(userId, authentication.getName())) { // Only allow getting users own inventory, for now
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access other users inventory");
		}
	}
}
