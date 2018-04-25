package co.edu.lasalle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.edu.lasalle.model.Cliente;

@Repository
public class ClienteDaoImpl implements ClienteDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCliente(Cliente cliente) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cliente);
		logger.info("Cliente creado. Detalles = " + cliente);
	}

	@Override
	public void updateCliente(Cliente cliente) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(cliente);
		logger.info("Cliente .actualizado. Detalles = " + cliente);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listClientes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Cliente> clientesList = session.createQuery("from Cliente").list();
		for(Cliente cliente : clientesList){
			logger.info("Clientes: " + cliente);
		}
		return clientesList;
	}

	@Override
	public Cliente getClienteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Cliente cliente = (Cliente) session.load(Cliente.class, new Integer(id));
		logger.info("Cliente encontrado. Detalles = " + cliente);
		return cliente;
	}

	@Override
	public void deleteCliente(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cliente cliente = (Cliente) session.load(Cliente.class, new Integer(id));
		if(null != cliente){
			session.delete(cliente);
		}
		logger.info("Cliente borrado. Detalles = " + cliente);
	}
	
	@Override
	public Cliente findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();		
		Cliente cliente = (Cliente) session.load(Cliente.class, username);
		logger.info("Cliente encontrado. Detalles = " + cliente);
		return cliente;
	}

}
