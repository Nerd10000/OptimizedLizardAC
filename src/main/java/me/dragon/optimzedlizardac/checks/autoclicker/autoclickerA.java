package me.dragon.optimzedlizardac.checks.autoclicker;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.PlayerStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class autoclickerA implements PacketListener {
    private int cps,lastCps;
    private int MAX_CPS = 20;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            cps++;

            if (cps > MAX_CPS){
                DataStruc.alert("cps= " + cps, (Player) event.getPlayer(), CheckType.AUTOCLICKER, GradeEnum.A);
                event.setCancelled(true);
            }

            this.lastCps = cps;
        }else if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            cps = 0;
        }
    }
}
