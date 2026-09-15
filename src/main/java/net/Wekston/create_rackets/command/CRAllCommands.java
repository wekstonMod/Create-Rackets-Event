package net.Wekston.create_rackets.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.Wekston.create_rackets.Data.CRDataHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

import static net.Wekston.create_rackets.CreateRacketsMod.onStartRacketEvent;
import static net.Wekston.create_rackets.CreateRacketsMod.onStopRacketEvent;

public class CRAllCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("rackets")
                .requires(cs -> cs.hasPermission(2))
                        .then(CRCommandOption.register())
                .then(Commands.literal("start").executes(ctx -> changeStartEvent(ctx.getSource())))
                .then(Commands.literal("stop").executes(ctx -> changeStopEvent(ctx.getSource())))
        );
    }


    private static int changeStartEvent(CommandSourceStack source) {
        boolean isStart = CRDataHelper.isAttack();
        if (!isStart) {
            MinecraftServer server = source.getServer();
            CRDataHelper.setAttack(true);
            onStartRacketEvent(server);
            source.sendSuccess(() -> Component.translatable("create_rackets.command.start"), true);
        } else {
            source.sendFailure(Component.translatable("create_rackets.command.start.fail"));
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int changeStopEvent(CommandSourceStack source) {
        boolean isStart = CRDataHelper.isAttack();
        if (isStart) {
            MinecraftServer server = source.getServer();
            CRDataHelper.setAttack(false);
            onStopRacketEvent(server);
            source.sendSuccess(() -> Component.translatable("create_rackets.command.stop"), true);
        }
        else {
            source.sendFailure(Component.translatable("create_rackets.command.stop.fail"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
