/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class FormActionButton {
    
    private String action;
    private String method;
    private String value;
    private String btnType;
    private String btnName;

    public FormActionButton(String action, String method, String value, String btnType, String btnName) {
        this.action = action;
        this.method = method;
        this.value = value;
        this.btnType = btnType;
        this.btnName = btnName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getId() {
        return value;
    }

    public void setId(String value) {
        this.value = value;
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }    
    
}

