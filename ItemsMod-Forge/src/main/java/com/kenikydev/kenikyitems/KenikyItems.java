package com.kenikydev.kenikyitems;

import com.kenikydev.kenikyitems.block.ModBlocks;
import com.kenikydev.kenikyitems.component.ModDataComponentsTypes;
import com.kenikydev.kenikyitems.effect.ModEffects;
import com.kenikydev.kenikyitems.enchantment.ModEnchantmentEffects;
import com.kenikydev.kenikyitems.entity.ModEntities;
import com.kenikydev.kenikyitems.entity.client.TriceratopsRenderer;
import com.kenikydev.kenikyitems.item.ModCreativeModTabs;
import com.kenikydev.kenikyitems.item.ModItems;
import com.kenikydev.kenikyitems.potion.ModPotions;
import com.kenikydev.kenikyitems.sound.ModSounds;
import com.kenikydev.kenikyitems.util.ModItemProperties;
import com.kenikydev.kenikyitems.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KenikyItems.MODID)
public class KenikyItems
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "kenikyitems";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public KenikyItems()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModDataComponentsTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModSounds.register(modEventBus);

        ModEffects.register(modEventBus);

        ModPotions.register(modEventBus);

        ModEntities.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModVillagers.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
       event.enqueueWork(() -> {
           ComposterBlock.COMPOSTABLES.put(ModItems.KFOOD.get(), 0.4f);
           ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI_SEEDS.get(), 0.15f);
       });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();
            EntityRenderers.register(ModEntities.TRICERATOPS.get(), TriceratopsRenderer::new);
        }
    }

}
