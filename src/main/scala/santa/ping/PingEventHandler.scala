package santa.ping

import net.minecraft.client.Minecraft
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.Style
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class PingEventHandler {
  @SubscribeEvent
  def onChatMessage(event: ClientChatReceivedEvent): Unit = {
    val mc = Minecraft.getMinecraft
    val player = mc.thePlayer
    val name = player.getName.toLowerCase
    val component: ITextComponent = event.getMessage
    val text = event.getMessage.getUnformattedText.toLowerCase.replaceFirst("<.+>", "")
    if (text.contains(name)) {
      playSoundSendMessage(component, mc)
    } else if (Ping.config.customNames.nonEmpty && Ping.config.customNames.get.exists(text.contains)) {
      playSoundSendMessage(component, mc)
    }
  }

  /**
    * Plays the sound defined in the config, and alters the chat message based on the config.
    *
    * @param component The ChatComponentTranslation to change.
    * @param mc        The Minecraft instance to play the sound in.
    */
  def playSoundSendMessage(component: ITextComponent, mc: Minecraft): Unit = {
    mc.getSoundHandler.playSound(Ping.sound)
    val style = new Style
    if (Ping.config.customColor.nonEmpty) style.setColor(Ping.config.customColor.get)
    style.setBold(Ping.config.bold)
    style.setItalic(Ping.config.italic)
    style.setStrikethrough(Ping.config.strikethrough)
    style.setUnderlined(Ping.config.underline)
    component.setStyle(style)
  }
}