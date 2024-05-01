package net.mcreator.minecraftdnd.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.minecraftdnd.network.MinecraftDndModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BasicAttackRollsProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (((entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DefenseType).equals("Block")) {
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
		} else if (((entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DefenseType).equals("Dodge")) {
			if (entity instanceof Player) {
				entity.getPersistentData().putDouble("userdefenseroll",
						(Mth.nextInt(RandomSource.create(), 1, 100) + (entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DodgeModifier));
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(entity.getPersistentData().getDouble("userdefenseroll")))), true);
			} else if (!(entity instanceof Player)) {
				entity.getPersistentData().putDouble("userdefenseroll", (Mth.nextInt(RandomSource.create(), 1, 100) + entity.getPersistentData().getDouble("dodgemodifier")));
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("You Rolled" + new java.text.DecimalFormat("#").format(entity.getPersistentData().getDouble("userdefenseroll")))), true);
			}
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
		if (((entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DefenseType).equals("Block")) {
			if (sourceentity.getPersistentData().getDouble("userattackroll") < entity.getPersistentData().getDouble("userdefenseroll")) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Negated Damage"), true);
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Enemy Defense Was Higher, Negated Damage"), true);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.hit")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.hit")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		} else if (((entity.getCapability(MinecraftDndModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinecraftDndModVariables.PlayerVariables())).DefenseType).equals("Dodge")) {
			if (sourceentity.getPersistentData().getDouble("userattackroll") < entity.getPersistentData().getDouble("userdefenseroll")) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Dodged"), true);
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Enemy Speed Was Higher, Dodged Attack"), true);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, 2, 2);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, 2, 2, false);
					}
				}
			}
		}
	}
}
