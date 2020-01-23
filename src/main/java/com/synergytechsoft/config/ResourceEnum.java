
package com.synergytechsoft.config;

/**
 *
 * @author Suresh Pun
 */
public enum ResourceEnum {

    
    USER(ActionEnum.VIEW)
    ;

    private final ActionEnum[] ae;

    private ResourceEnum(ActionEnum... ae) {
        this.ae = ae;
    }

    public ActionEnum[] getActions() {
        return this.ae;
    }
}
