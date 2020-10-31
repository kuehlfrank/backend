package kuehlfrank.backend.restapi;

import java.util.Arrays;
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
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "inventory")
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

	@GetMapping("")
	public Inventory getInventory(Authentication authentication) {
		return inventoryRepository.findByUserId(authentication.getName()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));
	}

	@PostMapping("/inventoryEntry")
	public InventoryEntry addInventoryEntry(Authentication authentication, @RequestBody AddItemDto addItemDto) {
		var existingIngredient = ingredientRepository.findByName(addItemDto.getIngredientName());
		var unit = unitRepository.findById(addItemDto.getUnitId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find unit"));
		// try to find existing InventoryEntry
		if (existingIngredient.isPresent() && inventoryEntryRepository
				.findByIngredientAndUnitId(existingIngredient.get().getIngredientId(), unit.getUnitId()).isPresent()) {
			var ie = inventoryEntryRepository
					.findByIngredientAndUnitId(existingIngredient.get().getIngredientId(), unit.getUnitId()).get();

			// TODO: Insert new alternative names

			ie.increaseAmount(addItemDto.getAmount());
			return inventoryEntryRepository.save(ie);
		} else {
			Inventory inventory = inventoryRepository.findByUserId(authentication.getName()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find inventory for user"));

			// use existing ingredient if possible else create new one
			Ingredient ingredient = existingIngredient.orElseGet(() -> ingredientRepository
					.save(new Ingredient(addItemDto.getIngredientName(), addItemDto.isCommon())));

			// add all alternative names
			var names = Arrays.stream(addItemDto.getAlternative_names())
					.map(s -> new IngredientAlternativeName(s, ingredient)).collect(Collectors.toList());
			ingredientAlternativeNameRepository.saveAll(names);

			InventoryEntry inventoryEntry = new InventoryEntry(inventory, ingredient, addItemDto.getAmount(),
					addItemDto.getImgSrc(), unit);
			return inventoryEntryRepository.save(inventoryEntry);
		}
	}

	@DeleteMapping("/inventoryEntry/{inventoryEntryId}")
	public Message deleteInventoryEntry(Authentication authentication, @PathVariable UUID inventoryEntryId) {
		inventoryEntryRepository.deleteById(inventoryEntryId);
		return new Message("success");
	}

	@PutMapping("/inventoryEntry/{inventoryEntryId}")
	public InventoryEntry updateInventoryEntry(Authentication authentication, @PathVariable UUID inventoryEntryId,
			@RequestBody UpdateInventoryEntryDto dto) {
		InventoryEntry inventoryEntry = inventoryEntryRepository.findById(inventoryEntryId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Unable to find inventoryEntry for inventoryEntryId"));
		
		if (dto.getUnitId() != null) {
			inventoryEntry.setUnit(unitRepository.findById(dto.getUnitId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find unit")));
		}
		if (dto.getAmount() != null) {
			inventoryEntry.setAmount(dto.getAmount());
		}

		return inventoryEntryRepository.save(inventoryEntry);
	}
}
