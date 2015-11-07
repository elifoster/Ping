package santa.ping

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import net.minecraftforge.common.MinecraftForge

@Mod(name = "Ping!", modid = "ping", version = "1.0.0") class Ping {
    @Mod.EventHandler fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(PingEventHandler())
    }
}

