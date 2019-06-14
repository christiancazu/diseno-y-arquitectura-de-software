package HTMLbuilders;

import HTMLcomponents.FormCheck;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class FormCheckBuilder {

    protected FormCheck formCheck = new FormCheck();
    
    public FormCheckBuilder inputId(String inputId) {
        formCheck.inputId = inputId;
        return this;
    }
    
    public FormCheckBuilder inputName(String inputName) {
        formCheck.inputName = inputName;
        return this;
    }
    
    public FormCheckBuilder inputValue(String inputValue) {
        formCheck.inputValue = inputValue;
        return this;
    }
    
    public FormCheckBuilder labelText(String labelText) {
        formCheck.labelText = labelText;
        return this;
    }
    
    public FormCheck build() {
        return formCheck;
    }
    
}
