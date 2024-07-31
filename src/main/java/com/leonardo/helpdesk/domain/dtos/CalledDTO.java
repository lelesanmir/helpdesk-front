package com.leonardo.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardo.helpdesk.domain.Called;

public class CalledDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closingDate;
	@NotNull(message = "The PRIORITY field is required!")
	private Integer priority;
	@NotNull(message = "The STATUS field is required!")
	private Integer status;
	@NotNull(message = "The TITLE field is required!")
	private String title;
	@NotNull(message = "The OBSERVATION field is required!")
	private String observation;
	@NotNull(message = "The TECHNICIAN field is required!")
	private Integer technician;
	@NotNull(message = "The CLIENT field is required!")
	private Integer client;
	private String nameTechnician;
	private String nameClient;

	public CalledDTO() {
		super();
	}

	public CalledDTO(Called obj) {
		super();
		this.id = obj.getId();
		this.openDate = obj.getOpenDate();
		this.closingDate = obj.getClosingDate();
		this.priority = obj.getPriority().getCode();
		this.status = obj.getStatus().getCode();
		this.title = obj.getTitle();
		this.observation = obj.getObservation();
		this.technician = obj.getTechnician().getId();
		this.client = obj.getClient().getId();
		this.nameTechnician = obj.getTechnician().getName();
		this.nameClient = obj.getClient().getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getTechnician() {
		return technician;
	}

	public void setTechnician(Integer technician) {
		this.technician = technician;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getNameTechnician() {
		return nameTechnician;
	}

	public void setNameTechnician(String nameTechnician) {
		this.nameTechnician = nameTechnician;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
}