package co.edu.lasalle.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.lasalle.dao.DestinatarioDao;
import co.edu.lasalle.model.Destinatario;

public class DestinatarioDaoImpl implements DestinatarioDao {

	private static final Logger logger = LoggerFactory.getLogger(DestinatarioDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Destinatario addDestinatario(Destinatario destinatario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(destinatario);
		logger.info("Destinatario creado. Detalles = " + destinatario);
		return destinatario;
	}
	
	@Override
	public Destinatario findByNombre(String nombre) {
		Session session = this.sessionFactory.getCurrentSession();		
		Destinatario destinatario = (Destinatario) session.load(Destinatario.class, nombre);
		logger.info("Destinatario encontrado. Detalles = " + destinatario);
		return destinatario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Destinatario> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Destinatario> destinatariosList = session.createQuery("from Destinatario").list();
		for(Destinatario destinatario : destinatariosList){
			logger.info("Destinatarios: " + destinatario);
		}
		return destinatariosList;
	}
	
	@Override
	public void deleteByNombre(String nombre) {
		Session session = this.sessionFactory.getCurrentSession();
		Destinatario destinatario = (Destinatario) session.load(Destinatario.class, nombre);
		if(null != destinatario){
			session.delete(destinatario);
		}
		logger.info("Destinatario borrado. Detalles = " + destinatario);
	}
}
