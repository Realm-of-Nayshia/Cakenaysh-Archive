package com.stelios.cakenaysh.Listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void onPing(org.bukkit.event.server.ServerListPingEvent e){

        //e.setMaxPlayers(5);
        //e.motd(Component.text("This is a test server!",
        //                TextColor.color(0,150,255))
        //        .decoration(TextDecoration.BOLD, true));

        //try {
        //    e.setServerIcon(Bukkit.loadServerIcon(new File("icon.png")));
        //} catch (Exception ex) {
        //    ex.printStackTrace();
        //}
    }

}
