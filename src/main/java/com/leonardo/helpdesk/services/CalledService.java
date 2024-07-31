package com.leonardo.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Called;
import com.leonardo.helpdesk.domain.Client;
import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.dtos.CalledDTO;
import com.leonardo.helpdesk.enums.Priority;
import com.leonardo.helpdesk.enums.Status;
import com.leonardo.helpdesk.repositories.CalledRepository;
import com.leonardo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository repository;

	@Autowired
	private TechnicianService technicianService;

	@Autowired
	private ClientService clientService;

	public Called findById(Integer id) {
		Optional<Called> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id));
	}

	public List<Called> findAll() {
		return repository.findAll();
	}

	public Called create(@Valid CalledDTO objDto) {
		return repository.save(newCalled(objDto));
	}

	public Called update(Integer id, @Valid CalledDTO objDto) {
		objDto.setId(id);
		Called oldObj = findById(id);
		updateData(oldObj, objDto);
		return repository.save(oldObj);
	}

	private void updateData(Called entity, CalledDTO objDto) {
		entity.setPriority(Priority.toEnum(objDto.getPriority()));
		entity.setStatus(Status.toEnum(objDto.getStatus()));
		entity.setTitle(objDto.getTitle());
		entity.setObservation(objDto.getObservation());
		entity.setTechnician(technicianService.findById(objDto.getTechnician()));
		entity.setClient(clientService.findById(objDto.getClient()));

		if (objDto.getStatus() == 2 && entity.getClosingDate() == null) {
			entity.setClosingDate(LocalDate.now());
		} else if (objDto.getStatus() != 2) {
			entity.setClosingDate(null);
		}
	}

	private Called newCalled(CalledDTO objDto) {
		Technician technician = technicianService.findById(objDto.getTechnician());
		Client client = clientService.findById(objDto.getClient());

		Called called = new Called();
		called.setOpenDate(LocalDate.now());
		called.setPriority(Priority.toEnum(objDto.getPriority()));
		called.setStatus(Status.toEnum(objDto.getStatus()));
		called.setTitle(objDto.getTitle());
		called.setObservation(objDto.getObservation());
		called.setTechnician(technician);
		called.setClient(client);

		if (objDto.getStatus() == 2) {
			called.setClosingDate(LocalDate.now());
		}

		return called;
	}
}