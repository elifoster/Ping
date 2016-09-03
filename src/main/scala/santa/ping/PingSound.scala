package santa.ping

import net.minecraft.client.audio.ISound
import net.minecraft.client.audio.ISound.AttenuationType
import net.minecraft.client.audio.Sound
import net.minecraft.client.audio.SoundEventAccessor
import net.minecraft.client.audio.SoundHandler
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory

class PingSound(var name: String) extends ISound {
  val loc = new ResourceLocation("ping", name)
  var soundEvent: SoundEventAccessor = _
  var sound: Sound = _

  override def createAccessor(handler: SoundHandler): SoundEventAccessor = {
    soundEvent = handler.getAccessor(this.getSoundLocation)
    sound = if (soundEvent == null) SoundHandler.MISSING_SOUND else soundEvent.cloneEntry
    soundEvent
  }

  override def getSoundLocation: ResourceLocation = loc
  override def getSound: Sound = sound
  override def getCategory: SoundCategory = SoundCategory.MASTER
  override def canRepeat: Boolean = false
  override def getRepeatDelay: Int = 0
  override def getVolume: Float = 1.0F
  override def getPitch: Float = 1.0F
  override def getXPosF: Float = 0
  override def getYPosF: Float = 0
  override def getZPosF: Float = 0
  override def getAttenuationType: ISound.AttenuationType = AttenuationType.NONE
}
