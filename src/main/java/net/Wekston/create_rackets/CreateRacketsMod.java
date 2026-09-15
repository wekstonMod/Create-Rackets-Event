package net.Wekston.create_rackets;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;


@Mod(CreateRacketsMod.MODID)
public class CreateRacketsMod
{
    public static final String MODID = "create_rackets";
    public CreateRacketsMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
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
