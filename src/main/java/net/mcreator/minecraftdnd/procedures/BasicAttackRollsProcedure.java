package net.mcreator.minecraftdnd.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

import net.mcreator.minecraftdnd.network.MinecraftDndModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BasicAttackRollsProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity entity, Entity sourceentity) {
		execute(null, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof Player) {
			entity.getPersistentData().putDouble("userdefenseroll",
					(Mth.nextInt(RandomSource.create(), 1, 100) + (entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DefenseModifier));
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(entity.getPersistentData().getDouble("userdefenseroll")))), true);
		} else if (!(entity instanceof Player)) {
			entity.getPersistentData().putDouble("userdefenseroll", (Mth.nextInt(RandomSource.create(), 1, 100) + entity.getPersistentData().getDouble("defensemodifier")));
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(entity.getPersistentData().getDouble("userdefenseroll")))), true);
		}
		if (sourceentity instanceof Player) {
			sourceentity.getPersistentData().putDouble("userattackroll",
					(Mth.nextInt(RandomSource.create(), 1, 100) + (sourceentity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).AttackModifier));
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(sourceentity.getPersistentData().getDouble("userattackroll")))), true);
		} else if (!(sourceentity instanceof Player)) {
			sourceentity.getPersistentData().putDouble("userattackroll", (Mth.nextInt(RandomSource.create(), 1, 100) + sourceentity.getPersistentData().getDouble("attackmodifier")));
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(sourceentity.getPersistentData().getDouble("userattackroll")))), true);
		}
		if (sourceentity.getPersistentData().getDouble("userattackroll") < entity.getPersistentData().getDouble("userdefenseroll")) {
			if (event != null && event.isCancelable()) {
				event.setCanceled(true);
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Negated Damage"), true);
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Enemy Defense Was Higher, Negated Damage"), true);
		}
	}
}
