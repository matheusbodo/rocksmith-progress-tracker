package br.com.matheusbodo.rspt.views;

import br.com.matheusbodo.rspt.RSPTUI;
import br.com.matheusbodo.rspt.layout.LoginLayout;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	private static final long serialVersionUID = 7514518743878091838L;

	public LoginView() {
		final LoginLayout layout = new LoginLayout();
		addComponent(layout);
		setSizeFull();
		
		layout.getBtnLogin().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				//TODO do proper login
				if ("admin".equals(layout.getFieldLogin().getValue()) && "admin".equals(layout.getFieldLogin().getValue())) {
					RSPTUI rsptUI = RSPTUI.getCurrent();
					rsptUI.displayApplication();
					rsptUI.getNavigator().navigateTo("");
				} else {
					Notification.show("Login failed", Type.WARNING_MESSAGE);
				}
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}

}
