package br.com.matheusbodo.rspt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;
import org.vaadin.spring.VaadinUI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("rspt")
@Title("Login UI")
@VaadinUI(path="/login")
@Widgetset(value="br.com.rspt.RSPTWidgetSet")
public class LoginUI extends UI implements ClickListener {

	@Autowired
	@Qualifier(value="authenticationManager")
	private AuthenticationManager authenticationManager;

    private static final long serialVersionUID = -8667709390052949671L;

    private VerticalLayout layout;

    private TextField username;
    private PasswordField password;

    private Button btnLogin;
    
    GoogleAnalyticsTracker tracker;

    @PostConstruct
    public void initLayout() {
        layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addStyleName("login-background");
        
        Label labelTitle = new Label();
        labelTitle.setValue("Rocksmith Practice Track");
        labelTitle.addStyleName(ValoTheme.LABEL_HUGE);
        layout.addComponent(labelTitle);
        layout.setComponentAlignment(labelTitle, Alignment.MIDDLE_CENTER);

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.addStyleName("login-box");
        loginLayout.setHeightUndefined();
        loginLayout.setWidth("300px");
        loginLayout.setSpacing(true);
        loginLayout.setMargin(true);

        username = new TextField("Username");
        username.setWidth("80%");
        password = new PasswordField("Password");
        password.setWidth("80%");

        btnLogin = new Button("Login");
        btnLogin.addClickListener(this);

        loginLayout.addComponent(username);
        loginLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        loginLayout.addComponent(password);
        loginLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        loginLayout.addComponent(btnLogin);
        loginLayout.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);
        
        layout.addComponent(loginLayout);
        layout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
        layout.setExpandRatio(loginLayout, 1);
        
        tracker = new GoogleAnalyticsTracker("UA-56035202-1");
        tracker.extend(this);
        tracker.trackPageview("login");
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(layout);
        getPage().setTitle("Rocksmith Progress Tracker");
    }

    @Override
    public void buttonClick(ClickEvent event) {
    	try {
	    	Authentication authentication = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
	    	authentication = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			getPage().setLocation("/");
    	} catch (Exception e) {
			Notification.show("Login failed.", Type.ERROR_MESSAGE);
		}
    }

}
