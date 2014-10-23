package br.com.matheusbodo.rspt.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.repository.SongRepository;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@Component
@UIScope
@VaadinView(name="editSong")
public class EditSongView  extends SongFormView {

	private static final long serialVersionUID = 3935122536159347058L;

	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected Song getSong(ViewChangeEvent event) {
		String idStr = event.getParameters();
		Long id = 0L;
		try {
			id = Long.parseLong(idStr);
		} catch (NumberFormatException e) { }
		
		return songRepository.findByIdAndUser(id, userService.findLoggedUser());
	}

}
