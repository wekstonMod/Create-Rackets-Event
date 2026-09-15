package net.Wekston.create_rackets;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import java.util.List;

@Mod(CreateRacketsMod.MODID)
public class CreateRacketsMod {
    public static final String MODID = "create_rackets";
    public CreateRacketsMod(IEventBus modEventBus, ModContainer modContainer) {
    }


    public static void onStartRacketEvent(MinecraftServer server) {
        List<ServerPlayer> playerList = server.getPlayerList().getPlayers();
        for (ServerPlayer player : playerList) {
            player.sendSystemMessage(Component.translatable("create_rackets.info.start_rackets"));
        }
    }
    public static void onStopRacketEvent(MinecraftServer server) {
        List<ServerPlayer> playerList = server.getPlayerList().getPlayers();
        for (ServerPlayer player : playerList) {
            player.sendSystemMessage(Component.translatable("create_rackets.info.stop_rackets"));
        }
    }
}
