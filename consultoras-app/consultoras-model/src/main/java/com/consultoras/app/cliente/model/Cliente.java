package com.consultoras.app.cliente.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "tbl_cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 5504614394897558185L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tbl_cliente_id")
	private Long id;
	
	@NotNull	
	@Column(name = "tbl_cliente_consultora_id")
	private Long consultoraId;	

	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "tbl_cliente_primer_nombre")
	private String primerNombre;

	@Size(min = 2, max = 100)
	@Column(name = "tbl_cliente_segundo_nombre")
	private String segundoNombre;

	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "tbl_cliente_primer_apellido")
	private String primerApellido;

	@Size(min = 2, max = 100)
	@Column(name = "tbl_cliente_segundo_apellido")
	private String segundoApellido;

	@Size(min = 2, max = 100)
	@Column(name = "tbl_cliente_apellido_casada")
	private String apellidoCasada;

	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "tbl_cliente_fecha_nacimiento")
	private Calendar fechaNacimiento;

	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "tbl_cliente_fecha_aniversario")
	private Calendar fechaAniversario;

	@NotNull
	@Size(min = 2, max = 20)
	@Column(name = "tbl_cliente_hora_localizacion")
	private String horaLocalizacion;

	@NotNull
	@Size(min = 2, max = 1000)
	@Column(name = "tbl_cliente_direccion")
	private String direccion;

	@Size(max = 200)
	@Email
	@Column(name = "tbl_cliente_email")
	private String email;

	@Size(min = 8, max = 25)
	@Column(name = "tbl_cliente_celular")
	private String celular;

	@Size(min = 8, max = 25)
	@Column(name = "tbl_cliente_telefono_casa")
	private String telefonoCasa;

	@Size(min = 8, max = 25)
	@Column(name = "tbl_cliente_telefono_oficina")
	private String telefonoOficina;

	@Size(min = 1, max = 10)
	@Column(name = "tbl_cliente_telefono_oficina_extension")
	private String telefonoOficinaExtension;

	@Size(min = 8, max = 25)
	@Column(name = "tbl_cliente_telefono_conyuge")
	private String telefonoConyuge;

	@Size(min = 2, max = 500)
	@Column(name = "tbl_cliente_fotografia")
	private String fotografia;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_rango_edad")
	private RangoEdad rangoEdad;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_tono_base")
	private TonoBase tonoBase;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_tipo_labios")
	private TipoLabios tipoLabios;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_forma_cara")
	private FormaCara formaCara;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_tipo_piel")
	private TipoPiel tipoPiel;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_tono_piel")
	private TonoPiel tonoPiel;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_color_cabello")
	private ColorCabello colorCabello;

	@Enumerated(EnumType.STRING)
	@Column(name = "tbl_cliente_color_ojos")
	private ColorOjos colorOjos;

	@Temporal(TemporalType.DATE)
	@Column(name = "tbl_cliente_fecha_cliente_preferido")
	private Calendar fechaClientePreferido;

	@Size(min = 2, max = 200)
	@Column(name = "tbl_cliente_referido_por")
	private String referidoPor;

	public Cliente() {

	};

	public Cliente(final long consultoraId, final String primerNombre, final String segundoNombre, final String primerApellido,
			final String segundoApellido, final String apellidoCasada, final Calendar fechaNacimiento,
			final Calendar fechaAniversario, final String horaLocalizacion, final String direccion, final String email,
			final String celular, final String telefonoCasa, final String telefonoOficina,
			final String telefonoOficinaExtension, final String telefonoConyuge, final String fotografia,
			final RangoEdad rangoEdad, final TonoBase tonoBase, final TipoLabios tipoLabios, final FormaCara formaCara,
			final TipoPiel tipoPiel, final TonoPiel tonoPiel, final ColorCabello colorCabello,
			final ColorOjos colorOjos, final Calendar fechaClientePreferido, final String referidoPor) {

		this.consultoraId =  consultoraId;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.apellidoCasada = apellidoCasada;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaAniversario = fechaAniversario;
		this.horaLocalizacion = horaLocalizacion;
		this.direccion = direccion;
		this.email = email;
		this.celular = celular;
		this.telefonoCasa = telefonoCasa;
		this.telefonoOficina = telefonoOficina;
		this.telefonoOficinaExtension = telefonoOficinaExtension;
		this.telefonoConyuge = telefonoConyuge;
		this.fotografia = fotografia;
		this.rangoEdad = rangoEdad;
		this.tonoBase = tonoBase;
		this.tipoLabios = tipoLabios;
		this.formaCara = formaCara;
		this.tipoPiel = tipoPiel;
		this.tonoPiel = tonoPiel;
		this.colorCabello = colorCabello;
		this.colorOjos = colorOjos;
		this.fechaClientePreferido = fechaClientePreferido;
		this.referidoPor = referidoPor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getConsultoraId() {
		return consultoraId;
	}

	public void setConsultoraId(Long consultoraId) {
		this.consultoraId = consultoraId;
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

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Calendar getFechaAniversario() {
		return fechaAniversario;
	}

	public void setFechaAniversario(Calendar fechaAniversario) {
		this.fechaAniversario = fechaAniversario;
	}

	public String getHoraLocalizacion() {
		return horaLocalizacion;
	}

	public void setHoraLocalizacion(String horaLocalizacion) {
		this.horaLocalizacion = horaLocalizacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	public String getTelefonoOficina() {
		return telefonoOficina;
	}

	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}

	public String getTelefonoConyuge() {
		return telefonoConyuge;
	}

	public void setTelefonoConyuge(String telefonoConyuge) {
		this.telefonoConyuge = telefonoConyuge;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public RangoEdad getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(RangoEdad rangoEdad) {
		this.rangoEdad = rangoEdad;
	}

	public TonoBase getTonoBase() {
		return tonoBase;
	}

	public void setTonoBase(TonoBase tonoBase) {
		this.tonoBase = tonoBase;
	}

	public TipoLabios getTipoLabios() {
		return tipoLabios;
	}

	public void setTipoLabios(TipoLabios tipoLabios) {
		this.tipoLabios = tipoLabios;
	}

	public FormaCara getFormaCara() {
		return formaCara;
	}

	public void setFormaCara(FormaCara formaCara) {
		this.formaCara = formaCara;
	}

	public TipoPiel getTipoPiel() {
		return tipoPiel;
	}

	public void setTipoPiel(TipoPiel tipoPiel) {
		this.tipoPiel = tipoPiel;
	}

	public TonoPiel getTonoPiel() {
		return tonoPiel;
	}

	public void setTonoPiel(TonoPiel tonoPiel) {
		this.tonoPiel = tonoPiel;
	}

	public ColorCabello getColorCabello() {
		return colorCabello;
	}

	public void setColorCabello(ColorCabello colorCabello) {
		this.colorCabello = colorCabello;
	}

	public ColorOjos getColorOjos() {
		return colorOjos;
	}

	public void setColorOjos(ColorOjos colorOjos) {
		this.colorOjos = colorOjos;
	}

	public Calendar getFechaClientePreferido() {
		return fechaClientePreferido;
	}

	public void setFechaClientePreferido(Calendar fechaClientePreferido) {
		this.fechaClientePreferido = fechaClientePreferido;
	}

	public String getReferidoPor() {
		return referidoPor;
	}

	public void setReferidoPor(String referidoPor) {
		this.referidoPor = referidoPor;
	}

	public String getTelefonoOficinaExtension() {
		return telefonoOficinaExtension;
	}

	public void setTelefonoOficinaExtension(String telefonoOficinaExtension) {
		this.telefonoOficinaExtension = telefonoOficinaExtension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoCasada == null) ? 0 : apellidoCasada.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((colorCabello == null) ? 0 : colorCabello.hashCode());
		result = prime * result + ((colorOjos == null) ? 0 : colorOjos.hashCode());
		result = prime * result + ((consultoraId == null) ? 0 : consultoraId.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaAniversario == null) ? 0 : fechaAniversario.hashCode());
		result = prime * result + ((fechaClientePreferido == null) ? 0 : fechaClientePreferido.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((formaCara == null) ? 0 : formaCara.hashCode());
		result = prime * result + ((fotografia == null) ? 0 : fotografia.hashCode());
		result = prime * result + ((horaLocalizacion == null) ? 0 : horaLocalizacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((primerApellido == null) ? 0 : primerApellido.hashCode());
		result = prime * result + ((primerNombre == null) ? 0 : primerNombre.hashCode());
		result = prime * result + ((rangoEdad == null) ? 0 : rangoEdad.hashCode());
		result = prime * result + ((referidoPor == null) ? 0 : referidoPor.hashCode());
		result = prime * result + ((segundoApellido == null) ? 0 : segundoApellido.hashCode());
		result = prime * result + ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		result = prime * result + ((telefonoCasa == null) ? 0 : telefonoCasa.hashCode());
		result = prime * result + ((telefonoConyuge == null) ? 0 : telefonoConyuge.hashCode());
		result = prime * result + ((telefonoOficina == null) ? 0 : telefonoOficina.hashCode());
		result = prime * result + ((telefonoOficinaExtension == null) ? 0 : telefonoOficinaExtension.hashCode());
		result = prime * result + ((tipoLabios == null) ? 0 : tipoLabios.hashCode());
		result = prime * result + ((tipoPiel == null) ? 0 : tipoPiel.hashCode());
		result = prime * result + ((tonoBase == null) ? 0 : tonoBase.hashCode());
		result = prime * result + ((tonoPiel == null) ? 0 : tonoPiel.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apellidoCasada == null) {
			if (other.apellidoCasada != null)
				return false;
		} else if (!apellidoCasada.equals(other.apellidoCasada))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (colorCabello != other.colorCabello)
			return false;
		if (colorOjos != other.colorOjos)
			return false;
		if (consultoraId == null) {
			if (other.consultoraId != null)
				return false;
		} else if (!consultoraId.equals(other.consultoraId))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaAniversario == null) {
			if (other.fechaAniversario != null)
				return false;
		} else if (!fechaAniversario.equals(other.fechaAniversario))
			return false;
		if (fechaClientePreferido == null) {
			if (other.fechaClientePreferido != null)
				return false;
		} else if (!fechaClientePreferido.equals(other.fechaClientePreferido))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (formaCara != other.formaCara)
			return false;
		if (fotografia == null) {
			if (other.fotografia != null)
				return false;
		} else if (!fotografia.equals(other.fotografia))
			return false;
		if (horaLocalizacion == null) {
			if (other.horaLocalizacion != null)
				return false;
		} else if (!horaLocalizacion.equals(other.horaLocalizacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (rangoEdad != other.rangoEdad)
			return false;
		if (referidoPor == null) {
			if (other.referidoPor != null)
				return false;
		} else if (!referidoPor.equals(other.referidoPor))
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
		if (telefonoCasa == null) {
			if (other.telefonoCasa != null)
				return false;
		} else if (!telefonoCasa.equals(other.telefonoCasa))
			return false;
		if (telefonoConyuge == null) {
			if (other.telefonoConyuge != null)
				return false;
		} else if (!telefonoConyuge.equals(other.telefonoConyuge))
			return false;
		if (telefonoOficina == null) {
			if (other.telefonoOficina != null)
				return false;
		} else if (!telefonoOficina.equals(other.telefonoOficina))
			return false;
		if (telefonoOficinaExtension == null) {
			if (other.telefonoOficinaExtension != null)
				return false;
		} else if (!telefonoOficinaExtension.equals(other.telefonoOficinaExtension))
			return false;
		if (tipoLabios != other.tipoLabios)
			return false;
		if (tipoPiel != other.tipoPiel)
			return false;
		if (tonoBase != other.tonoBase)
			return false;
		if (tonoPiel != other.tonoPiel)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", consultoraId=" + consultoraId + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", apellidoCasada=" + apellidoCasada + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaAniversario=" + fechaAniversario + ", horaLocalizacion=" + horaLocalizacion + ", direccion="
				+ direccion + ", email=" + email + ", celular=" + celular + ", telefonoCasa=" + telefonoCasa
				+ ", telefonoOficina=" + telefonoOficina + ", telefonoOficinaExtension=" + telefonoOficinaExtension
				+ ", telefonoConyuge=" + telefonoConyuge + ", fotografia=" + fotografia + ", rangoEdad=" + rangoEdad
				+ ", tonoBase=" + tonoBase + ", tipoLabios=" + tipoLabios + ", formaCara=" + formaCara + ", tipoPiel="
				+ tipoPiel + ", tonoPiel=" + tonoPiel + ", colorCabello=" + colorCabello + ", colorOjos=" + colorOjos
				+ ", fechaClientePreferido=" + fechaClientePreferido + ", referidoPor=" + referidoPor + "]";
	}

	

}
