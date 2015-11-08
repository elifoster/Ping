package santa.ping

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.MinecraftForge

@Mod(name = "Ping!", modid = "ping", version = "1.0.0") class Ping {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        Config.initialize(event)
        if (!isSoundTypeValid(Config.soundType.toLowerCase())) {
            Config.soundType = "blop"
        }
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(PingEventHandler())
    }

    /**
     * @param sound The sound type string provided by the configuration file.
     */
    fun isSoundTypeValid(sound: String): Boolean {
        val validTypes = arrayOf("blop", "gum", "pling", "ping", "ting", "pop")
        if (validTypes.contains(sound)) {
            return true
        } else {
            return false
        }
    }
}

