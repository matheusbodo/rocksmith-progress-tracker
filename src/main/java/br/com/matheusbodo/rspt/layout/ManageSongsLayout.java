package br.com.matheusbodo.rspt.layout;

import java.util.Arrays;

import lombok.Getter;

import org.tepi.filtertable.paged.PagedFilterControlConfig;
import org.tepi.filtertable.paged.PagedFilterTable;

import br.com.matheusbodo.rspt.layout.generator.EditSongButtonGenerator;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Getter
public class ManageSongsLayout extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 4502510763096522780L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	@SuppressWarnings("rawtypes")
	private PagedFilterTable tbSongs;
	@AutoGenerated
	private Button btnNewSong;
	@AutoGenerated
	private Label lblTitle;
	
	private PagedFilterControlConfig tableConfig;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ManageSongsLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		lblTitle.addStyleName(ValoTheme.LABEL_H1);
		btnNewSong.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		tbSongs.setFilterBarVisible(true);
		tbSongs.addContainerProperty("artist", String.class, null);
		tbSongs.setColumnHeader("artist", "Artist");
		tbSongs.addContainerProperty("title", String.class, null);
		tbSongs.setColumnHeader("title", "Title");
		
		tbSongs.addContainerProperty("id", Long.class, null);
		tbSongs.addContainerProperty("artist", String.class, null);
		tbSongs.addContainerProperty("title", String.class, null);
		tbSongs.addContainerProperty("edit", Button.class, null);
		tbSongs.addGeneratedColumn("edit", new EditSongButtonGenerator());
		

		tbSongs.setColumnHeader("id", "ID");
		tbSongs.setColumnHeader("artist", "Artista");
		tbSongs.setColumnHeader("title", "Música");
		
		tbSongs.setFilterBarVisible(true);
		tbSongs.setPageLength(15);
		tableConfig = new PagedFilterControlConfig();
		tableConfig.setPageLengthsAndCaptions(Arrays.asList(10, 20, 50));
		mainLayout.addComponent(tbSongs.createControls(tableConfig));
	}

	@AutoGenerated
	@SuppressWarnings("rawtypes")
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// lblTitle
		lblTitle = new Label();
		lblTitle.setImmediate(false);
		lblTitle.setWidth("-1px");
		lblTitle.setHeight("-1px");
		lblTitle.setValue("Manage Songs");
		mainLayout.addComponent(lblTitle);
		mainLayout.setComponentAlignment(lblTitle, new Alignment(20));
		
		// btnSave
		btnNewSong = new Button();
		btnNewSong.setCaption("Add Song");
		btnNewSong.setImmediate(false);
		btnNewSong.setWidth("-1px");
		btnNewSong.setHeight("-1px");
		mainLayout.addComponent(btnNewSong);
		mainLayout.setComponentAlignment(btnNewSong, new Alignment(6));
		
		// tbSongs
		tbSongs = new PagedFilterTable();
		tbSongs.setImmediate(false);
		tbSongs.setWidth("100.0%");
		tbSongs.setHeight("-1px");
		mainLayout.addComponent(tbSongs);
		
		return mainLayout;
	}

}