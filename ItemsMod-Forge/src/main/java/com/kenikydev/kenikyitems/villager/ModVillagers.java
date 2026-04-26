package com.kenikydev.kenikyitems.villager;

import com.google.common.collect.ImmutableSet;
import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.block.ModBlocks;
import com.kenikydev.kenikyitems.sound.ModSounds;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, KenikyItems.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, KenikyItems.MODID);

    public static final RegistryObject<PoiType> KENIKY_POI = POI_TYPE.register("keniky_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.ACACIA_DOOR.getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> KENIKIER = VILLAGER_PROFESSIONS.register("kenikier",
            () -> new VillagerProfession("kenikier", holder -> holder.value() == KENIKY_POI.get(),
                    holder -> holder.value() == KENIKY_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    ModSounds.MAGIC_BLOCK_HIT.get()));

    public static void register(IEventBus bus) {
        POI_TYPE.register(bus);
        VILLAGER_PROFESSIONS.register(bus);
    }
}
