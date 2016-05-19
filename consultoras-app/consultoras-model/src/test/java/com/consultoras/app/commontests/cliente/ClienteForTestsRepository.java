package com.consultoras.app.commontests.cliente;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Ignore;

import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.model.ColorCabello;
import com.consultoras.app.cliente.model.ColorOjos;
import com.consultoras.app.cliente.model.FormaCara;
import com.consultoras.app.cliente.model.RangoEdad;
import com.consultoras.app.cliente.model.TipoLabios;
import com.consultoras.app.cliente.model.TipoPiel;
import com.consultoras.app.cliente.model.TonoBase;
import com.consultoras.app.cliente.model.TonoPiel;

@Ignore
public class ClienteForTestsRepository {

	public static Cliente lucia() {

		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();

		fechaNacimiento.setTimeInMillis(531252000L * 1000);
		fechaAniversario.setTimeInMillis(1278007200L * 1000);
		fechaPreferida.setTimeInMillis(1391277600L * 1000);

		return new Cliente(1L , "Lucia", "Isabel", "Navarijo", "Bachez", "Reina", fechaNacimiento, fechaAniversario, "15:00",
				"24 Avenida 26-71 Zona 16, Portal de San Isidro Residencias, Guatemala", "lisabelnavarijo@gmail.com",
				"4769-2191", "2219-5470", "2229-2500", "12345", "5575-2615", "/images/foto001.jpg", RangoEdad.Treintas,
				TonoBase.Beige, TipoLabios.Fino, FormaCara.Ovalada, TipoPiel.Mixta, TonoPiel.Blanca,
				ColorCabello.Castano, ColorOjos.Cafe, fechaPreferida, "Alberto Reina");
	}

	public static Cliente maria() {

		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();

		fechaNacimiento.setTimeInMillis(-482565600 * 1000);
		fechaAniversario.setTimeInMillis(330458400 * 1000);
		fechaPreferida.setTimeInMillis(1466272800 * 1000);

		return new Cliente(1L, "Maria", "Claudia", "Pineda", "Osorio", "Reina", fechaNacimiento, fechaAniversario, "10:00",
				"10 Calle A 19-09 Zona 17, Colonia Lomas del Norte, Guatemala", "marygacelas@yahoo.com", "5555-3799",
				"2256-1772", null, null, null, "/images/foto001.jpg", RangoEdad.Sesentas, TonoBase.Bronze, TipoLabios.Fino,
				FormaCara.Redonda, TipoPiel.Mixta, TonoPiel.Morena, ColorCabello.Rojizo, ColorOjos.Cafe, fechaPreferida,
				"Alberto Reina");
	}

	public static Cliente enieytilde() {

		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();

		fechaNacimiento.setTimeInMillis(340048800L * 1000);
		fechaAniversario.setTimeInMillis(1128448800L * 1000);
		fechaPreferida.setTimeInMillis(1476900000L * 1000);

		return new Cliente(1L, "Aná", "Mariá", "Iñarratu", "Lopez", "Martinez", fechaNacimiento, fechaAniversario, "10:00",
				"20 Calle 11-29 Zona 19, Colonia Primero de Julio, Guatemala", "inarratu@gmail.com", "7545-7756",
				"2445-7756", "2254-4546","12345", "4464-4646", "/images/foto001.jpg", RangoEdad.Cuarentas, TonoBase.Ivory,
				TipoLabios.Gruesos, FormaCara.Redonda, TipoPiel.Grasosa, TonoPiel.Negra, ColorCabello.Negro,
				ColorOjos.Verde, fechaPreferida, "Luisa Molina");
	}

	public static Cliente clienteWithId(final Cliente cliente, final Long id) {
		cliente.setId(id);
		return cliente;
	}

	public static List<Cliente> allClientes() {
		return Arrays.asList(lucia(), maria(), enieytilde());

	}

}
