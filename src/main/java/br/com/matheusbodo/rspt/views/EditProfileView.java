package br.com.matheusbodo.rspt.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.Role;
import br.com.matheusbodo.rspt.layout.EditProfileLayout;
import br.com.matheusbodo.rspt.security.SecuredView;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Component
@UIScope
@VaadinView(name="editProfile")
public class EditProfileView extends VerticalLayout implements SecuredView {

	private static final long serialVersionUID = -7183681152488114450L;
	
	private EditProfileLayout layout;
	
	@Autowired
	private UserService userService;
	
	public EditProfileView() {
		layout = new EditProfileLayout();
		addComponent(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		User user = userService.findLoggedUser();
		layout.getLblUser().setValue(user.getEmail());
		
		layout.getBtnChangePassword().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 8467579485305420641L;
			@Override
			public void buttonClick(ClickEvent event) {
				showChangePasswordModal();
			}
		});
		
		layout.getBtnResetData().addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4280325017297738047L;

			@Override
			public void buttonClick(ClickEvent event) {
				showConfirmActionModal("Reset data", "Are you sure you want to delete all data?", new ClickListener() {
					private static final long serialVersionUID = -8031028508897549752L;
					@Override
					public void buttonClick(ClickEvent event) {
						userService.resetData();
						Notification.show("All data erased.", Type.HUMANIZED_MESSAGE);
					}
				});
			}
		});
		
		layout.getBtnDeleteAccount().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 700374659879620310L;
			@Override
			public void buttonClick(ClickEvent event) {
				showConfirmActionModal("Delete account", "Are you sure you want to delete your account?", new ClickListener() {
					private static final long serialVersionUID = -3341002888772155513L;
					@Override
					public void buttonClick(ClickEvent event) {
						userService.deleteAccount();
						SecurityContextHolder.getContext().setAuthentication(null);
						Page.getCurrent().setLocation("/");
						Notification.show("Account deleted.", Type.HUMANIZED_MESSAGE);
					}
				});
			}
		});
	}

	@Override
	public Role[] getAuthorizedRoles() {
		return new Role[] { Role.USER };
	}
	
	private void showChangePasswordModal() {
		final Window changePasswordModal = new Window("Change Password");
		changePasswordModal.setClosable(true);
		changePasswordModal.setModal(true);
		
		FormLayout changePasswordForm = new FormLayout();
		changePasswordForm.setWidthUndefined();
		
		final PasswordField fieldCurrentPassword = new PasswordField();
		fieldCurrentPassword.setImmediate(true);
		fieldCurrentPassword.setCaption("Current password:");
		changePasswordForm.addComponent(fieldCurrentPassword );
		
		final PasswordField fieldNewPassword = new PasswordField();
		fieldNewPassword.setImmediate(true);
		fieldNewPassword.setCaption("New password:");
		fieldNewPassword.addValidator(new StringLengthValidator("Password must contain at least 6 characters", 6, Integer.MAX_VALUE, false));
		changePasswordForm.addComponent(fieldNewPassword );
		
		
		final PasswordField fieldNewPasswordConfirmation = new PasswordField();
		fieldNewPasswordConfirmation.setImmediate(true);
		fieldNewPasswordConfirmation.setCaption("Repeat new password:");
		fieldNewPasswordConfirmation.addValidator(new StringLengthValidator("Password must contain at least 6 characters", 6, Integer.MAX_VALUE, false));
		fieldNewPasswordConfirmation.addValidator(new Validator() {
			private static final long serialVersionUID = 2990149315356237268L;
			@Override
			public void validate(Object value) throws InvalidValueException {
				String valueStr = (String) value;
				if (!valueStr.equals(fieldNewPassword.getValue())) {
					throw new InvalidValueException("The passwords informed are not the same.");
				}
			}
		});
		changePasswordForm.addComponent(fieldNewPasswordConfirmation );
		
		Button btnChangePassword = new Button();
		btnChangePassword.setCaption("Change It!");
		btnChangePassword.addStyleName(ValoTheme.BUTTON_PRIMARY);
		changePasswordForm.addComponent(btnChangePassword);
		
		changePasswordModal.setContent(changePasswordForm);
		changePasswordModal.center();
		
		btnChangePassword.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4300656986354501538L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					fieldNewPassword.validate();
					fieldNewPasswordConfirmation.validate();
					if (!userService.isValidCurrentPassword(fieldCurrentPassword.getValue())) {
						Notification.show("Current password is incorrect.", Type.ERROR_MESSAGE);
					} else {
						userService.changeCurrentPassword(fieldNewPassword.getValue());
						Notification.show("Password changed!", Type.HUMANIZED_MESSAGE);
						changePasswordModal.close();
					}
				} catch (InvalidValueException e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		});
		
		UI.getCurrent().addWindow(changePasswordModal);
	}
	
	public void showConfirmActionModal(String title, String message, ClickListener confirmClickListener) {
		final Window confirmActionModal = new Window(title);
		confirmActionModal.setClosable(true);
		confirmActionModal.setModal(true);
		
		VerticalLayout layout = new VerticalLayout();
		confirmActionModal.setContent(layout);
		layout.setWidthUndefined();
		
		Label label = new Label();
		label.setValue(message);
		layout.addComponent(label);
		
		Button btnConfirm = new Button();
		btnConfirm.addStyleName(ValoTheme.BUTTON_DANGER);
		btnConfirm.addClickListener(confirmClickListener);
		btnConfirm.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 3551396070270816055L;
			@Override
			public void buttonClick(ClickEvent event) {
				confirmActionModal.close();
			}
		});
		
		confirmActionModal.center();
		UI.getCurrent().addWindow(confirmActionModal);
	}

}
