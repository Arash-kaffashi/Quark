/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [02/07/2016, 23:09:15 (GMT)]
 */
package vazkii.quark.world.feature;

import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import vazkii.quark.base.Quark;
import vazkii.quark.base.lib.LibEntityIDs;
import vazkii.quark.base.module.Feature;
import vazkii.quark.world.client.render.RenderPirate;
import vazkii.quark.world.entity.EntityPirate;
import vazkii.quark.world.item.ItemPirateHat;

public class PirateShips extends Feature {

	public static Item pirate_hat;
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		pirate_hat = new ItemPirateHat();
		
		EntityRegistry.registerModEntity(EntityPirate.class, "pirate", LibEntityIDs.PIRATE, Quark.instance, 80, 3, true, 0x4d1d14, 0xac9617);
	}
	
	@Override
	public void preInitClient(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, RenderPirate.FACTORY);
	}
	
}