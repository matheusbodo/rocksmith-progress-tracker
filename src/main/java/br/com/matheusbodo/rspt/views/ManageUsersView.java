package br.com.matheusbodo.rspt.views;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.ManageUsersLayout;
import br.com.matheusbodo.rspt.layout.generator.EditUserButtonGenerator;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="manageUsers")
public class ManageUsersView  extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = 123872610518386730L;
	
	private ManageUsersLayout layout;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private EditUserButtonGenerator editUserButtonGenerator;
	
	public ManageUsersView() {
		layout = new ManageUsersLayout();
		addComponent(layout);
		
		layout.getBtnNewUser().addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4094109928488038171L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo("users/new");
			}
		});
	}
	
	@PostConstruct
	public void init() {
		layout.getTbUsers().addGeneratedColumn("edit", editUserButtonGenerator);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAContainer<User> jpaContainer = JPAContainerFactory.make(User.class, entityManager);
		layout.getTbUsers().setContainerDataSource(jpaContainer);
		layout.getTbUsers().setColumnCollapsingAllowed(true);
		layout.getTbUsers().setColumnCollapsed("password", true);
		layout.getTbUsers().setColumnCollapsed("rules", true);
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.ADMIN };
	}

}
