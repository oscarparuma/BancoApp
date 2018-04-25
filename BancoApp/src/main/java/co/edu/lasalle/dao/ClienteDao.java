package co.edu.lasalle.dao;

import java.util.List;

//import org.springframework.data.repository.CrudRepository;

import co.edu.lasalle.model.Cliente;

public interface ClienteDao {

	public void addCliente(Cliente cliente);
	public void updateCliente(Cliente cliente);
	public List<Cliente> listClientes();
	public Cliente getClienteById(int id);
	public void deleteCliente(int id);
	public Cliente findByUsername(String username);
}
