package com.leonardo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Called;
import com.leonardo.helpdesk.domain.Client;
import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.enums.Priority;
import com.leonardo.helpdesk.enums.Profile;
import com.leonardo.helpdesk.enums.Status;
import com.leonardo.helpdesk.repositories.CalledRepository;
import com.leonardo.helpdesk.repositories.ClientRepository;
import com.leonardo.helpdesk.repositories.TechnicianRepository;

@Service
public class DBService {

	@Autowired
	private TechnicianRepository technicianRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CalledRepository calledRepository;
	
	@Autowired
	private BCryptPasswordEncoder enconder;

	public void instanceDB() {
		Technician tec1 = new Technician(null, "Leonardo S. Miranda", "348.772.840-01", "leo@gmail.com", enconder.encode("123"));
		tec1.addProfile(Profile.ADMIN);
		Technician tec2 = new Technician(null, "Juli S. Miranda", "982.615.180-78", "julio@gmail.com", enconder.encode("123"));
		tec2.addProfile(Profile.ADMIN);
		Technician tec3 = new Technician(null, "Mario S. Miranda", "765.950.850-46", "mario@gmail.com", enconder.encode("123"));
		tec3.addProfile(Profile.ADMIN);

		Client cli1 = new Client(null, "Jeremias Isac", "005.612.370-18", "jeremias@hotmail.com", enconder.encode("123"));
		Client cli2 = new Client(null, "Joab Isac", "707.447.850-49", "joab@hotmail.com", enconder.encode("123"));

		Called c1 = new Called(null, Priority.AVERAGE, Status.PROGRESS, "Called 01", "First called", tec1, cli1);
		Called c2 = new Called(null, Priority.AVERAGE, Status.OPEN, "Called 02", "Two called", tec1, cli2);

		technicianRepository.saveAll(Arrays.asList(tec1));
		technicianRepository.saveAll(Arrays.asList(tec2));
		technicianRepository.saveAll(Arrays.asList(tec3));
		
		
		clientRepository.saveAll(Arrays.asList(cli1));
		clientRepository.saveAll(Arrays.asList(cli2));
		
		calledRepository.saveAll(Arrays.asList(c1));
		calledRepository.saveAll(Arrays.asList(c2));
		

	}
}
