package me.khajiitos.potionvapes.forge;

import me.khajiitos.potionvapes.common.PotionVapes;
import me.khajiitos.potionvapes.common.event.LungCancerEvents;
import me.khajiitos.potionvapes.common.packet.PacketManager;
import me.khajiitos.potionvapes.common.stuff.VapeEnchantmentCategory;
import me.khajiitos.potionvapes.common.stuff.VapeVillagerTrades;
import me.khajiitos.potionvapes.common.util.TickDelayedCalls;
import me.khajiitos.potionvapes.forge.client.PotionVapesClient;
import me.khajiitos.potionvapes.forge.event.ForgeLungCancerEvents;
import me.khajiitos.potionvapes.forge.packet.ForgePacketManager;
import me.khajiitos.potionvapes.forge.packet.ForgePackets;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PotionVapes.MOD_ID)
public class PotionVapesForge {

    public PotionVapesForge() {
        PotionVapes.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PacketManager.instance = new ForgePacketManager();
        ForgePackets.init();

        VapeEnchantmentCategory.CATEGORY = EnchantmentCategory.create("VAPE", VapeEnchantmentCategory.PREDICATE);
        BlockInit.init(eventBus);
        ItemInit.init(eventBus);
        SoundInit.init(eventBus);
        BlockEntityInit.init(eventBus);
        MenuInit.init(eventBus);
        EnchantmentInit.init(eventBus);
        LootTablesInit.init(eventBus);
        ParticleTypeInit.init(eventBus);
        MobEffectInit.init(eventBus);

        ProfessionsInit.init(eventBus);
        PoiTypesInit.init(eventBus);
        VapeVillagerTrades.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> PotionVapesClient::init);
        MinecraftForge.EVENT_BUS.addListener(PotionVapesForge::onServerTick);
        MinecraftForge.EVENT_BUS.addListener(PotionVapesForge::onPlayerTick);

        MinecraftForge.EVENT_BUS.register(ForgeLungCancerEvents.class);
    }

    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            LungCancerEvents.tickPlayer(e.player);
        }
    }

    public static void onServerTick(TickEvent.ServerTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            TickDelayedCalls.tick(e.getServer());
        }
    }
}