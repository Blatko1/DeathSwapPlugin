package main;

import commands.SetSwapTimeCommand;
import commands.StartCommand;
import commands.StopCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwapMain extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("deathswapstart").setExecutor(new StartCommand());
        this.getCommand("deathswapstop").setExecutor(new StopCommand());
        this.getCommand("deathswapset").setExecutor(new SetSwapTimeCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
