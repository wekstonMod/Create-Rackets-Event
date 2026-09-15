package net.Wekston.create_rackets.Event;

import net.Wekston.create_rackets.CreateRacketsMod;
import net.Wekston.create_rackets.Data.CRDataHelper;
import net.Wekston.create_rackets.command.CRAllCommands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rbasamoyai.createbigcannons.index.CBCEntityTypes;
import rbasamoyai.createbigcannons.munitions.big_cannon.he_shell.HEShellProjectile;

import java.util.Random;

import static net.Wekston.create_rackets.CreateRacketsMod.onStartRacketEvent;


@Mod.EventBusSubscriber(modid = CreateRacketsMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvent {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CRAllCommands.register(event.getDispatcher());
    }
    private static final Random random = new Random();
    private static int tick = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        MinecraftServer server = player.getServer();
        if (server == null) return;

        Level level = server.getLevel(Level.OVERWORLD);
        if (level == null) return;

        int randomTickSpeed = level.getGameRules().getInt(GameRules.RULE_RANDOMTICKING);
        if (CRDataHelper.isAttack()) {
            if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) {
                return;
            }
            tick++;
            if (tick < CRDataHelper.getCooldown() * randomTickSpeed) return;
            BlockPos pos = CRDataHelper.getPos();
            double radius = CRDataHelper.getRadius();
            double x = pos.getX() + random.nextDouble(-radius, radius);
            double z = pos.getZ() + random.nextDouble(-radius, radius);
            if (CRDataHelper.isCircle()) {
                if (Mth.ceil(x - pos.getX() + Mth.ceil(z - pos.getZ())) > radius * radius) {
                    return;
                }
            }
            Vec3 blockPos = new Vec3(x, 320 + random.nextDouble(10, 20), z);
            HEShellProjectile he_shell = CBCEntityTypes.HE_SHELL.create(level);
            assert he_shell != null;
            he_shell.moveTo(blockPos);
            he_shell.shootFromRotation(he_shell, random.nextInt(-10, 10) + 90, random.nextInt(0, 360), 0.5F, 1.5F, 0.5f);
            ItemStack stack = CRDataHelper.getFuze().copy();
            he_shell.setFuze(stack);
            level.addFreshEntity(he_shell);
            tick = 0;
        }
        else {
            if (CRDataHelper.getRandomEvent() == 0) {
                return;
            }
            tick++;
            if (tick >= 20 * randomTickSpeed)  {
                CRDataHelper.setEventTick(CRDataHelper.getEventTick() + 1);
                tick = 0;
            }
            if (CRDataHelper.getEventTick() >= CRDataHelper.getSecondEvent()) {
                int randoms = random.nextInt(0, 100);
                if (randoms < CRDataHelper.getRandomEvent()) {
                    CRDataHelper.setAttack(true);
                    onStartRacketEvent(server);
                    CRDataHelper.setEventTick(0);
                }
                else {
                    CRDataHelper.setEventTick(0);
                }
            }
        }
    }
}