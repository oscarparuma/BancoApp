package co.edu.lasalle.service;

import java.util.List;

import co.edu.lasalle.model.Cliente;

public interface ClienteService {
	
	public void addCliente(Cliente cliente);
	public void updateCliente(Cliente cliente);
	public List<Cliente> listClientes();
	public Cliente getClienteById(int id);
	public void deleteCliente(int id);
	public Cliente findByUsername(String username);
}
