package br.com.matheusbodo.rspt.security;

import br.com.matheusbodo.rspt.entity.enums.Role;

import com.vaadin.navigator.View;

public interface SecuredView extends View {

	Role[] getAuthorizedRoles(); 
}
