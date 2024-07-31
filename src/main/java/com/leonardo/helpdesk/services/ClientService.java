package com.leonardo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Client;
import com.leonardo.helpdesk.domain.Person;
import com.leonardo.helpdesk.domain.dtos.ClientDTO;
import com.leonardo.helpdesk.repositories.ClientRepository;
import com.leonardo.helpdesk.repositories.PersonRepository;
import com.leonardo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.leonardo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BCryptPasswordEncoder enconder;

	public Client findById(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();

	}

	public Client create(ClientDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(enconder.encode(objDTO.getPassword()));
		validateByCpfEEmail(objDTO);
		Client newObj = new Client(objDTO);
		return repository.save(newObj);
	}

	public Client update(Integer id, @Valid ClientDTO objDto) {
		objDto.setId(id);
		Client oldObj = findById(id);
		validateByCpfEEmail(objDto);
		oldObj = new Client(objDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Client obj = findById(id);
		if(obj.getCalled().size() > 0) {
			throw new DataIntegrityViolationException("Client has a service order and cannot be deleted!");
		}
			repository.deleteById(id);
	}

	private void validateByCpfEEmail(ClientDTO objDTO) {
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
