package br.com.matheusbodo.rspt.layout.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.service.UserService;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Component
public class EditUserButtonGenerator implements ColumnGenerator {

	private static final long serialVersionUID = -127265132668957368L;
	
	@Autowired
	private UserService userService;

	@Override
	public HorizontalLayout generateCell(CustomTable source, Object itemId, Object columnId) {
		HorizontalLayout layout = new HorizontalLayout();
		createEditUserButton(source, itemId, layout);
		createChangePasswordButton(source, itemId, layout);
		return layout;
	}

	private void createEditUserButton(CustomTable source, Object itemId,HorizontalLayout layout) {
		Button button = new Button();
		button.setIcon(FontAwesome.PENCIL);
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		final Long id = (Long) source.getItem(itemId).getItemProperty("id").getValue();
		
		button.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4063224807254283421L;
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo("editUser/" + id);
			}
		});
		layout.addComponent(button);
	}
	
	private void createChangePasswordButton(CustomTable source, Object itemId,HorizontalLayout layout) {
		Button button = new Button();
		button.setIcon(FontAwesome.KEY);
		button.addStyleName(ValoTheme.BUTTON_DANGER);
		
		final Long id = (Long) source.getItem(itemId).getItemProperty("id").getValue();
		
		button.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4063224807254283421L;
			@Override
			public void buttonClick(ClickEvent event) {
				showChangePasswordModal(id);
			}
		});
		layout.addComponent(button);
	}
	
	private void showChangePasswordModal(final Long userId) {
		final Window changePasswordModal = new Window("Change Password");
		changePasswordModal.setClosable(true);
		changePasswordModal.setModal(true);
		
		FormLayout changePasswordForm = new FormLayout();
		changePasswordForm.setWidthUndefined();
		
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
					User user = userService.findOne(userId);
					fieldNewPassword.validate();
					fieldNewPasswordConfirmation.validate();
					userService.changeUserPassword(user, fieldNewPassword.getValue());
					Notification.show("Password changed!", Type.HUMANIZED_MESSAGE);
					changePasswordModal.close();
				} catch (InvalidValueException e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		});
		
		UI.getCurrent().addWindow(changePasswordModal);
	}

}
