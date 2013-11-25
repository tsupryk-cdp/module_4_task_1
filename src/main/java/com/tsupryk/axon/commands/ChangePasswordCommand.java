package com.tsupryk.axon.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * The Class ChangePasswordCommand.
 * <p/>
 * Date: 21.11.13
 *
 * @author Vitaliy_Tsupryk
 */
public class ChangePasswordCommand implements Command {

    @TargetAggregateIdentifier
    private String userId;

    private String newPassword;

    public ChangePasswordCommand() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
