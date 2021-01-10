package timer;

import main.DeathSwapMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SwaperTimer {

    private static int ID = -1;
    private static int DEATH_SWAP_TIME = 300;

    public static void startTimer(){
        if(ID == -1){
            ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(DeathSwapMain.getPlugin(), new Runnable() {
                private int i = 0;
                @Override
                public void run() {
                    if(Bukkit.getOnlinePlayers().size() < 2){
                        stopTimer();
                        Bukkit.broadcastMessage(ChatColor.RED + "Game canceled! Not enough players!");
                    }
                    if(i > DEATH_SWAP_TIME-10){
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Time left: " + (DEATH_SWAP_TIME - i));
                    }
                    if(i == DEATH_SWAP_TIME){
                        swapAllPlayers();
                        i = 0;
                    }
                    else{
                        i++;
                    }
                }
            },20, 20);
            Bukkit.broadcastMessage(ChatColor.GREEN + "DEATH SWAP has started!");
            return;
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Timer is currently running!");
    }

    public static void stopTimer(){
        if(ID == -1){
            Bukkit.broadcastMessage(ChatColor.RED + "Timer is not currently running!");
            return;
        }
        Bukkit.getScheduler().cancelTasks(DeathSwapMain.getPlugin());
        ID = -1;
        Bukkit.broadcastMessage(ChatColor.GREEN + "DEATH SWAP been stopped!");
    }

    public static void setTime(int i){
        if(ID != -1){
            Bukkit.broadcastMessage(ChatColor.RED + "Stop the timer to set time!");
            return;
        }
        if(i < 10){
            Bukkit.broadcastMessage(ChatColor.RED + "Time can't be bellow 10 seconds!");
            return;
        }
        DEATH_SWAP_TIME = i;
        Bukkit.broadcastMessage(ChatColor.GREEN + "Time set!");
    }

    private static void swapAllPlayers(){

        HashMap<Integer, Player> map = new HashMap<>();
        int i = 0;
        //Puts all players in a list with an unique index.
        for(Player p: Bukkit.getOnlinePlayers()){
            map.put(i, p);
            i++;
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.shuffle(list);

        Location loc = map.get(list.get(0)).getLocation();

        for(int n = 1; n<=list.size(); n++){
            if(n == list.size()){
                Player p1 = map.get(list.get(n-1));
                swapPlayer(p1, loc);
                continue;
            }
            Player p1 = map.get(list.get(n-1));
            Player p2 = map.get(list.get(n));
            swapPlayer(p1, p2.getLocation());
        }

    }

    private static void swapPlayer(Player p, Location loc){
        p.teleport(loc);
    }

}
