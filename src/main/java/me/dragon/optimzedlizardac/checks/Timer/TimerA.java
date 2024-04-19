package me.dragon.optimzedlizardac.checks.Timer;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;

import java.util.ArrayList;

public class TimerA implements PacketListener {
    //Made by GladurBad (Medusa) dev; I just implemented it;

    private  long lastFlyPacket;
    private ArrayList<Long> samples = new ArrayList<>(10);
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            long now = System.currentTimeMillis();

            long delta = now - lastFlyPacket;

            if (delta > 1){
                samples.add(delta);
            }
            if (samples.size() == 10){ //The list is full;
                long avg = getAvg();
                long time = 50 / avg;


            }


            lastFlyPacket = System.currentTimeMillis();
        }
    }
    public long getAvg(){
        return (long) samples.stream()
                .mapToLong(Long::valueOf) // Map each element to long value
                .average()                // Calculate the average
                .orElse(0.0);             // Return 0 if the list is empty
    }





}
