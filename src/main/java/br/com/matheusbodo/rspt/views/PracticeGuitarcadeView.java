package br.com.matheusbodo.rspt.views;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.GuitarcadePracticeLog;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.PracticeGuitarcadeLayout;
import br.com.matheusbodo.rspt.repository.GuitarcadePracticeLogRepository;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="practiceGuitarcade")
public class PracticeGuitarcadeView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = -2456729066457924348L;
	
	private static final Logger LOGGER = Logger.getLogger(PracticeGuitarcadeView.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuitarcadePracticeLogRepository guitarcadePracticeLogRepository;
	
	private PracticeGuitarcadeLayout layout;
	
	private BeanFieldGroup<GuitarcadePracticeLog> binder;
	
	public PracticeGuitarcadeView() {
		layout = new PracticeGuitarcadeLayout();
		addComponent(layout);
		
		binder = bindFields();
		layout.getBtnSave().addClickListener(new ClickListener() {
			private static final long serialVersionUID = -2341703478688555832L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					GuitarcadePracticeLog log = binder.getItemDataSource().getBean();
					if (log.getMinutes() == null) {
						log.setMinutes(0);
					}
					log.setPracticeDate(Calendar.getInstance());
					log.setUser(userService.findLoggedUser());
					guitarcadePracticeLogRepository.save(log);
					Notification.show("Practice log saved.");
					binder.setItemDataSource(new GuitarcadePracticeLog());
				} catch (CommitException ce) {
					Notification.show("Error saving practice log. Verify the information provided and try again.", Type.ERROR_MESSAGE);
				} catch (Exception e) {
					LOGGER.error("Error saving guitarcade practice log", e);
					Notification.show("Error saving practice log.", Type.ERROR_MESSAGE);
				}
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		binder.setItemDataSource(new GuitarcadePracticeLog());
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.ADMIN, Role.USER };
	}
	
	private BeanFieldGroup<GuitarcadePracticeLog> bindFields() {
		final BeanFieldGroup<GuitarcadePracticeLog> binder = new BeanFieldGroup<GuitarcadePracticeLog>(GuitarcadePracticeLog.class);
		binder.bind(layout.getComboGames(), "game");
		binder.bind(layout.getComboGameLevels(), "gameLevel");
		binder.bind(layout.getFieldMinutes(), "minutes");
		binder.bind(layout.getFieldScore(), "score");
		return binder;
	}

}
