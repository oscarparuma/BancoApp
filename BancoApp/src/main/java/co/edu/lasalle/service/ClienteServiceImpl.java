package co.edu.lasalle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.lasalle.dao.ClienteDao;
import co.edu.lasalle.model.Cliente;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	private ClienteDao clienteDao;

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	@Transactional
	public void addCliente(Cliente cliente) {
		this.clienteDao.addCliente(cliente);
	}

	@Override
	@Transactional
	public void updateCliente(Cliente cliente) {
		this.clienteDao.updateCliente(cliente);
	}

	@Override
	@Transactional
	public List<Cliente> listClientes() {
		return this.clienteDao.listClientes();
	}

	@Override
	@Transactional
	public Cliente getClienteById(int id) {
		return this.clienteDao.getClienteById(id);
	}

	@Override
	@Transactional
	public void deleteCliente(int id) {
		this.clienteDao.deleteCliente(id);
	}
	
	@Override
	@Transactional
	public Cliente findByUsername(String username) {
		return this.clienteDao.findByUsername(username);
	}
	
}
