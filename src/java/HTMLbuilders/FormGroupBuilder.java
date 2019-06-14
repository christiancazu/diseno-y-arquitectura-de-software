package HTMLbuilders;

import HTMLcomponents.FormGroup;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class FormGroupBuilder {
    
    protected FormGroup formGroup = new FormGroup();
    
    public FormGroupBuilder labelText(String labelText) {
        formGroup.labelText = labelText;
        return this;
    }
    
    public FormGroupBuilder inputType(String inputType) {
        formGroup.inputType = inputType;
        return this;
    }
    
    public FormGroupBuilder inputId(String inputId) {
        formGroup.inputId = inputId;
        return this;
    }
    
    public FormGroupBuilder inputName(String inputName) {
        formGroup.inputName = inputName;
        return this;
    }
    
    public FormGroupBuilder inputValue(String inputValue) {
        formGroup.inputValue = inputValue;
        return this;
    }
    
    public FormGroupBuilder inputPlaceholder(String inputPlaceholder) {
        formGroup.inputPlaceholder = inputPlaceholder;
        return this;
    }
    
    public FormGroupBuilder inputRequired(boolean inputRequired) {
        formGroup.inputRequired = inputRequired;
        return this;
    }
    
    public FormGroup build() {
        return formGroup;
    }
    
}
