
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minecraftdnd.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.minecraftdnd.item.TestItem;
import net.mcreator.minecraftdnd.MinecraftDndMod;

public class MinecraftDndModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftDndMod.MODID);
	public static final RegistryObject<Item> TEST = REGISTRY.register("test", () -> new TestItem());
}
