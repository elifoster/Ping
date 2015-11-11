package santa.ping;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;

public class PingEventHandler {
    @SubscribeEvent
    public void onChatMessage(ServerChatEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        String name = player.getDisplayName().toLowerCase();
        String text = event.component.getUnformattedText().toLowerCase();
        text = text.replaceFirst("<.+>", "");
        if (text.contains(name)) {
            playSoundSendMessage(event.component, player);
        } else if (Ping.customNames != null) {
            for (int i = 0; i < Ping.customNames.length; i++) {
                if (text.contains(Ping.customNames[i])) {
                    playSoundSendMessage(event.component, player);
                    break;
                }
            }
        }
    }

    /**
     * Plays the sound defined in the config, and alters the chat message based on the config.
     *
     * @author Eli Clemente Gordillo Foster
     * @param component The ChatComponentTranslation to change.
     * @param target The player whose client to play the sound in.
     */
    private void playSoundSendMessage(ChatComponentTranslation component, EntityPlayer target) {
        target.playSound(String.format("ping:%s", Config.soundType), 1.0F, 1.0F);
        if (Ping.customColor != null) {
            component.getChatStyle().setColor(Ping.customColor);
        }

        component.getChatStyle().setBold(Config.bold);
        component.getChatStyle().setItalic(Config.italic);
        component.getChatStyle().setStrikethrough(Config.strikethrough);
        component.getChatStyle().setUnderlined(Config.underline);
    }
}