package br.com.matheusbodo.rspt.layout;

import lombok.Getter;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Getter
public class EditOfficeLayout extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = -3177625771303790810L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button btnSalvar;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private FormLayout formLayout_1;
	@AutoGenerated
	private TextField fieldSuburbName;
	@AutoGenerated
	private TextField fieldComplement;
	@AutoGenerated
	private TextField fieldStreet;
	@AutoGenerated
	private FormLayout formAddress;
	@AutoGenerated
	private TextField fieldCity;
	@AutoGenerated
	private TextField fieldState;
	@AutoGenerated
	private TextField fieldNumber;
	@AutoGenerated
	private TextField fieldZipCode;
	@AutoGenerated
	private Label lblAddress;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private FormLayout formLayout_2;
	@AutoGenerated
	private ComboBox comboOwner;
	@AutoGenerated
	private TextField fieldCellPhone;
	@AutoGenerated
	private TextField fieldPhone;
	@AutoGenerated
	private FormLayout formLayout1;
	@AutoGenerated
	private TextField fieldEmail;
	@AutoGenerated
	private TextField fieldDocument;
	@AutoGenerated
	private TextField fieldName;
	@AutoGenerated
	private Label lblTitle;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public EditOfficeLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		lblTitle.addStyleName(ValoTheme.LABEL_H2);
		lblAddress.addStyleName(ValoTheme.LABEL_H2);
		
		btnSalvar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	}

	@AutoGenerated
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
		lblTitle.setValue("Dados do Escritório");
		mainLayout.addComponent(lblTitle);
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// lblAddress
		lblAddress = new Label();
		lblAddress.setImmediate(false);
		lblAddress.setWidth("-1px");
		lblAddress.setHeight("-1px");
		lblAddress.setValue("Endereço");
		mainLayout.addComponent(lblAddress);
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2);
		
		// btnSalvar
		btnSalvar = new Button();
		btnSalvar.setCaption("Salvar");
		btnSalvar.setImmediate(false);
		btnSalvar.setWidth("-1px");
		btnSalvar.setHeight("-1px");
		mainLayout.addComponent(btnSalvar);
		mainLayout.setComponentAlignment(btnSalvar, new Alignment(20));
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(true);
		
		// formLayout1
		formLayout1 = buildFormLayout1();
		horizontalLayout_1.addComponent(formLayout1);
		
		// formLayout_2
		formLayout_2 = buildFormLayout_2();
		horizontalLayout_1.addComponent(formLayout_2);
		
		return horizontalLayout_1;
	}

	@AutoGenerated
	private FormLayout buildFormLayout1() {
		// common part: create layout
		formLayout1 = new FormLayout();
		formLayout1.setImmediate(false);
		formLayout1.setWidth("100.0%");
		formLayout1.setHeight("-1px");
		formLayout1.setMargin(false);
		formLayout1.setSpacing(true);
		
		// fieldName
		fieldName = new TextField();
		fieldName.setCaption("Nome");
		fieldName.setImmediate(false);
		fieldName.setWidth("90.0%");
		fieldName.setHeight("-1px");
		formLayout1.addComponent(fieldName);
		
		// fieldDocument
		fieldDocument = new TextField();
		fieldDocument.setCaption("CPF/CNPJ");
		fieldDocument.setImmediate(false);
		fieldDocument.setWidth("90.0%");
		fieldDocument.setHeight("-1px");
		formLayout1.addComponent(fieldDocument);
		
		// fieldEmail
		fieldEmail = new TextField();
		fieldEmail.setCaption("E-mail");
		fieldEmail.setImmediate(false);
		fieldEmail.setWidth("90.0%");
		fieldEmail.setHeight("-1px");
		formLayout1.addComponent(fieldEmail);
		
		return formLayout1;
	}

	@AutoGenerated
	private FormLayout buildFormLayout_2() {
		// common part: create layout
		formLayout_2 = new FormLayout();
		formLayout_2.setImmediate(false);
		formLayout_2.setWidth("100.0%");
		formLayout_2.setHeight("-1px");
		formLayout_2.setMargin(false);
		formLayout_2.setSpacing(true);
		
		// fieldPhone
		fieldPhone = new TextField();
		fieldPhone.setCaption("Telefone");
		fieldPhone.setImmediate(false);
		fieldPhone.setWidth("90.0%");
		fieldPhone.setHeight("-1px");
		formLayout_2.addComponent(fieldPhone);
		
		// fieldCellPhone
		fieldCellPhone = new TextField();
		fieldCellPhone.setCaption("Celular");
		fieldCellPhone.setImmediate(false);
		fieldCellPhone.setWidth("90.0%");
		fieldCellPhone.setHeight("-1px");
		formLayout_2.addComponent(fieldCellPhone);
		
		// comboOwner
		comboOwner = new ComboBox();
		comboOwner.setCaption("Responsável");
		comboOwner.setImmediate(false);
		comboOwner.setWidth("90.0%");
		comboOwner.setHeight("-1px");
		formLayout_2.addComponent(comboOwner);
		
		return formLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("100.0%");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(true);
		
		// formAddress
		formAddress = buildFormAddress();
		horizontalLayout_2.addComponent(formAddress);
		
		// formLayout_1
		formLayout_1 = buildFormLayout_1();
		horizontalLayout_2.addComponent(formLayout_1);
		
		return horizontalLayout_2;
	}

	@AutoGenerated
	private FormLayout buildFormAddress() {
		// common part: create layout
		formAddress = new FormLayout();
		formAddress.setImmediate(false);
		formAddress.setWidth("100.0%");
		formAddress.setHeight("-1px");
		formAddress.setMargin(false);
		formAddress.setSpacing(true);
		
		// fieldZipCode
		fieldZipCode = new TextField();
		fieldZipCode.setCaption("CEP");
		fieldZipCode.setImmediate(false);
		fieldZipCode.setWidth("90.0%");
		fieldZipCode.setHeight("-1px");
		formAddress.addComponent(fieldZipCode);
		
		// fieldNumber
		fieldNumber = new TextField();
		fieldNumber.setCaption("Número");
		fieldNumber.setImmediate(false);
		fieldNumber.setWidth("90.0%");
		fieldNumber.setHeight("-1px");
		formAddress.addComponent(fieldNumber);
		
		// fieldState
		fieldState = new TextField();
		fieldState.setCaption("Estado");
		fieldState.setImmediate(false);
		fieldState.setWidth("90.0%");
		fieldState.setHeight("-1px");
		formAddress.addComponent(fieldState);
		
		// fieldCity
		fieldCity = new TextField();
		fieldCity.setCaption("Cidade");
		fieldCity.setImmediate(false);
		fieldCity.setWidth("90.0%");
		fieldCity.setHeight("-1px");
		formAddress.addComponent(fieldCity);
		
		return formAddress;
	}

	@AutoGenerated
	private FormLayout buildFormLayout_1() {
		// common part: create layout
		formLayout_1 = new FormLayout();
		formLayout_1.setImmediate(false);
		formLayout_1.setWidth("100.0%");
		formLayout_1.setHeight("-1px");
		formLayout_1.setMargin(false);
		formLayout_1.setSpacing(true);
		
		// fieldStreet
		fieldStreet = new TextField();
		fieldStreet.setCaption("Logradouro");
		fieldStreet.setImmediate(false);
		fieldStreet.setWidth("90.0%");
		fieldStreet.setHeight("-1px");
		formLayout_1.addComponent(fieldStreet);
		
		// fieldComplement
		fieldComplement = new TextField();
		fieldComplement.setCaption("Complemento");
		fieldComplement.setImmediate(false);
		fieldComplement.setWidth("90.0%");
		fieldComplement.setHeight("-1px");
		formLayout_1.addComponent(fieldComplement);
		
		// fieldSuburbName
		fieldSuburbName = new TextField();
		fieldSuburbName.setCaption("Bairro");
		fieldSuburbName.setImmediate(false);
		fieldSuburbName.setWidth("90.0%");
		fieldSuburbName.setHeight("-1px");
		formLayout_1.addComponent(fieldSuburbName);
		
		return formLayout_1;
	}

}
