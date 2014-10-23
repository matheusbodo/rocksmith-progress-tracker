package br.com.matheusbodo.rspt.views;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.ManageSongsLayout;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="manageSongs")
public class ManageSongsView  extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = 123872610518386730L;
	
	private ManageSongsLayout layout;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public ManageSongsView() {
		layout = new ManageSongsLayout();
		addComponent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAContainer<Song> jpaContainer = JPAContainerFactory.make(Song.class, entityManager);
		Compare.Equal filter = new Compare.Equal("user", userService.findLoggedUser());
		jpaContainer.addContainerFilter(filter);
		layout.getTbSongs().setContainerDataSource(jpaContainer);
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.USER, Role.ADMIN};
	}

}
