package santa.ping;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PingEventHandler {
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        String name = player.getName().toLowerCase();
        TextComponentTranslation component = (TextComponentTranslation) event.getMessage();
        String text = event.getMessage().getUnformattedText().toLowerCase();
        text = text.replaceFirst("<.+>", "");
        if (text.contains(name)) {
            playSoundSendMessage(component, mc);
        } else if (Ping.customNames != null) {
            for (int i = 0; i < Ping.customNames.length; i++) {
                if (text.contains(Ping.customNames[i])) {
                    playSoundSendMessage(component, mc);
                    break;
                }
            }
        }
    }

    /**
     * Plays the sound defined in the config, and alters the chat message based on the config.
     *
     * @param component The ChatComponentTranslation to change.
     * @param mc The Minecraft instance to play the sound in.
     */
    private void playSoundSendMessage(TextComponentTranslation component, Minecraft mc) {
        mc.getSoundHandler().playSound(Ping.SOUND);
        Style style = new Style();
        if (Ping.customColor != null) {
            style.setColor(Ping.customColor);
        }

        style.setBold(Config.bold);
        style.setItalic(Config.italic);
        style.setStrikethrough(Config.strikethrough);
        style.setUnderlined(Config.underline);

        component.setStyle(style);
    }
}