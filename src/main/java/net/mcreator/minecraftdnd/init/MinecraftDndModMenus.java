
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.minecraftdnd.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.minecraftdnd.world.inventory.SystemCreatorGUIMenu;
import net.mcreator.minecraftdnd.MinecraftDndMod;

public class MinecraftDndModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MinecraftDndMod.MODID);
	public static final RegistryObject<MenuType<SystemCreatorGUIMenu>> SYSTEM_CREATOR_GUI = REGISTRY.register("system_creator_gui", () -> IForgeMenuType.create(SystemCreatorGUIMenu::new));
}
