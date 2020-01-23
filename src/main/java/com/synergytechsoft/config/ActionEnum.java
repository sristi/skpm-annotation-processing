
package com.synergytechsoft.config;

/**
 *
 * @author Suresh Pun
 */
public enum ActionEnum {

   
    VIEW("View");
    
    private String types;

    /**
     *
     * @return
     */
    public String getTypes() {
        return types;
    }

    /**
     *
     * @param types
     */
    public void setTypes(String types) {
        this.types = types;
    }

    ActionEnum(String types) {
        this.types = types;
    }
    
}
