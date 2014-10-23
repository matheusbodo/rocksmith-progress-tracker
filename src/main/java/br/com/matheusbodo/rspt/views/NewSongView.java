package br.com.matheusbodo.rspt.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@Component
@UIScope
@VaadinView(name="songs/new")
public class NewSongView extends SongFormView {

	private static final long serialVersionUID = 2781235417364076111L;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected Song getSong(ViewChangeEvent event) {
		Song song = new Song();
		song.setUser(userService.findLoggedUser());
		return song;
	}


}
