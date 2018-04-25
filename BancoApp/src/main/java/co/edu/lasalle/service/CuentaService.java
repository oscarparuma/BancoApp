package co.edu.lasalle.service;

import java.security.Principal;

import co.edu.lasalle.model.Cuenta;

public interface CuentaService {

	public Cuenta addCuenta();
	public void consignacion(double monto, Principal principal);
	public void retiro(double monto, Principal principal);
}
