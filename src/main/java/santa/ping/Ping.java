package santa.ping;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = "Ping!", modid = "ping", version = "1.1.2")
public class Ping {
    public static String[] customNames;
    public static EnumChatFormatting customColor = null;
    public static PingSound SOUND;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.initialize(event);
        if (!isSoundTypeValid(Config.soundType)) {
            FMLLog.warning("[Ping!] Ping sound type is invalid. Using blop. Please fix your config.");
            Config.soundType = "blop";
        }

        String[] names = getNamesFromConfig(Config.customNames);
        if (names != null) {
            customNames = names;
        }

        customColor = getColorFromConfig(Config.customColor);
        if (customColor == null) {
            FMLLog.warning("[Ping!] Highlight color is invalid. Please fix your config.");
        }

        SOUND = new PingSound(Config.soundType);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PingEventHandler());
    }

    /**
     * Determines if the sound is valid according to the Config valid types array.
     *
     * @author Eli Clemente Gordillo Foster
     * @param sound The sound type string provided by the configuration file.
     * @return A boolean value representing whether the given sound type is valid.
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
     *
     * @author Eli Clemente Gordillo Foster
     * @param base The base customNames string.
     * @return An array containing all of the separated usernames
     */
    private static String[] getNamesFromConfig(String base) {
        if (!base.equals("")) {
            return base.split(";");
        }
        return null;
    }

    /**
     * Gets the according EnumChatFormatting color based on the string in the config file.
     *
     * @author Eli Clemente Gordillo Foster
     * @param color The string representing the color.
     * @return An EnumChatFormatting color value, or null if it is invalid.
     */
    private static EnumChatFormatting getColorFromConfig(String color) {
        switch (color) {
            case ("aqua"): return EnumChatFormatting.AQUA;
            case ("black"): return EnumChatFormatting.BLACK;
            case ("blue"): return EnumChatFormatting.BLUE;
            case ("dark aqua"): return EnumChatFormatting.DARK_AQUA;
            case ("dark blue"): return EnumChatFormatting.DARK_BLUE;
            case ("dark gray"): return EnumChatFormatting.DARK_GRAY;
            case ("dark grey"): return EnumChatFormatting.DARK_GRAY;
            case ("dark green"): return EnumChatFormatting.DARK_GREEN;
            case ("dark purple"): return EnumChatFormatting.DARK_PURPLE;
            case ("dark red"): return EnumChatFormatting.DARK_RED;
            case ("gold"): return EnumChatFormatting.GOLD;
            case ("gray"): return EnumChatFormatting.GRAY;
            case ("grey"): return EnumChatFormatting.GRAY;
            case ("green"): return EnumChatFormatting.GREEN;
            case ("light purple"): return EnumChatFormatting.LIGHT_PURPLE;
            case ("red"): return EnumChatFormatting.RED;
            case ("yellow"): return EnumChatFormatting.YELLOW;
        }
        return null;
    }
}

