package co.edu.lasalle.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.edu.lasalle.model.Cuenta;

@Repository
public class CuentaDaoImpl implements CuentaDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCuenta(Cuenta cuenta) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(cuenta);
		logger.info("Cuenta creada. Detalles = " + cuenta);
	}
	
	@Override
	public Cuenta findByNumeroCuenta(int numeroCuenta) {
		Session session = this.sessionFactory.getCurrentSession();		
		Cuenta cuenta = (Cuenta) session.load(Cuenta.class, numeroCuenta);
		logger.info("Cuenta encontrada. Detalles = " + cuenta);
		return cuenta;
	}

}

