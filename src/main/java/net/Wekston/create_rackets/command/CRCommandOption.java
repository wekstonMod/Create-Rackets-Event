package net.Wekston.create_rackets.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.Wekston.create_rackets.Data.CRDataHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CRCommandOption {
    static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("option")
                .then(Commands.literal("radius")
                        .then(Commands.literal("get").executes(ctx -> getRadius(ctx.getSource())))
                        .then(Commands.literal("set").then(Commands.argument("radius", IntegerArgumentType.integer(1)).executes(ctx -> setRadius(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "radius")
                                                )
                                        )
                                )
                        )
                )
                .then(Commands.literal("center")
                        .then(Commands.literal("get").executes(ctx -> getCenter(ctx.getSource())))
                        .then(Commands.literal("set").then(Commands.argument("pos", BlockPosArgument.blockPos()).executes(ctx -> setCenter(ctx.getSource(), BlockPosArgument.getBlockPos(ctx, "pos")
                                                )
                                        )
                                )
                        )
                )
                .then(Commands.literal("events")
                        .then(Commands.literal("chance")
                                .then(Commands.literal("get").executes(ctx -> getRandomChance(ctx.getSource())))
                                .then(Commands.literal("set").then(Commands.argument("random", IntegerArgumentType.integer(0, 100)).executes(ctx -> setRandomChance(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "random")
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("seconds")
                                .then(Commands.literal("get").executes(ctx -> getSeconds(ctx.getSource())))
                                .then(Commands.literal("set").then(Commands.argument("seconds", IntegerArgumentType.integer(0)).executes(ctx -> setSeconds(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "seconds")
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .then(Commands.literal("center")
                        .then(Commands.literal("get").executes(ctx -> getCenter(ctx.getSource())))
                        .then(Commands.literal("set").then(Commands.argument("pos", BlockPosArgument.blockPos()).executes(ctx -> setCenter(ctx.getSource(), BlockPosArgument.getBlockPos(ctx, "pos")
                                                )
                                        )
                                )
                        )
                )
                .then(Commands.literal("circle")
                        .then(Commands.literal("get").executes(ctx -> getCircle(ctx.getSource())))
                        .then(Commands.literal("set")
                                .then(Commands.literal("on").executes(ctx -> setCircle(ctx.getSource(), true)
                                        )
                                )
                                .then(Commands.literal("off").executes(ctx -> setCircle(ctx.getSource(), false)
                                        )
                                )
                        )
                )
                .then(Commands.literal("fuze")
                        .then(Commands.literal("get").executes(ctx -> getFuze(ctx.getSource())))
                        .then(Commands.literal("set").executes(ctx -> setFuze(ctx.getSource())
                                )
                        )
                )
                .then(Commands.literal("cooldown")
                        .then(Commands.literal("get").executes(ctx -> getCooldown(ctx.getSource())))
                        .then(Commands.literal("set").then(Commands.argument("cooldown", IntegerArgumentType.integer(1)).executes(ctx -> setCooldown(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "cooldown")
                                                )
                                        )
                                )
                        )
                );
    }
    private static int getRadius(CommandSourceStack source) {
        int radius = CRDataHelper.getRadius();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.radius.get", radius), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setRadius(CommandSourceStack source, int radius) {
        CRDataHelper.setRadius(radius);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.radius.set", radius), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int getCenter(CommandSourceStack source) {
        BlockPos pos = CRDataHelper.getPos();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.center.get", pos.getX(), pos.getY(), pos.getZ()), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setCenter(CommandSourceStack source, BlockPos pos) {
        CRDataHelper.setPos(pos);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.center.set", pos.getX(), pos.getY(), pos.getZ()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int getCooldown(CommandSourceStack source) {
        int cooldown = CRDataHelper.getCooldown();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.cooldown.get", cooldown), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setCooldown(CommandSourceStack source, int cooldown) {
        CRDataHelper.setCooldown(cooldown);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.cooldown.set", cooldown), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int getCircle(CommandSourceStack source) {
        boolean circle = CRDataHelper.isCircle();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.circle.get", circle), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setCircle(CommandSourceStack source, boolean circle) {
        CRDataHelper.setCircle(circle);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.circle.set", circle), true);
        return Command.SINGLE_SUCCESS;
    }


    private static int getFuze(CommandSourceStack source) {
        ItemStack fuze = CRDataHelper.getFuze();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.fuze.get", fuze.getDisplayName()), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setFuze(CommandSourceStack source) {
        if (source.getEntity() instanceof Player player) {
            ItemStack stack = player.getMainHandItem().copy();
            CRDataHelper.setFuze(stack);
            source.sendSuccess(() -> Component.translatable("create_rackets.command.fuze.set", stack.getDisplayName()), true);
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int getRandomChance(CommandSourceStack source) {
        int random = CRDataHelper.getRandomEvent();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.random_chance.get", random), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setRandomChance(CommandSourceStack source, int random) {
        CRDataHelper.setRandomEvent(random);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.random_chance.set", random), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int getSeconds(CommandSourceStack source) {
        int seconds = CRDataHelper.getSecondEvent();
        int random = CRDataHelper.getRandomEvent();
        source.sendSuccess(() -> Component.translatable("create_rackets.command.seconds.get", random, seconds), true);
        return Command.SINGLE_SUCCESS;
    }
    private static int setSeconds(CommandSourceStack source, int seconds) {
        CRDataHelper.setSecondEvent(seconds);
        source.sendSuccess(() -> Component.translatable("create_rackets.command.seconds.set", seconds), true);
        return Command.SINGLE_SUCCESS;
    }
}
