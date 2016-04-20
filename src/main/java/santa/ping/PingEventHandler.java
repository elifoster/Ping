package santa.ping;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PingEventHandler {
    @SubscribeEvent
    public void onChatMessage(ServerChatEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        String name = player.getName().toLowerCase();
        ChatComponentTranslation component = (ChatComponentTranslation) event.getComponent();
        String text = event.getComponent().getUnformattedText().toLowerCase();
        text = text.replaceFirst("<.+>", "");
        if (text.contains(name)) {
            playSoundSendMessage(component, player);
        } else if (Ping.customNames != null) {
            for (int i = 0; i < Ping.customNames.length; i++) {
                if (text.contains(Ping.customNames[i])) {
                    playSoundSendMessage(component, player);
                    break;
                }
            }
        }
    }

    /**
     * Plays the sound defined in the config, and alters the chat message based on the config.
     *
     * @param component The ChatComponentTranslation to change.
     * @param target The player whose client to play the sound in.
     */
    private void playSoundSendMessage(ChatComponentTranslation component, EntityPlayer target) {
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