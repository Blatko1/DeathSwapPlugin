package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import timer.SwaperTimer;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equals("deathswapstart")){
                if(args.length == 0){
                    SwaperTimer.startTimer();
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Usage: /deathswapstart.");
                return true;
            }
            p.sendMessage(ChatColor.RED + "Usage: /deathswapstart.");
            return true;
        }
        sender.sendMessage(ChatColor.GOLD + "Only players can use this command.");
        return true;
    }
}
