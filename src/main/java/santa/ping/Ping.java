package santa.ping;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = "Ping!", modid = "ping", version = "1.0.0")
public class Ping {
    public static String[] customNames;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.initialize(event);
        if (!isSoundTypeValid(Config.soundType)) {
            FMLLog.warning("[Ping] Ping sound type is invalid. Using blop. Please fix your config.");
            Config.soundType = "blop";
        }

        String[] names = getNamesFromConfig(Config.customNames);
        if (names != null) {
            customNames = names;
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

    /**
     * Converts the custom names string in the config to an array of usernames.
     * @param base The base customNames string.
     * @return An array containing all of the separated usernames
     */
    private static String[] getNamesFromConfig(String base) {
        if (base != "") {
            return base.split(";");
        }
        return null;
    }
}

