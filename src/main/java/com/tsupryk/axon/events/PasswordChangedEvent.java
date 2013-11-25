package com.tsupryk.axon.events;

/**
 * The Class PasswordChangedEvent.
 * <p/>
 * Date: 21.11.13
 *
 * @author Vitaliy_Tsupryk
 */
public class PasswordChangedEvent {

    private String newPassword;
    private Object userId;


    public PasswordChangedEvent(String userId, String newPassword) {
        this.newPassword = newPassword;
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Object getUserId() {
        return userId;
    }
}
