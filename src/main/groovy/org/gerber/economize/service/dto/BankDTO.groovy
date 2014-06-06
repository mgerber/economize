package org.gerber.economize.service.dto

import org.gerber.economize.domain.Bank

class BankDTO {
	private Long id

	private String bankCode

	private String name
	
	private String location
	
	private String bic
	
	private String crc
	
	private String hbciHost
	
	private String pinTanURL

	private String hbciVersion
	
	private String pinTanVersion

	private String port
		
	private String country
		
	public BankDTO() {		
	}

	public BankDTO(final Bank bank) {
		this.id = bank.id
		this.bankCode = bank.bankCode
		this.name = bank.name
		this.location = bank.location
		this.bic = bank.bic
		this.crc = bank.crc
		this.hbciHost = bank.hbciHost
		this.pinTanURL = bank.pinTanURL
		this.hbciVersion = bank.hbciVersion
		this.pinTanVersion = bank.pinTanVersion
		this.port = bank.port
		this.country = bank.country
	}
	
	
    @Override
    public String toString() {
        return "Bank{" +
				"id='" + id + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", BIC='" + bic + '\'' +
                ", crc='" + crc + '\'' +
                ", hbciHost='" + hbciHost + '\'' +
                ", pinTanURL='" + pinTanURL + '\'' +
                ", hbciVersion='" + hbciVersion + '\'' +
                ", pinTanVersion='" + pinTanVersion + '\'' +
                ", port='" + port + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
