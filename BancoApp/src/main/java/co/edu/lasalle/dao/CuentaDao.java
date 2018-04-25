package co.edu.lasalle.dao;

import co.edu.lasalle.model.Cuenta;

public interface CuentaDao {

	public void addCuenta(Cuenta cuenta);
    public Cuenta findByNumeroCuenta (int accountNumber);
}
