package santa.ping;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    public static String soundType;
    public static String customNames;
    public static String customColor;
    public static boolean bold;
    public static boolean italic;
    public static boolean strikethrough;
    public static boolean underline;

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
        config.addCustomCategoryComment("Custom Color", "The color to highlight the text. Can be any of the " +
          "following: aqua, black, blue, dark aqua, dark blue, dark gray, dark grey, dark green, dark purple, " +
          "dark red, gold, gray, grey, green, light purple, red, yellow");
        customColor = config.get("Custom Color", "The custom color for the highlighted text", "").getString();

        bold = config.getBoolean("Bold", "Chat Style", true, "");
        italic = config.getBoolean("Italic", "Chat Style", false, "");
        strikethrough = config.getBoolean("Strikethrough", "Chat Style", false, "");
        underline = config.getBoolean("Underline", "Chat Style", false, "");

        config.save();
    }
}