package santa.ping;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = "Ping!", modid = "ping", version = "1.0.0")
public class Ping {
    public static String soundType;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.initialize(event);
        if (!isSoundTypeValid(Config.soundType)) {
            FMLLog.warning("[Ping] Ping sound type is invalid. Using blop. Please fix your config.");
            soundType = "blop";
        } else {
            soundType = Config.soundType;
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PingEventHandler());
    }

    /**
     * @param sound The sound type string provided by the configuration file.
     */
    private static boolean isSoundTypeValid(String sound) {
        for (String s : Config.VALID_TYPES) {
            if (s.equalsIgnoreCase(sound)) {
                return true;
            }
        }
        return false;
    }
}

