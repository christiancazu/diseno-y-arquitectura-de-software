package HTMLbuilders;

import HTMLcomponents.FormActionButton;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class FormActionButtonBuilder {
    
    protected FormActionButton formActionButton = new FormActionButton();
    
    public FormActionButtonBuilder formAction(String formAction) {
    formActionButton.formAction = formAction;
        return this;
    }
    
    public FormActionButtonBuilder formMethod(String formMethod) {
        formActionButton.formMethod = formMethod;
        return this;
    }
    
    public FormActionButtonBuilder inputHiddenValue(String inputHiddenValue) {
        formActionButton.inputHiddenValue = inputHiddenValue;
        return this;
    }
    
    public FormActionButtonBuilder btnType(String btnType) {
        formActionButton.btnType = btnType;
        return this;
    }
    
    public FormActionButtonBuilder btnName(String btnName) {
        formActionButton.btnName = btnName;
        return this;
    }
    
    public FormActionButton build() {
        return formActionButton;
    }
            
}
