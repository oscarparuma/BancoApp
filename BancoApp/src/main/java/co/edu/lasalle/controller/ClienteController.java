package co.edu.lasalle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.lasalle.model.Cliente;
import co.edu.lasalle.service.ClienteService;

// Ref: https://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial

@Controller
public class ClienteController {

	private ClienteService clienteService;
	
	@Autowired(required=true)
	@Qualifier(value="clienteService")
	public void setClienteService(ClienteService clienteService){
		this.clienteService = clienteService;
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public String listClientes(Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("listClientes", this.clienteService.listClientes());
		return "cliente";
	}
	
	// Crea y actualiza un cliente
	@RequestMapping(value= "/cliente/add", method = RequestMethod.POST)
	public String addCliente(@ModelAttribute("cliente") Cliente cliente) {
		
		if(cliente.getId() == 0) {
			// Nuevo cliente
			this.clienteService.addCliente(cliente);
		} else {
			// Actualiza cliente
			this.clienteService.updateCliente(cliente);
		}
		
		return "redirect:/clientes";
		
	}
	
	@RequestMapping("/delete/{id}")
    public String deleteCliente(@PathVariable("id") int id){
		
        this.clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
 
    @RequestMapping("/edit/{id}")
    public String editCliente(@PathVariable("id") int id, Model model){
        model.addAttribute("cliente", this.clienteService.getClienteById(id));
        model.addAttribute("listClientes", this.clienteService.listClientes());
        return "cliente";
    }

}
