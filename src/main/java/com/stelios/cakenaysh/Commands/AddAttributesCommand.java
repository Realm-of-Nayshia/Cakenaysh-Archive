package com.stelios.cakenaysh.Commands;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddAttributesCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //if there are the correct # of args
        if (args.length == 3){

            //if the target player is online
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getServer().getPlayer(args[0]))) {

                //get the targeted player
                Player player = Bukkit.getServer().getPlayer(args[0]);
                assert player != null;

                //get the main class
                Main main = Main.getPlugin(Main.class);

                //if the attribute is a valid one add the correct value to it
                switch (args[1].toLowerCase()) {
                    case "playtime":
                        try {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addPlayTime(Float.parseFloat(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s play time has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s play time has increased by " + args[2] + ".");
                            }
                        } catch (NumberFormatException e) {
                            //error: invalid playtime
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid playtime.", TextColor.color(255, 0, 0)));
                            } else {
                                System.out.println("Invalid playtime.");
                            }
                        }
                        break;

                    case "xp":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addXp(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s xp has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s xp has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid xp
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid xp.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid xp.");
                            }
                        }
                        break;

                    //EXPERIMENTAL STARTS


                    case "stamina":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addStaminaDatabase(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s stamina has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s stamina has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid stamina
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid stamina.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid stamina.");
                            }
                        }
                        break;

                    case "health":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthDatabase(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s health has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s health has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid health
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid health.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid health.");
                            }
                        }
                        break;





                    //EXPERIMENTAL ENDS


                    case "maxhealth":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addMaxHealth(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s max health has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s max health has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid max health
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid max health.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid max health.");
                            }
                        }
                        break;

                    case "speed":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addSpeed(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s speed has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s speed has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid speed
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid speed.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid speed.");
                            }
                        }
                        break;

                    case "meleeproficiency":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addMeleeProficiency(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s melee proficiency has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s melee proficiency has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid melee proficiency
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid melee proficiency.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid melee proficiency.");
                            }
                        }
                        break;

                    case "rangedproficiency":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addRangedProficiency(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s ranged proficiency has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s ranged proficiency has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid ranged proficiency
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid ranged proficiency.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid ranged proficiency.");
                            }
                        }
                        break;

                    case "wilsoncoin":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addWilsonCoin(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text(player.getName() + "'s WilsonCoin has increased by " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println(player.getName() + "'s WilsonCoin has increased by " + args[2] + ".");
                            }
                        }catch (NumberFormatException e){
                            //error: invalid wilson coin
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Invalid WilsonCoin.", TextColor.color(255,0,0)));
                            } else {
                                System.out.println("Invalid WilsonCoin.");
                            }
                        }
                        break;

                    default:
                        //error: invalid attribute
                        if (sender instanceof Player) {
                            sender.sendMessage(Component.text("Invalid attribute.", TextColor.color(255,0,0)));
                        } else {
                            System.out.println("Invalid attribute.");
                        }
                }

            }else{
                //error: player not online
                if (sender instanceof Player) {
                    sender.sendMessage(Component.text("That player is not online.", TextColor.color(255,0,0)));
                } else {
                    System.out.println("That player is not online.");
                }
            }

        }else{
            //error: incorrect usage
            if (sender instanceof Player) {
                sender.sendMessage(Component.text("Incorrect usage! Use /addattribute <player> <attribute> <amount>.", TextColor.color(255,0,0)));
            } else {
                System.out.println("Incorrect usage! Use /addattribute <player> <attribute> <amount>.");
            }
        }
        return false;

    }

}
