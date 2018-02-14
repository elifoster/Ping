package santa.ping

import net.minecraft.util.text.TextFormatting
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.FMLLog
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class Config(event: FMLPreInitializationEvent) {
  val DEFAULT_SOUND = "blop"
  val VALID_TYPES = List("blop", "gum", "pling", "ping", "ting", "pop")
  val COMMENT: String = String.format("The sound types to use as the ping noise. Can be any of the " +
    " following: %s. Defaults to %s, and will be %s if the value here is invalid.",
    VALID_TYPES.mkString(", "), DEFAULT_SOUND, DEFAULT_SOUND)

  val config = new Configuration(event.getSuggestedConfigurationFile)
  config.load()

  config.addCustomCategoryComment("Sound Type", COMMENT)
  var soundType: String = config.get("Sound Type", "The sound type", DEFAULT_SOUND).getString
  if (!VALID_TYPES.exists(s => s.equals(soundType))) {
    soundType = DEFAULT_SOUND
    FMLLog.warning("[Ping!] Ping sound type is invalid. Using blop. Please fix your config.")
  }

  config.addCustomCategoryComment("Custom Names", "Separate each with a semicolon ;")
  val customNamesString: String = config.get("Custom Names", "Custom names to be pinged as", "").getString
  val customNames: Option[Array[String]] = getNamesFromConfig(customNamesString)

  config.addCustomCategoryComment("Custom Color", "The color to highlight the text. Can be any of the " + "following: aqua, black, blue, dark aqua, dark blue, dark gray, dark grey, dark green, dark purple, " + "dark red, gold, gray, grey, green, light purple, red, yellow")
  val customColor: Option[TextFormatting] = getColorFromConfig(config.get("Custom Color", "The custom color for the highlighted text", "").getString)
  if (customColor.isEmpty) FMLLog.warning("[Ping!] Highlight color is invalid. Please fix your config.")

  val bold: Boolean = config.getBoolean("Bold", "Chat Style", true, "")
  val italic: Boolean = config.getBoolean("Italic", "Chat Style", false, "")
  val strikethrough: Boolean = config.getBoolean("Strikethrough", "Chat Style", false, "")
  val underline: Boolean = config.getBoolean("Underline", "Chat Style", false, "")
  config.save()

  def getNamesFromConfig(base: String): Option[Array[String]] = if (base.isEmpty) None else Some(base.split(";"))

  def getColorFromConfig(color: String): Option[TextFormatting] = {
    color match {
      case ("aqua") => Some(TextFormatting.AQUA)
      case ("black") => Some(TextFormatting.BLACK)
      case ("blue") => Some(TextFormatting.BLUE)
      case ("dark aqua") => Some(TextFormatting.DARK_AQUA)
      case ("dark blue") => Some(TextFormatting.DARK_BLUE)
      case ("dark gray") => Some(TextFormatting.DARK_GRAY)
      case ("dark grey") => Some(TextFormatting.DARK_GRAY)
      case ("dark green") => Some(TextFormatting.DARK_GREEN)
      case ("dark purple") => Some(TextFormatting.DARK_PURPLE)
      case ("dark red") => Some(TextFormatting.DARK_RED)
      case ("gold") => Some(TextFormatting.GOLD)
      case ("gray") => Some(TextFormatting.GRAY)
      case ("grey") => Some(TextFormatting.GRAY)
      case ("green") => Some(TextFormatting.GREEN)
      case ("light purple") => Some(TextFormatting.LIGHT_PURPLE)
      case ("red") => Some(TextFormatting.RED)
      case ("yellow") => Some(TextFormatting.YELLOW)
      case _ => None
    }
  }
}