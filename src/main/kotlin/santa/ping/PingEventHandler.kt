package santa.ping

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent

class PingEventHandler {
    @SubscribeEvent fun onChatMessage(event: ClientChatReceivedEvent) {
        var mc = Minecraft.getMinecraft()
        var player = mc.thePlayer
        var world = mc.theWorld
        var text = event.message.unformattedText
        if (text.contains(player.displayName, false)) {
            world.playSoundAtEntity(player, "ping:ping", 1.0F, 1.0F)
        }
    }
}