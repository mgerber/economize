package org.gerber.economize.service.dto

import org.gerber.economize.domain.Bank

class BankDTO {
	private String id

	private String name
	
	private String host
	
	private String port
	
	private String bankCode
	
	private String country
	
	private String hbciVersion
	
	public BankDTO() {		
	}

	public BankDTO(final Bank bank) {
		this.id = bank.id
		this.name = bank.name
		this.host = bank.host
		this.port = bank.port
		this.bankCode = bank.bankCode
		this.country = bank.country
	}
	
	
    @Override
    public String toString() {
        return "Bank{" +
				"id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", blz='" + bankCode + '\'' +
                ", country='" + country + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
