package br.com.matheusbodo.rspt.views;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.UserFormLayout;
import br.com.matheusbodo.rspt.repository.UserRepository;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class UserFormView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = 1557151015690939657L;

	private static final Logger LOGGER = Logger.getLogger(UserFormView.class);
	
	private User user;
	
	private UserFormLayout layout;
	
	private BeanFieldGroup<User> binder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	public UserFormView() {
		layout = new UserFormLayout();
		addComponent(layout);
		
		binder = bindFields();
		setupSaveButton(binder);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		user = getUser(event);
		if (user == null) {
			UI.getCurrent().getNavigator().navigateTo("manageUsers");
			Notification.show("User not found.", Type.ERROR_MESSAGE);
		}
		binder.setItemDataSource(user);
	}
	
	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.USER, Role.ADMIN };
	}

	private void setupSaveButton(final BeanFieldGroup<User> binder) {
		layout.getBtnSave().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 8659462486777133006L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					userRepository.save(user);
					UI.getCurrent().getNavigator().navigateTo("manageUsers");
					Notification.show("User " + user.getEmail() + " saved.", Type.TRAY_NOTIFICATION);
				} catch (CommitException e) {
					LOGGER.error("Error saving user.", e);
					Notification.show("Error saving user. Verify the data provided and try again.", Type.ERROR_MESSAGE);
				} catch (Exception e) {
					LOGGER.error("Error saving user.", e);
					Notification.show("Error saving user", Type.ERROR_MESSAGE);
				}
			}
		});
	}

	private BeanFieldGroup<User> bindFields() {
		final BeanFieldGroup<User> binder = new BeanFieldGroup<User>(User.class);
		binder.bind(layout.getFieldEmail(), "email");
		binder.bind(layout.getComboRole(), "role");
		return binder;
	}

	protected abstract User getUser(ViewChangeEvent event);
	
}
