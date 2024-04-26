package me.dragon.lizardacflyimpl.impl;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.lizardacflyimpl.LizardACFLyImpl;
import me.dragon.lizardacflyimpl.Managers.MovementManager;
import me.dragon.lizardacflyimpl.Managers.TickManager;
import org.bukkit.entity.Player;

public class FlyC implements PacketListener {

        private  double lastDeltaY,buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){
            double deltaY = MovementManager.deltaY;

            double prediction = Math.abs((lastDeltaY - 0.08) * 0.98F);
            final double difference = Math.abs(deltaY - prediction);
            Player player = (Player) event.getPlayer();

            boolean isInvalid = difference > 0.00001D && TickManager.airTicks >= 11 && !(MovementManager.y % 0.5 == 0 && player.isOnGround() && lastDeltaY < 0);
           // player.sendMessage("prediction= "+ prediction + "diff= "+ difference);
            if (isInvalid){
               buffer++;

               if (buffer > 1){
                   LizardACFLyImpl.flagEvent(player,'C',"difference= "+ difference + " airTicks= "+ TickManager.airTicks);
                   buffer -= 0.10;
               }
            }
            buffer -= 0.67;
            lastDeltaY = deltaY;
        }
    }
}
