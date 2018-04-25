package co.edu.lasalle.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaccion")
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date fecha;
	private String descripcion;
	private String tipo;
	private String estado;
	private double monto;
	private BigDecimal saldoDisponible;
	
	@ManyToOne
	@JoinColumn(name = "cuenta_id")
	private Cuenta cuenta;

	public Transaccion() {
	}

	public Transaccion(Date fecha, String descripcion, String tipo, String estado, double monto,
			BigDecimal saldoDisponible, Cuenta cuenta) {
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
		this.monto = monto;
		this.saldoDisponible = saldoDisponible;
		this.cuenta = cuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
