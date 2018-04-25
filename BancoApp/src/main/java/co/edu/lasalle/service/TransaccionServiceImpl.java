package co.edu.lasalle.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.lasalle.dao.CuentaDao;
import co.edu.lasalle.dao.TransaccionDao;
import co.edu.lasalle.dao.DestinatarioDao;
import co.edu.lasalle.model.Cuenta;
import co.edu.lasalle.model.Transaccion;
import co.edu.lasalle.model.Destinatario;
import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.service.TransaccionService;
import co.edu.lasalle.service.ClienteService;

@Service
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TransaccionDao transaccionDao;

	@Autowired
	private CuentaDao cuentaDao;
	
	@Autowired
	private DestinatarioDao destinatarioDao;

	public List<Transaccion> findTransaccionList(String username) {
		Cliente cliente = clienteService.findByUsername(username);
		List<Transaccion> transaccionList = cliente.getCuenta().getTransaccionList();

		return transaccionList;
	}

	@Override
	@Transactional
	public void saveTransaccionConsignacion(Transaccion transaccion) {
		transaccionDao.addTransaccion(transaccion);
	}

	@Override
	@Transactional
	public void saveTransaccionRetiro(Transaccion transaccion) {
		transaccionDao.addTransaccion(transaccion);
	}
	
	@Override
	@Transactional
	public void transferenciaEntreCuentas(String cuentaOrigen, String cuentaDestino,
											String monto, Cuenta cuenta) {
        
            cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal(monto)));
            cuentaDao.addCuenta(cuenta);

            Date fecha = new Date();

            Transaccion transaccion = new Transaccion(fecha, "Transferencia entre cuentas desde " + cuentaOrigen + " hacia " + cuentaDestino, "Cuenta", "Finalizado", Double.parseDouble(monto), cuenta.getSaldo(), cuenta);
            transaccionDao.addTransaccion(transaccion);
    }

	@Override
	@Transactional
	public List<Destinatario> findDestinatarioList(Principal principal) {
        String username = principal.getName();
        List<Destinatario> destinatarioList = destinatarioDao.findAll().stream()
                .filter(destinatario -> username.equals(destinatario.getCliente().getUsername()))
                .collect(Collectors.toList());

        return destinatarioList;
    }

	@Override
	@Transactional
    public Destinatario saveDestinatario(Destinatario destinatario) {
        return destinatarioDao.addDestinatario(destinatario);
    }

	@Override
	@Transactional
    public Destinatario findDestinatarioByNombre(String nombreDestinatario) {
        return destinatarioDao.findByNombre(nombreDestinatario);
    }

	@Override
	@Transactional
    public void deleteDestinatarioByNombre(String nombreDestinatario) {
    	destinatarioDao.deleteByNombre(nombreDestinatario);
    }
    
	@Override
	@Transactional
    public void transferenciaATercero(Destinatario destinatario, String monto, Cuenta cuenta) {

            cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal(monto)));
            cuentaDao.addCuenta(cuenta);

            Date fecha = new Date();

            Transaccion transaccion = new Transaccion(fecha, "Transferencia al destinatario " + destinatario.getNombre(), "Transferencia", "Finalizado", Double.parseDouble(monto), cuenta.getSaldo(), cuenta);
            transaccionDao.addTransaccion(transaccion);
    }

	public void setTransaccionDao(TransaccionDao transaccionDao) {
		this.transaccionDao = transaccionDao;
	}

	public void setCuentaDao(CuentaDao cuentaDao) {
		this.cuentaDao = cuentaDao;
	}
}

