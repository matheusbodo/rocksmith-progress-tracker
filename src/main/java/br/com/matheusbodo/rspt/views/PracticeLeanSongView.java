package br.com.matheusbodo.rspt.views;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.LearnSongPracticeLog;
import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.PracticeLearnSongLayout;
import br.com.matheusbodo.rspt.repository.LearnSongPracticeLogRepository;
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
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(name="practiceLearnSong")
public class PracticeLeanSongView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = -2456729066457924348L;
	
	private static final Logger LOGGER = Logger.getLogger(PracticeLeanSongView.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LearnSongPracticeLogRepository learnSongPracticeLogRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	private PracticeLearnSongLayout layout;
	
	private BeanFieldGroup<LearnSongPracticeLog> binder;
	
	public PracticeLeanSongView() {
		layout = new PracticeLearnSongLayout();
		addComponent(layout);
		
		binder = bindFields();
		layout.getBtnSave().addClickListener(new ClickListener() {
			private static final long serialVersionUID = -2341703478688555832L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					LearnSongPracticeLog log = binder.getItemDataSource().getBean();
					if (log.getMinutes() == null) {
						log.setMinutes(0);
					}
					log.setPracticeDate(Calendar.getInstance());
					log.setUser(userService.findLoggedUser());
					learnSongPracticeLogRepository.save(log);
					Notification.show("Practice log saved.");
					binder.setItemDataSource(new LearnSongPracticeLog());
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
		binder.setItemDataSource(new LearnSongPracticeLog());
		
		layout.getComboSongs().removeAllItems();
		
		List<Song> userSongs = songRepository.findByUser(userService.findLoggedUser());
		for (Song song : userSongs) {
			layout.getComboSongs().addItem(song);
			layout.getComboSongs().setItemCaption(song, song.getArtist() + " - " + song.getTitle());
		}
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.ADMIN, Role.USER };
	}
	
	private BeanFieldGroup<LearnSongPracticeLog> bindFields() {
		final BeanFieldGroup<LearnSongPracticeLog> binder = new BeanFieldGroup<LearnSongPracticeLog>(LearnSongPracticeLog.class);
		binder.bind(layout.getComboSongs(), "song");
		binder.bind(layout.getComboPath(), "path");
		binder.bind(layout.getFieldMinutes(), "minutes");
		binder.bind(layout.getFieldAccuracy(), "accuracy");
		binder.bind(layout.getFieldCompleted(), "completed");
		return binder;
	}

}
