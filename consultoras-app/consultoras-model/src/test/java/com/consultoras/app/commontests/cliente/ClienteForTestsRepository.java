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
	
	
	public static Cliente lucia(){
		
		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();
		
		fechaNacimiento.set(1986, 10, 10);
		fechaAniversario.set(2010, 6, 24);
		fechaPreferida.set(2016, 4, 25);
		
		return new Cliente("Lucia","Isabel","Navarijo","Bachez","Reina", 
				fechaNacimiento, fechaAniversario, "15:00", 
				"24 Avenida 26-71 Zona 16, Portal De San Isidro Residencias, Guatemala", 
				"lisabelnavarijo@gmail.com", "4769-2171", "2219-5470", "2222-2222", "5575-2615", 
				"\\pictures\\Picture01", RangoEdad.Treintas, TonoBase.Beige, TipoLabios.Fino, FormaCara.Ovalada, 
				TipoPiel.Mixta, TonoPiel.Blanca, ColorCabello.Castano, ColorOjos.Cafe, fechaPreferida,
				"Alberto Reina");
	}
	
	public static Cliente maria(){
		
		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();
		
		fechaNacimiento.set(1954, 9, 16);
		fechaAniversario.set(1980, 6, 21);
		fechaPreferida.set(2016, 6, 18);
		
		return new Cliente("Maria","Claudia","Pineda","Osorio","Reina", 
				fechaNacimiento, fechaAniversario, "10:00", 
				"10 Calle A 19-09 Zona 17, Colonia Lomas del Norte, Guatemala", 
				"marygacelas@yahoo.com", "5555-3799", "2256-1772", null, null, 
				"\\pictures\\Picture02", RangoEdad.Sesentas, TonoBase.Bronze, TipoLabios.Fino, FormaCara.Redonda, 
				TipoPiel.Mixta, TonoPiel.Morena, ColorCabello.Rojizo, ColorOjos.Cafe, fechaPreferida,
				"Maria Rodriguez");
	}
	
	public static Cliente enieytilde(){
		
		Calendar fechaNacimiento = Calendar.getInstance();
		Calendar fechaAniversario = Calendar.getInstance();
		Calendar fechaPreferida = Calendar.getInstance();
		
		fechaNacimiento.set(1980, 10, 10);
		fechaAniversario.set(2005, 4, 10);
		fechaPreferida.set(2016, 10, 19);
		
		return new Cliente("Aná","Mariá","Iñarratu","Lopez","Martinez", 
				fechaNacimiento, fechaAniversario, "10:00", 
				"20 Calle 11-29 Zona 19, Colonia Primero de Julio, Guatemala", 
				"inarratu@gmail.com", "7545-7756", "2445-7756",null, "4464-4646", 
				"\\pictures\\Picture03", RangoEdad.Cuarentas, TonoBase.Ivory, TipoLabios.Gruesos, FormaCara.Redonda, 
				TipoPiel.Grasosa, TonoPiel.Negra, ColorCabello.Negro, ColorOjos.Verde, fechaPreferida,
				"Luisa Molina");
	}
	
	public static Cliente clienteWithId(final Cliente cliente, final Long id) {
		cliente.setId(id);
		return cliente;
	}
	
	public static List<Cliente> allClientes(){
		return Arrays.asList(lucia(), maria(), enieytilde());
		
	}

}
