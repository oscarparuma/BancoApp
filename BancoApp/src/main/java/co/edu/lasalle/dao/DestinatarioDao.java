package co.edu.lasalle.dao;

import java.util.List;

import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.model.Destinatario;

public interface DestinatarioDao {
	
	public Destinatario addDestinatario(Destinatario destinatario);
    public List<Destinatario> findAll();
    public Destinatario findByNombre(String nombreDestinatario);
    void deleteByNombre(String nombreDestinatario);
}
