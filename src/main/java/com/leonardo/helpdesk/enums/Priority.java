package com.leonardo.helpdesk.enums;

public enum Priority {
	
	LOW(0, "ROLE_LOW"), AVERAGE(1, "ROLE_AVERAGE"), HIGH(2, "ROLE_HIGH");

	private Integer code;
	private String description;
	
	private Priority(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Priority toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Priority x : Priority.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid priority!");
	}
	
	
}
