package santa.ping;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = "Ping!", modid = "ping", version = "3.0.4", acceptedMinecraftVersions = "[1.9,1.10.2]")
public class Ping {
    public static String[] customNames;
    public static TextFormatting customColor = null;
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
     * @param color The string representing the color.
     * @return An EnumChatFormatting color value, or null if it is invalid.
     */
    private static TextFormatting getColorFromConfig(String color) {
        switch (color) {
            case ("aqua"): return TextFormatting.AQUA;
            case ("black"): return TextFormatting.BLACK;
            case ("blue"): return TextFormatting.BLUE;
            case ("dark aqua"): return TextFormatting.DARK_AQUA;
            case ("dark blue"): return TextFormatting.DARK_BLUE;
            case ("dark gray"): return TextFormatting.DARK_GRAY;
            case ("dark grey"): return TextFormatting.DARK_GRAY;
            case ("dark green"): return TextFormatting.DARK_GREEN;
            case ("dark purple"): return TextFormatting.DARK_PURPLE;
            case ("dark red"): return TextFormatting.DARK_RED;
            case ("gold"): return TextFormatting.GOLD;
            case ("gray"): return TextFormatting.GRAY;
            case ("grey"): return TextFormatting.GRAY;
            case ("green"): return TextFormatting.GREEN;
            case ("light purple"): return TextFormatting.LIGHT_PURPLE;
            case ("red"): return TextFormatting.RED;
            case ("yellow"): return TextFormatting.YELLOW;
        }
        return null;
    }
}

