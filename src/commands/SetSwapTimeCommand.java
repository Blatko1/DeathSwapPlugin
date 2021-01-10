package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import timer.SwaperTimer;

public class SetSwapTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equals("deathswapset")){
                if(args.length == 1){
                    SwaperTimer.setTime(Integer.parseInt(args[0]));
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Usage: /deathswapset <seconds>.");
                return true;
            }
            p.sendMessage(ChatColor.RED + "Usage: /deathswapset <seconds>.");
            return true;
        }
        sender.sendMessage(ChatColor.GOLD + "Only players can use this command.");
        return true;
    }
}
