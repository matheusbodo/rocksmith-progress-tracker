package br.com.matheusbodo.rspt.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.repository.UserRepository;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@Component
@UIScope
@VaadinView(name="editUser")
public class EditUserView  extends UserFormView {

	private static final long serialVersionUID = 3935122536159347058L;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected User getUser(ViewChangeEvent event) {
		String idStr = event.getParameters();
		Long id = 0L;
		try {
			id = Long.parseLong(idStr);
		} catch (NumberFormatException e) { }
		
		return userRepository.findOne(id);
	}

}
