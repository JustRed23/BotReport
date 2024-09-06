package dev.JustRed23.botreport;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.connection.PreLoginEvent;

public class LoginListener {

    //TODO: wait for paper devs to answer issue, only pre login fires during read timeout

    @Subscribe
    public void onPreLogin(PreLoginEvent event) {
        System.out.println("pre login");
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
        System.out.println("login");
    }

    @Subscribe
    public void onPostLogin(PostLoginEvent event) {
        System.out.println("post login");
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        System.out.println("disconnect");
    }
}
