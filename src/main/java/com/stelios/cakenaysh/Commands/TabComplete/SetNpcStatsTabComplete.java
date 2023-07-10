package com.stelios.cakenaysh.Commands.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetNpcStatsTabComplete implements org.bukkit.command.TabCompleter{

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //first argument: stat name
        if (args.length == 1) {

            return org.bukkit.util.StringUtil.copyPartialMatches(args[0], java.util.Arrays.asList("critdamage","critchance",
                    "strength", "infernaldefense","infernaldamage","undeaddefense","undeaddamage","aquaticdefense",
                    "aquaticdamage","aerialdefense","aerialdamage","meleedefense", "meleedamage", "rangeddefense",
                    "rangeddamage","magicdefense","magicdamage"), new ArrayList<>());
        }

        //return the list
        return new ArrayList<>();

    }

}
