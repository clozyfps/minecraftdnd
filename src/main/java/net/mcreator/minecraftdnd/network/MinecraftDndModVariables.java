package net.mcreator.minecraftdnd.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.minecraftdnd.MinecraftDndMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinecraftDndModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		MinecraftDndMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.Attribute4 = original.Attribute4;
			clone.Attribute3 = original.Attribute3;
			clone.Attribute2 = original.Attribute2;
			clone.Attribute1 = original.Attribute1;
			clone.Attribute5 = original.Attribute5;
			clone.Sub1Atr1 = original.Sub1Atr1;
			clone.Sub2Atr1 = original.Sub2Atr1;
			clone.Sub3Atr1 = original.Sub3Atr1;
			clone.Sub4Atr1 = original.Sub4Atr1;
			clone.Sub5Atr1 = original.Sub5Atr1;
			clone.Sub1Atr2 = original.Sub1Atr2;
			clone.Sub2Atr2 = original.Sub2Atr2;
			clone.Sub3Atr2 = original.Sub3Atr2;
			clone.Sub4Atr2 = original.Sub4Atr2;
			clone.Sub5Atr2 = original.Sub5Atr2;
			clone.Sub1Atr3 = original.Sub1Atr3;
			clone.Sub2Atr3 = original.Sub2Atr3;
			clone.Sub3Atr3 = original.Sub3Atr3;
			clone.Sub4Atr3 = original.Sub4Atr3;
			clone.Sub5Atr3 = original.Sub5Atr3;
			clone.Sub1Atr4 = original.Sub1Atr4;
			clone.Sub2Atr4 = original.Sub2Atr4;
			clone.Sub3Atr4 = original.Sub3Atr4;
			clone.Sub4Atr4 = original.Sub4Atr4;
			clone.Sub5Atr4 = original.Sub5Atr4;
			clone.Sub1Atr5 = original.Sub1Atr5;
			clone.Sub2Atr5 = original.Sub2Atr5;
			clone.Sub3Atr5 = original.Sub3Atr5;
			clone.Sub4Atr5 = original.Sub4Atr5;
			clone.Sub5Atr5 = original.Sub5Atr5;
			clone.AttackModifier = original.AttackModifier;
			clone.DefenseModifier = original.DefenseModifier;
			clone.DefenseType = original.DefenseType;
			clone.DodgeModifier = original.DodgeModifier;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("minecraft_dnd", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public String Attribute4 = "";
		public String Attribute3 = "";
		public String Attribute2 = "";
		public String Attribute1 = "";
		public String Attribute5 = "";
		public String Sub1Atr1 = "";
		public String Sub2Atr1 = "\"\"";
		public String Sub3Atr1 = "\"\"";
		public String Sub4Atr1 = "\"\"";
		public String Sub5Atr1 = "\"\"";
		public String Sub1Atr2 = "\"\"";
		public String Sub2Atr2 = "\"\"";
		public String Sub3Atr2 = "\"\"";
		public String Sub4Atr2 = "\"\"";
		public String Sub5Atr2 = "\"\"";
		public String Sub1Atr3 = "\"\"";
		public String Sub2Atr3 = "\"\"";
		public String Sub3Atr3 = "\"\"";
		public String Sub4Atr3 = "\"\"";
		public String Sub5Atr3 = "\"\"";
		public String Sub1Atr4 = "\"\"";
		public String Sub2Atr4 = "\"\"";
		public String Sub3Atr4 = "\"\"";
		public String Sub4Atr4 = "\"\"";
		public String Sub5Atr4 = "\"\"";
		public String Sub1Atr5 = "\"\"";
		public String Sub2Atr5 = "\"\"";
		public String Sub3Atr5 = "\"\"";
		public String Sub4Atr5 = "\"\"";
		public String Sub5Atr5 = "\"\"";
		public double AttackModifier = 0;
		public double DefenseModifier = 0;
		public String DefenseType = "Block";
		public double DodgeModifier = 0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				MinecraftDndMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("Attribute4", Attribute4);
			nbt.putString("Attribute3", Attribute3);
			nbt.putString("Attribute2", Attribute2);
			nbt.putString("Attribute1", Attribute1);
			nbt.putString("Attribute5", Attribute5);
			nbt.putString("Sub1Atr1", Sub1Atr1);
			nbt.putString("Sub2Atr1", Sub2Atr1);
			nbt.putString("Sub3Atr1", Sub3Atr1);
			nbt.putString("Sub4Atr1", Sub4Atr1);
			nbt.putString("Sub5Atr1", Sub5Atr1);
			nbt.putString("Sub1Atr2", Sub1Atr2);
			nbt.putString("Sub2Atr2", Sub2Atr2);
			nbt.putString("Sub3Atr2", Sub3Atr2);
			nbt.putString("Sub4Atr2", Sub4Atr2);
			nbt.putString("Sub5Atr2", Sub5Atr2);
			nbt.putString("Sub1Atr3", Sub1Atr3);
			nbt.putString("Sub2Atr3", Sub2Atr3);
			nbt.putString("Sub3Atr3", Sub3Atr3);
			nbt.putString("Sub4Atr3", Sub4Atr3);
			nbt.putString("Sub5Atr3", Sub5Atr3);
			nbt.putString("Sub1Atr4", Sub1Atr4);
			nbt.putString("Sub2Atr4", Sub2Atr4);
			nbt.putString("Sub3Atr4", Sub3Atr4);
			nbt.putString("Sub4Atr4", Sub4Atr4);
			nbt.putString("Sub5Atr4", Sub5Atr4);
			nbt.putString("Sub1Atr5", Sub1Atr5);
			nbt.putString("Sub2Atr5", Sub2Atr5);
			nbt.putString("Sub3Atr5", Sub3Atr5);
			nbt.putString("Sub4Atr5", Sub4Atr5);
			nbt.putString("Sub5Atr5", Sub5Atr5);
			nbt.putDouble("AttackModifier", AttackModifier);
			nbt.putDouble("DefenseModifier", DefenseModifier);
			nbt.putString("DefenseType", DefenseType);
			nbt.putDouble("DodgeModifier", DodgeModifier);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			Attribute4 = nbt.getString("Attribute4");
			Attribute3 = nbt.getString("Attribute3");
			Attribute2 = nbt.getString("Attribute2");
			Attribute1 = nbt.getString("Attribute1");
			Attribute5 = nbt.getString("Attribute5");
			Sub1Atr1 = nbt.getString("Sub1Atr1");
			Sub2Atr1 = nbt.getString("Sub2Atr1");
			Sub3Atr1 = nbt.getString("Sub3Atr1");
			Sub4Atr1 = nbt.getString("Sub4Atr1");
			Sub5Atr1 = nbt.getString("Sub5Atr1");
			Sub1Atr2 = nbt.getString("Sub1Atr2");
			Sub2Atr2 = nbt.getString("Sub2Atr2");
			Sub3Atr2 = nbt.getString("Sub3Atr2");
			Sub4Atr2 = nbt.getString("Sub4Atr2");
			Sub5Atr2 = nbt.getString("Sub5Atr2");
			Sub1Atr3 = nbt.getString("Sub1Atr3");
			Sub2Atr3 = nbt.getString("Sub2Atr3");
			Sub3Atr3 = nbt.getString("Sub3Atr3");
			Sub4Atr3 = nbt.getString("Sub4Atr3");
			Sub5Atr3 = nbt.getString("Sub5Atr3");
			Sub1Atr4 = nbt.getString("Sub1Atr4");
			Sub2Atr4 = nbt.getString("Sub2Atr4");
			Sub3Atr4 = nbt.getString("Sub3Atr4");
			Sub4Atr4 = nbt.getString("Sub4Atr4");
			Sub5Atr4 = nbt.getString("Sub5Atr4");
			Sub1Atr5 = nbt.getString("Sub1Atr5");
			Sub2Atr5 = nbt.getString("Sub2Atr5");
			Sub3Atr5 = nbt.getString("Sub3Atr5");
			Sub4Atr5 = nbt.getString("Sub4Atr5");
			Sub5Atr5 = nbt.getString("Sub5Atr5");
			AttackModifier = nbt.getDouble("AttackModifier");
			DefenseModifier = nbt.getDouble("DefenseModifier");
			DefenseType = nbt.getString("DefenseType");
			DodgeModifier = nbt.getDouble("DodgeModifier");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.Attribute4 = message.data.Attribute4;
					variables.Attribute3 = message.data.Attribute3;
					variables.Attribute2 = message.data.Attribute2;
					variables.Attribute1 = message.data.Attribute1;
					variables.Attribute5 = message.data.Attribute5;
					variables.Sub1Atr1 = message.data.Sub1Atr1;
					variables.Sub2Atr1 = message.data.Sub2Atr1;
					variables.Sub3Atr1 = message.data.Sub3Atr1;
					variables.Sub4Atr1 = message.data.Sub4Atr1;
					variables.Sub5Atr1 = message.data.Sub5Atr1;
					variables.Sub1Atr2 = message.data.Sub1Atr2;
					variables.Sub2Atr2 = message.data.Sub2Atr2;
					variables.Sub3Atr2 = message.data.Sub3Atr2;
					variables.Sub4Atr2 = message.data.Sub4Atr2;
					variables.Sub5Atr2 = message.data.Sub5Atr2;
					variables.Sub1Atr3 = message.data.Sub1Atr3;
					variables.Sub2Atr3 = message.data.Sub2Atr3;
					variables.Sub3Atr3 = message.data.Sub3Atr3;
					variables.Sub4Atr3 = message.data.Sub4Atr3;
					variables.Sub5Atr3 = message.data.Sub5Atr3;
					variables.Sub1Atr4 = message.data.Sub1Atr4;
					variables.Sub2Atr4 = message.data.Sub2Atr4;
					variables.Sub3Atr4 = message.data.Sub3Atr4;
					variables.Sub4Atr4 = message.data.Sub4Atr4;
					variables.Sub5Atr4 = message.data.Sub5Atr4;
					variables.Sub1Atr5 = message.data.Sub1Atr5;
					variables.Sub2Atr5 = message.data.Sub2Atr5;
					variables.Sub3Atr5 = message.data.Sub3Atr5;
					variables.Sub4Atr5 = message.data.Sub4Atr5;
					variables.Sub5Atr5 = message.data.Sub5Atr5;
					variables.AttackModifier = message.data.AttackModifier;
					variables.DefenseModifier = message.data.DefenseModifier;
					variables.DefenseType = message.data.DefenseType;
					variables.DodgeModifier = message.data.DodgeModifier;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
