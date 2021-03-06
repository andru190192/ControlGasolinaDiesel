package ec.com.distrito.tesisControlGasolina.seguridad.controller;

import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.redireccionar;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.control.service.ChoferService;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.Menu;
import ec.com.distrito.tesisControlGasolina.seguridad.service.MenuService;
import ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion;

@Controller
@Scope("session")
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MenuService menuService;

	@Autowired
	private ChoferService choferService;

	private MenuModel menuModel;
	private String nombreUsuario;

	public MenuBean() {
	}

	public void cargarMenu() {
		if (menuModel == null) {
			menuModel = new DefaultMenuModel();

			DefaultSubMenu subMenu1 = null;
			DefaultSubMenu subMenu2 = null;
			DefaultSubMenu subMenu3 = null;
			DefaultMenuItem menuItem = null;

			int padre1 = 0;
			int padre2 = 0;
			int padre3 = 0;

			List<Menu> listMenu = menuService
					.obtenerPorUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
			for (Menu menu : listMenu) {
				if (menu.getVista().compareTo("-") == 0) {
					if (menu.getNivel() == 1) {
						padre1 = menu.getId();
						subMenu1 = new DefaultSubMenu(menu.getNombre(), "fa " + menu.getIcono());
						menuModel.addElement(subMenu1);
						menuModel.addElement(new DefaultSeparator());
					} else if (menu.getNivel() == 2) {
						padre2 = menu.getId();
						subMenu2 = new DefaultSubMenu(menu.getNombre(), "fa " + menu.getIcono());
						subMenu1.addElement(subMenu2);
					} else if (menu.getNivel() == 3) {
						padre3 = menu.getId();
						subMenu3 = new DefaultSubMenu(menu.getNombre(), "fa " + menu.getIcono());
						subMenu2.addElement(subMenu3);
					}
				} else {
					menuItem = new DefaultMenuItem(menu.getNombre(), "fa " + menu.getIcono(), menu.getVista());
					menuItem.setAjax(true);
					menuItem.setUpdate("centro");
					if (padre1 == menu.getPadre())
						subMenu1.addElement(menuItem);
					else if (padre2 == menu.getPadre())
						subMenu2.addElement(menuItem);
					else if (padre3 == menu.getPadre())
						subMenu3.addElement(menuItem);
				}
			}
		}
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	@PostConstruct
	public void init() {
		UtilsAplicacion.actualizarPaginaWeb("www.educacion.gob.ec");
		Chofer p = choferService
				.obtenerActivoPorCedula(SecurityContextHolder.getContext().getAuthentication().getName());
		setNombreUsuario(p.getNombre() + " " + p.getApellido());
		cargarMenu();
	}

	public void cargarSistema() {

	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}