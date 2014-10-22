package br.com.matheusbodo.rspt;

import javax.annotation.PostConstruct;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.spring.VaadinUI;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@VaadinUI(path="/login")
@Title("Login UI")
public class LoginUI extends UI implements ClickListener {


    private static final long serialVersionUID = -8667709390052949671L;

    private VerticalLayout layout;

    private TextField username;
    private PasswordField password;

    private Button btnLogin;

    @PostConstruct
    public void initLayout() {
        layout = new VerticalLayout();
        layout.setSizeFull();

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setSizeUndefined();
        loginLayout.setSpacing(true);
        loginLayout.addComponent(new Label("Login"));

        username = new TextField("Username");
        password = new PasswordField("Password");

        btnLogin = new Button("Login");
        btnLogin.addClickListener(this);

        loginLayout.addComponent(username);
        loginLayout.addComponent(password);
        loginLayout.addComponent(btnLogin);

        layout.addComponent(loginLayout);
        layout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);

    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(layout);

    }

    @Override
    public void buttonClick(ClickEvent event) {

        try {
        	Authentication authentication = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
			SecurityContextHolder.getContext().setAuthentication(authentication);
            getPage().setLocation("/");
        } catch (AuthenticationException  e) {
            Notification.show("Login failed.", e.getMessage(), Type.ERROR_MESSAGE);
        }



    }

}
