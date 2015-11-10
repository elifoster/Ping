package santa.ping;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static String soundType;
    public static String customNames;
    public static final String[] VALID_TYPES = new String[] { "blop", "gum", "pling", "ping", "ting", "pop" };
    public static final String COMMENT = String.format("The sound types to use as the ping noise. Can be any of the " +
      "following: %s. Defaults to %s, and will be %s if the value here is invalid.",
      String.join(", ", VALID_TYPES), soundType, soundType);

    public static void initialize(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.addCustomCategoryComment("Sound Type", COMMENT);
        soundType = config.get("Sound Type", "The sound type", "blop").getString();
        config.addCustomCategoryComment("Custom Names", "Separate each name with a semicolon ;");
        customNames = config.get("Custom Names", "Custom names to be pinged as", "").getString();

        config.save();
    }
}