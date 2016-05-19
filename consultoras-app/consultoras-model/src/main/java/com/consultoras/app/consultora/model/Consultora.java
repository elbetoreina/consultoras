package com.consultoras.app.consultora.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.consultoras.app.cliente.model.Cliente;

@Entity
@Table(name="tbl_consultora")
public class Consultora implements Serializable {	
	private static final long serialVersionUID = 4797668206900493816L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tbl_consultora_id")
	private long id;
	
	@OneToMany(mappedBy="consultora")
	private Collection<Cliente> cliente;
	
	@NotNull
	@Column(name="tbl_consultora_primer_nombre")
	@Size(min = 2, max = 100)
	private String primerNombre;
	
	@Column(name="tbl_consultora_segundo_nombre")
	@Size(min = 2, max = 100)
	private String segundoNombre;
	
	@NotNull
	@Column(name="tbl_consultora_primer_apellido")
	@Size(min = 2, max = 100)
	private String primerApellido;	
	
	@Column(name="tbl_consultora_segundo_apellido")
	@Size(min = 2, max = 100)
	private String segundoApellido;	
	
	@Column(name="tbl_consultora_apellido_casada")
	@Size(min = 2, max = 100)
	private String apellidoCasada;
	
	@NotNull
	@Column(name="tbl_consultora_telefono_contacto")
	@Size(min = 8, max = 25)
	private String telefono;
	
	@NotNull
	@Email	
	@Column(name="tbl_consultora_email_contacto")	
	private String email;	
	
	@NotNull
	@Column(name="tbl_consultora_codigo_consultora")
	@Size(min = 2, max = 200)
	private String codigoConsultora;
	
	@Column(name="tbl_consultora_NIT")
	@Size(min = 2, max = 100)
	private String nit;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getApellidoCasada() {
		return apellidoCasada;
	}

	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public Consultora(){
		
	};

	public Consultora(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String apellidoCasada, String telefono, String email, String codigoConsultora, String nit) {		
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.apellidoCasada = apellidoCasada;
		this.telefono = telefono;
		this.email = email;
		this.codigoConsultora = codigoConsultora;
		this.nit = nit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoCasada == null) ? 0 : apellidoCasada.hashCode());
		result = prime * result + ((codigoConsultora == null) ? 0 : codigoConsultora.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nit == null) ? 0 : nit.hashCode());
		result = prime * result + ((primerApellido == null) ? 0 : primerApellido.hashCode());
		result = prime * result + ((primerNombre == null) ? 0 : primerNombre.hashCode());
		result = prime * result + ((segundoApellido == null) ? 0 : segundoApellido.hashCode());
		result = prime * result + ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultora other = (Consultora) obj;
		if (apellidoCasada == null) {
			if (other.apellidoCasada != null)
				return false;
		} else if (!apellidoCasada.equals(other.apellidoCasada))
			return false;
		if (codigoConsultora == null) {
			if (other.codigoConsultora != null)
				return false;
		} else if (!codigoConsultora.equals(other.codigoConsultora))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nit == null) {
			if (other.nit != null)
				return false;
		} else if (!nit.equals(other.nit))
			return false;
		if (primerApellido == null) {
			if (other.primerApellido != null)
				return false;
		} else if (!primerApellido.equals(other.primerApellido))
			return false;
		if (primerNombre == null) {
			if (other.primerNombre != null)
				return false;
		} else if (!primerNombre.equals(other.primerNombre))
			return false;
		if (segundoApellido == null) {
			if (other.segundoApellido != null)
				return false;
		} else if (!segundoApellido.equals(other.segundoApellido))
			return false;
		if (segundoNombre == null) {
			if (other.segundoNombre != null)
				return false;
		} else if (!segundoNombre.equals(other.segundoNombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consultora [id=" + id + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre
				+ ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", apellidoCasada="
				+ apellidoCasada + ", telefono=" + telefono + ", email=" + email + ", codigoConsultora="
				+ codigoConsultora + ", nit=" + nit + "]";
	}

}
