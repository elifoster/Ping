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
    val component: ITextComponent = event.getMessage

    def playSoundSendMessage(): Unit = {
      mc.getSoundHandler.playSound(Ping.sound)
      val style = new Style
      if (Ping.config.customColor.nonEmpty) style.setColor(Ping.config.customColor.get)
      style.setBold(Ping.config.bold)
      style.setItalic(Ping.config.italic)
      style.setStrikethrough(Ping.config.strikethrough)
      style.setUnderlined(Ping.config.underline)
      component.setStyle(style)
    }

    val player = mc.thePlayer
    val name = player.getName
    val text = component.getUnformattedText.toLowerCase.replaceFirst("^(\\[|<|\\* )"+name.toLowerCase+"(]|>|)?", "")
    val names: Array[String] = if (Ping.config.customNames.isEmpty) Array(name) else Ping.config.customNames.get :+ name

    if (names.map(n => n.toLowerCase).exists(text.contains)) playSoundSendMessage()
  }
}
