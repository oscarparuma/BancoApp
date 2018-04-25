package co.edu.lasalle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.edu.lasalle.dao.TransaccionDao;
import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.model.Transaccion;

@Repository
public class TransaccionDaoImpl implements TransaccionDao {

	private static final Logger logger = LoggerFactory.getLogger(TransaccionDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addTransaccion(Transaccion transaccion) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(transaccion);
		logger.info("Transaccion creada. Detalles = " + transaccion);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transaccion> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Transaccion> transaccionesList = session.createQuery("from Transaccion").list();
		for(Transaccion transaccion : transaccionesList){
			logger.info("Transacciones: " + transaccion);
		}
		return transaccionesList;
	}
}
