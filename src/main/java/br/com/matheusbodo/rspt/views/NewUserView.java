package br.com.matheusbodo.rspt.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@Component
@UIScope
@VaadinView(name="users/new")
public class NewUserView extends UserFormView {

	private static final long serialVersionUID = 2781235417364076111L;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected User getUser(ViewChangeEvent event) {
		return new User();
	}


}
