package br.com.matheusbodo.rspt.views;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.SongFormLayout;
import br.com.matheusbodo.rspt.repository.SongRepository;
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

public abstract class SongFormView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = 1557151015690939657L;

	private static final Logger LOGGER = Logger.getLogger(SongFormView.class);
	
	private Song song;
	
	private SongFormLayout layout;
	
	private BeanFieldGroup<Song> binder;
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private UserService userService;
	
	public SongFormView() {
		layout = new SongFormLayout();
		addComponent(layout);
		
		binder = bindFields();
		setupSaveButton(binder);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		song = getSong(event);
		if (song == null) {
			UI.getCurrent().getNavigator().navigateTo("manageSongs");
			Notification.show("Song not found.", Type.ERROR_MESSAGE);
		}
		binder.setItemDataSource(song);
	}
	
	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.USER, Role.ADMIN };
	}

	private void setupSaveButton(final BeanFieldGroup<Song> binder) {
		layout.getBtnSave().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 8659462486777133006L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					song.setUser(userService.findLoggedUser());
					songRepository.save(song);
					UI.getCurrent().getNavigator().navigateTo("manageSongs");
					Notification.show("Song " + song.getTitle() + " saved.", Type.TRAY_NOTIFICATION);
				} catch (CommitException e) {
					LOGGER.error("Error saving song.", e);
					Notification.show("Error saving song. Verify the data provided and try again.", Type.ERROR_MESSAGE);
				} catch (Exception e) {
					LOGGER.error("Error saving song.", e);
					Notification.show("Error saving song", Type.ERROR_MESSAGE);
				}
			}
		});
	}

	private BeanFieldGroup<Song> bindFields() {
		final BeanFieldGroup<Song> binder = new BeanFieldGroup<Song>(Song.class);
		binder.bind(layout.getFieldArtist(), "artist");
		binder.bind(layout.getFieldTitle(), "title");
		return binder;
	}

	protected abstract Song getSong(ViewChangeEvent event);
	
}
