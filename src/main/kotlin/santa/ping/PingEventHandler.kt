package santa.ping

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import kotlin.text.Regex

class PingEventHandler {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun onChatMessage(event: ClientChatReceivedEvent) {
        val mc = Minecraft.getMinecraft()
        val player = mc.thePlayer
        val name = player.displayName.toLowerCase()
        var text = event.message.unformattedText.toLowerCase()
        val regex = Regex("<.+>")
        text = text.replaceFirst(regex, "")
        if (text.contains(name) == true) {
            player.playSound("ping:${Config.soundType}", 1.0F, 1.0F)
        }
    }
}