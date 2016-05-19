package com.consultoras.app.commontests.consultora;


import com.consultoras.app.consultora.model.Consultora;

public class ConsultoraForTestsRepository {

	public static Consultora mary() {

		return new Consultora("Maria", "Claudia", "Pineda", "Osorio", null, "5575-2615", "marygacelas@yahoo.com",
				"MARY_01", "164578-7");
	}
	
	public static Consultora consultoraWithId(final Consultora consultora, final Long id) {
		consultora.setId(id);
		return consultora;
	}

}
