package net.Wekston.create_rackets.Data;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class CRDataHelper {
    private static CRData savedData = null;
    private static final String DATA_NAME = "cr_data";

    private static CRData getSavedData() {
        if (savedData == null) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server != null) {
                ServerLevel overworld = server.getLevel(Level.OVERWORLD);
                if (overworld != null) {
                    savedData = overworld.getDataStorage()
                            .computeIfAbsent(
                                    new CRData.Factory<>(
                                            CRData::create,
                                            CRData::load
                                    ),
                                    DATA_NAME
                            );
                }
            }
        }
        return savedData;
    }

    public static int getRadius() {
        CRData data = getSavedData();
        return data.getRadius();
    }

    public static boolean isAttack() {
        CRData data = getSavedData();
        return data.isAttack();
    }

    public static boolean isCircle() {
        CRData data = getSavedData();
        return data.isCircle();
    }

    public static BlockPos getPos() {
        CRData data = getSavedData();
        return data.getPos();
    }

    public static int getCooldown() {
        CRData data = getSavedData();
        return data.getCooldown();
    }

    public static int getEventTick() {
        CRData data = getSavedData();
        return data.getEventTick();
    }

    public static int getRandomEvent() {
        CRData data = getSavedData();
        return data.getRandomEvent();
    }

    public static int getSecondEvent() {
        CRData data = getSavedData();
        return data.getSecondEvents();
    }

    public static ItemStack getFuze() {
        CRData data = getSavedData();
        return data.getFuze();
    }


    public static void setRadius(int radius) {
        CRData data = getSavedData();
        if (data != null) {
            data.setRadius(radius);
        }
    }

    public static void setRandomEvent(int tick) {
        CRData data = getSavedData();
        if (data != null) {
            data.setRandomEvent(tick);
        }
    }

    public static void setEventTick(int tick) {
        CRData data = getSavedData();
        if (data != null) {
            data.setEventTick(tick);
        }
    }

    public static void setSecondEvent(int second) {
        CRData data = getSavedData();
        if (data != null) {
            data.setSecondEvents(second);
        }
    }

    public static void setAttack(boolean attack) {
        CRData data = getSavedData();
        if (data != null) {
            data.setAttack(attack);
        }
    }

    public static void setCircle(boolean circle) {
        CRData data = getSavedData();
        if (data != null) {
            data.setCircle(circle);
        }
    }

    public static void setPos(BlockPos pos) {
        CRData data = getSavedData();
        if (data != null) {
            data.setPos(pos);
        }
    }

    public static void setCooldown(int cooldown) {
        CRData data = getSavedData();
        if (data != null) {
            data.setCooldown(cooldown);
        }
    }

    public static void setFuze(ItemStack stack) {
        CRData data = getSavedData();
        if (data != null) {
            data.setFuze(stack);
        }
    }

}