package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpeedF implements PacketListener {

    /*
    Speed Type: F Created by Nerd1000 at 2024.04.19;
    Tries to detect bhooper.
     */

    private long avarageTimeL;

    private List<Long> avarageTime = new ArrayList<>(10);

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            if (avarageTime.size() != 10){
                avarageTime.add(MovementStruc.lastVelocityTaken);
            }else {
                avarageTimeL = getAverage();
                Player player = (Player) event.getPlayer();
                player.sendMessage("The Avarage Time: "+ avarageTimeL);
                HandleFull(10);
            }
        }
    }

    public void HandleFull(int capacity){
        if (avarageTime.size() == capacity){
            avarageTime.clear();
        }
    }

    public long getAverage(){
        int sum = 0;
        for (int i = 0; i < avarageTime.size(); i++){
            sum += avarageTime.get(i);
        }
        return (long) (double) sum / avarageTime.size();
    }
}
