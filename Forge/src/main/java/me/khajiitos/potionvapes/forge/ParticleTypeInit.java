package me.khajiitos.potionvapes.forge;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import me.khajiitos.potionvapes.common.PotionVapes;
import me.khajiitos.potionvapes.common.menu.VapeJuicerMenu;
import me.khajiitos.potionvapes.common.particle.VapeParticleOption;
import me.khajiitos.potionvapes.common.particle.VapeSmokeParticle;
import me.khajiitos.potionvapes.common.stuff.VapeMenus;
import me.khajiitos.potionvapes.common.stuff.VapeParticles;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ParticleTypeInit {
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PotionVapes.MOD_ID);

    private static final RegistryObject<ParticleType<VapeParticleOption>> VAPE_JUICER_OPTION = PARTICLE_TYPES.register("vape_smoke", () -> {
        VapeParticles.VAPE_SMOKE = new ParticleType<>(false, VapeParticleOption.DESERIALIZER) {
            @Override
            public @NotNull Codec<VapeParticleOption> codec() {
                return VapeParticleOption.CODEC;
            }
        };
        return VapeParticles.VAPE_SMOKE;
    });

    public static void init(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
