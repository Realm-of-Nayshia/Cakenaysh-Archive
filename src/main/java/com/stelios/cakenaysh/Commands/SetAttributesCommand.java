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

public class SetAttributesCommand implements CommandExecutor {


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

                //if the attribute is a valid one set it to the correct value
                switch (args[1].toLowerCase()) {
                    case "rank":
                        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRank(args[2]);
                        //confirmation message
                        if (sender instanceof Player) {
                            sender.sendMessage(Component.text("Set " + player.getName() + "'s rank to " + args[2] + ".", TextColor.color(0, 255, 0)));
                        } else {
                            System.out.println("Set " + player.getName() + "'s rank to " + args[2] + ".");
                        }
                        break;

                    case "joindate":
                        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setJoinDate(args[2]);
                        //confirmation message
                        if (sender instanceof Player) {
                            sender.sendMessage(Component.text("Set " + player.getName() + "'s join date to " + args[2] + ".", TextColor.color(0, 255, 0)));
                        } else {
                            System.out.println("Set " + player.getName() + "'s join date to " + args[2] + ".");
                        }
                        break;

                    case "playtime":
                        try {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setPlayTime(Float.parseFloat(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s play time to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s playtime to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setXp(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s xp to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s xp to " + args[2] + ".");
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



                    //EXPERIMENTAL CODE HERE!!



                    case "stamina":
                        try{
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaDatabase(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s stamina to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s stamina to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthDatabase(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s health to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s health to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMaxHealth(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s max health to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s max health to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setSpeed(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s speed to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s speed to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMeleeProficiency(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s melee proficiency to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s melee proficiency to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRangedProficiency(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s ranged proficiency to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s ranged proficiency to " + args[2] + ".");
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
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setWilsonCoin(Integer.parseInt(args[2]));
                            //confirmation message
                            if (sender instanceof Player) {
                                sender.sendMessage(Component.text("Set " + player.getName() + "'s WilsonCoin to " + args[2] + ".", TextColor.color(0, 255, 0)));
                            } else {
                                System.out.println("Set " + player.getName() + "'s WilsonCoin to " + args[2] + ".");
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
                sender.sendMessage(Component.text("Incorrect usage! Use /setattribute <player> <attribute> <value>.", TextColor.color(255,0,0)));
            } else {
                System.out.println("Incorrect usage! Use /setattribute <player> <attribute> <value>.");
            }
        }
        return false;
    }
}
