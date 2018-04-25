package co.edu.lasalle.service;

import java.security.Principal;
import java.util.List;

import co.edu.lasalle.model.Cuenta;
import co.edu.lasalle.model.Transaccion;
import co.edu.lasalle.model.Destinatario;

public interface TransaccionService {
	
	public List<Transaccion> findTransaccionList(String username);
	public void saveTransaccionConsignacion(Transaccion transaccion);
	public void saveTransaccionRetiro(Transaccion transaccion);
	public void transferenciaEntreCuentas(String cuentaOrigen, String cuentaDestino,
											String monto, Cuenta cuenta);
	public List<Destinatario> findDestinatarioList(Principal principal);
	public Destinatario saveDestinatario(Destinatario destinatario);
	public Destinatario findDestinatarioByNombre(String nombreDestinatario);
	public void deleteDestinatarioByNombre(String nombreDestinatario);
	public void transferenciaATercero(Destinatario destinatario, String monto, Cuenta cuenta);
	
}
