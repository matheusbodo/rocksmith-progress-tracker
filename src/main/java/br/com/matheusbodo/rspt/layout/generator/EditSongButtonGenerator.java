package br.com.matheusbodo.rspt.layout.generator;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class EditSongButtonGenerator implements ColumnGenerator {

	private static final long serialVersionUID = -127265132668957368L;

	@Override
	public Object generateCell(CustomTable source, Object itemId, Object columnId) {
		Button button = new Button();
		button.setIcon(FontAwesome.PENCIL);
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		final Long id = (Long) source.getItem(itemId).getItemProperty("id").getValue();
		
		button.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4063224807254283421L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo("editSong/" + id);
			}
		});
		
		return button;
	}

}
