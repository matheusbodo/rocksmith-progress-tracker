package br.com.matheusbodo.rspt.security;

import org.springframework.stereotype.Component;

import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.util.SecurityUtil;

import com.vaadin.navigator.ViewChangeListener;

@Component
public class RSPTViewChangeListener implements ViewChangeListener {

	private static final long serialVersionUID = -1645544112790565575L;
	
	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		if (event.getNewView() instanceof SecuredView) {
			SecuredView securedView = (SecuredView) event.getNewView();
			for (Role role : securedView.getAuthorizedRoles()) {
				if (SecurityUtil.hasRole(role)) {
					return true;
				}
			}
			event.getNavigator().navigateTo("acessoNegado");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {

	}
	
}
