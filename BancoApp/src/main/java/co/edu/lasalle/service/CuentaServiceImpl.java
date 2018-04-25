package co.edu.lasalle.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.lasalle.dao.CuentaDao;
import co.edu.lasalle.model.Cuenta;
import co.edu.lasalle.model.Transaccion;
import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.service.CuentaService;
import co.edu.lasalle.service.TransaccionService;
import co.edu.lasalle.service.ClienteService;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

	private static int nextAccountNumber = 11111111;

	@Autowired
	private CuentaDao cuentaDao;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TransaccionService transaccionService;

	@Override
	@Transactional
	public Cuenta addCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setSaldo(new BigDecimal(0.0));
		cuenta.setNumeroCuenta(cuentaGen());

		cuentaDao.addCuenta(cuenta);

		return cuentaDao.findByNumeroCuenta(cuenta.getNumeroCuenta());
	}
	
	@Override
	@Transactional
	public void consignacion(double monto, Principal principal) {
        Cliente cliente = clienteService.findByUsername(principal.getName());

        Cuenta cuenta = cliente.getCuenta();
        cuenta.setSaldo(cuenta.getSaldo().add(new BigDecimal(monto)));
        cuentaDao.addCuenta(cuenta);

        Date fecha = new Date();

        Transaccion transaccion = new Transaccion(fecha, "Congignación", "Cuenta", "Finalizado", monto, cuenta.getSaldo(), cuenta);
        transaccionService.saveTransaccionConsignacion(transaccion);
    }
    
	@Override
	@Transactional
    public void retiro(double monto, Principal principal) {
        Cliente cliente = clienteService.findByUsername(principal.getName());

        Cuenta cuenta = cliente.getCuenta();
        cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal(monto)));
        cuentaDao.addCuenta(cuenta);

        Date fecha = new Date();

        Transaccion transaccion = new Transaccion(fecha, "Retiro", "Cuenta", "Finalizado", monto, cuenta.getSaldo(), cuenta);
        transaccionService.saveTransaccionRetiro(transaccion);
    }

	private int cuentaGen() {
		return ++nextAccountNumber;
	}

	public void setCuentaDao(CuentaDao cuentaDao) {
		this.cuentaDao = cuentaDao;
	}

}
