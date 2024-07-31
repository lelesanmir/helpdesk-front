package com.leonardo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Person;
import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.dtos.TechnicianDTO;
import com.leonardo.helpdesk.repositories.PersonRepository;
import com.leonardo.helpdesk.repositories.TechnicianRepository;
import com.leonardo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.leonardo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository repository;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BCryptPasswordEncoder enconder;

	public Technician findById(Integer id) {
		Optional<Technician> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
	}

	public List<Technician> findAll() {
		return repository.findAll();

	}

	public Technician create(TechnicianDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(enconder.encode(objDTO.getPassword()));
		validateByCpfEEmail(objDTO);
		Technician newObj = new Technician(objDTO);
		return repository.save(newObj);
	}

	public Technician update(Integer id, @Valid TechnicianDTO objDto) {
		objDto.setId(id);
		Technician oldObj = findById(id);
		validateByCpfEEmail(objDto);
		oldObj = new Technician(objDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Technician obj = findById(id);
		if(obj.getCalled().size() > 0) {
			throw new DataIntegrityViolationException("Technician has a service order and cannot be deleted!");
		}
			repository.deleteById(id);
	}

	private void validateByCpfEEmail(TechnicianDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF already registered in the system!");
		}

		obj = personRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail already registered in the system!");
		}
	}

}
