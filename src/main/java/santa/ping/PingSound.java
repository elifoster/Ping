package santa.ping;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

public class PingSound implements ISound {
    public String name;
    public ResourceLocation loc;

    public PingSound(String name) {
        this.name = name;
        this.loc = new ResourceLocation("ping", name);
    }

    @Override
    public ResourceLocation getPositionedSoundLocation() {
        return this.loc;
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
