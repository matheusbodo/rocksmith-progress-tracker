package br.com.matheusbodo.rspt.views;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@VaadinView(name="accessDenied")
public class AccessDeniedView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 7568118033361970750L;

	public AccessDeniedView() {
		Label label = new Label("Acess denied");
		label.addStyleName(ValoTheme.LABEL_H1);
		addComponent(label);
		setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
