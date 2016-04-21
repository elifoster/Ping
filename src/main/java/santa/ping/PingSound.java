package santa.ping;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class PingSound implements ISound {
    public String name;
    public ResourceLocation loc;
    public SoundEventAccessor soundEvent;
    public Sound sound;

    public PingSound(String name) {
        this.name = name;
        this.loc = new ResourceLocation("ping", name);
    }

    @Override
    public ResourceLocation getSoundLocation() {
        return this.loc;
    }

    @Override
    public SoundEventAccessor createAccessor(SoundHandler handler) {
        this.soundEvent = handler.getAccessor(this.getSoundLocation());

        this.sound = this.soundEvent == null ? SoundHandler.MISSING_SOUND : this.soundEvent.cloneEntry();

        return this.soundEvent;
    }

    @Override
    public Sound getSound() {
        return this.sound;
    }

    @Override
    public SoundCategory getCategory() {
        return SoundCategory.MASTER;
    }

    @Override
    public boolean canRepeat() {
        return false;
    }

    @Override
    public int getRepeatDelay() {
        return 0;
    }

    @Override
    public float getVolume() {
        return 1.0F;
    }

    @Override
    public float getPitch() {
        return 1.0F;
    }

    @Override
    public float getXPosF() {
        return 0;
    }

    @Override
    public float getYPosF() {
        return 0;
    }

    @Override
    public float getZPosF() {
        return 0;
    }

    @Override
    public AttenuationType getAttenuationType() {
        return AttenuationType.NONE;
    }
}
