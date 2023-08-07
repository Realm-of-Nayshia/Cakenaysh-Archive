package com.stelios.cakenaysh.Listeners.Stats;

import com.stelios.cakenaysh.Events.SpeedChangedEvent;
import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Managers.StatsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SpeedChangedListener implements Listener {

    private final Main main;
    private final StatsManager statsManager;

    public SpeedChangedListener(Main main, StatsManager statsManager){
        this.main = main;
        this.statsManager = statsManager;
    }

    @EventHandler
    public void onSpeedChanged(SpeedChangedEvent e){
        e.getPlayer().setWalkSpeed(0.2F * ((e.getSpeed() + 100) / 100));
    }

}
