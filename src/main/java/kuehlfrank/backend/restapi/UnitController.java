package kuehlfrank.backend.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kuehlfrank.backend.model.Unit;
import kuehlfrank.backend.repositories.UnitRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UnitController {

	@Autowired
	private UnitRepository unitRepository;
	
	@GetMapping("/units")
	public Iterable<Unit> getUnits(Authentication authentication){
		return unitRepository.findAll();
	}
}
