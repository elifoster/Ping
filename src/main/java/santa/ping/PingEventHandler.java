package santa.ping;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class PingEventHandler {
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        String name = player.getDisplayName().toLowerCase();
        String text = event.message.getUnformattedText().toLowerCase();
        text = text.replaceFirst("<.+>", "");
        if (text.contains(name)) {
            playSoundSendMessage(event.message);
        } else if (Ping.customNames != null) {
            for (int i = 0; i < Ping.customNames.length; i++) {
                if (text.contains(Ping.customNames[i])) {
                    playSoundSendMessage(event.message);
                    break;
                }
            }
        }
    }

    /**
     * Plays the sound defined in the config, and alters the chat message based on the config.
     *
     * @param component The ChatComponent to change.
     */
    private void playSoundSendMessage(IChatComponent component) {
        Minecraft.getMinecraft().getSoundHandler().playSound(Ping.SOUND);
        if (Ping.customColor != null) {
            component.getChatStyle().setColor(Ping.customColor);
        }

        component.getChatStyle().setBold(Config.bold);
        component.getChatStyle().setItalic(Config.italic);
        component.getChatStyle().setStrikethrough(Config.strikethrough);
        component.getChatStyle().setUnderlined(Config.underline);
    }
}