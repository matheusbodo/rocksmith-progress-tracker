package br.com.matheusbodo.rspt.views;

import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
@VaadinView(name="acessoNegado")
public class AcessoNegadoView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 7568118033361970750L;

	public AcessoNegadoView() {
		addComponent(new Label("Acesso negado"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
