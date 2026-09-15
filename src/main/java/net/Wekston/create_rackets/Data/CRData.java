package net.Wekston.create_rackets.Data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class CRData extends SavedData {

    public static CRData create() {
        return new CRData();
    }

    private boolean attack = false;
    private boolean events = false;
    private ItemStack fuze = ItemStack.EMPTY;
    private int cooldown = 20;
    private int eventTick = 0;
    private int secondEvents = 0;
    private int randomEvent = 50;
    private BlockPos pos = new BlockPos(0, 70, 0);
    private int radius = 100;
    private boolean circle = true;
    public CRData() {
        super();
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putInt("radius", radius);
        tag.put("pos", NbtUtils.writeBlockPos(pos));
        tag.putBoolean("attack", attack);
        tag.putBoolean("circle", circle);
        tag.putBoolean("events", events);
        tag.putInt("cooldown", cooldown);
        tag.putInt("randomEvent", randomEvent);
        tag.putInt("secondEvents", secondEvents);
        tag.putInt("eventTick", eventTick);
        tag.put("fuze", fuze.save(new CompoundTag()));
        return tag;
    }

    public static CRData load(@NotNull CompoundTag tag) {
        CRData data = CRData.create();
        data.radius = tag.getInt("radius");
        data.randomEvent = tag.getInt("randomEvent");
        data.eventTick = tag.getInt("eventTick");
        data.attack = tag.getBoolean("attack");
        data.circle = tag.getBoolean("circle");
        data.events = tag.getBoolean("events");
        data.secondEvents = tag.getInt("secondEvents");
        data.pos = NbtUtils.readBlockPos((CompoundTag) tag.get("pos"));
        data.cooldown = tag.getInt("cooldown");
        data.fuze = ItemStack.of(tag.getCompound("fuze"));
        return data;
    }
    public int getRadius() {
        return radius;
    }
    public boolean isAttack() {
        return attack;
    }
    public boolean isCircle() {
        return circle;
    }
    public boolean isEvents() {
        return events;
    }
    public BlockPos getPos() {
        return pos;
    }
    public int getCooldown() {
        return cooldown;
    }



    public void setRadius(int radius) {
        this.radius = radius;
        setDirty();
    }
    public void setAttack(boolean attack) {
        this.attack = attack;
        setDirty();
    }
    public void setEvents(boolean events) {
        this.events = events;
        setDirty();
    }
    public void setCircle(boolean circle) {
        this.circle = circle;
        setDirty();
    }
    public void setPos(BlockPos pos) {
        this.pos = pos;
        setDirty();
    }
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
        setDirty();
    }

    public ItemStack getFuze() {
        return fuze;
    }

    public void setFuze(ItemStack stack) {
        this.fuze = stack;
        setDirty();
    }

    public int getEventTick() {
        return eventTick;
    }

    public void setEventTick(int eventTick) {
        this.eventTick = eventTick;
    }

    public int getRandomEvent() {
        return randomEvent;
    }

    public void setRandomEvent(int randomEvent) {
        this.randomEvent = randomEvent;
    }

    public int getSecondEvents() {
        return secondEvents;
    }

    public void setSecondEvents(int secondEvents) {
        this.secondEvents = secondEvents;
    }
}
