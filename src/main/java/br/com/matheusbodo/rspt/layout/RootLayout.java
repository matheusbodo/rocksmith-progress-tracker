package br.com.matheusbodo.rspt.layout;

import org.springframework.stereotype.Component;

import com.vaadin.ui.VerticalLayout;

@Component
public class RootLayout extends VerticalLayout {

	private static final long serialVersionUID = -7332042973039224297L;
	
	private VerticalLayout content;
	
	public RootLayout() {
		addStyleName("main-view");
		setSizeFull();
		
		content = new VerticalLayout();
		content.setHeight(100, Unit.PERCENTAGE);
		addComponent(content);
	}
	
	public VerticalLayout getContent() {
		return content;
	}
}
