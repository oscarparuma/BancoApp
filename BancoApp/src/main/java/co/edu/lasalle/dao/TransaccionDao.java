package co.edu.lasalle.dao;

import java.util.List;

import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.model.Transaccion;

public interface TransaccionDao {

	public void addTransaccion(Transaccion transaccion);
    public List<Transaccion> findAll();
}
