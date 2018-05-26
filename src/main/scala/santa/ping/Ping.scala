package santa.ping

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = "Ping!", modid = "chatping", version = "3.0.9", acceptedMinecraftVersions = "[1.9,1.12.2]", modLanguage = "scala", clientSideOnly = true)
object Ping {
  var sound: PingSound = _
  var config: Config = _

  @Mod.EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    config = new Config(event)
    sound = new PingSound(config.soundType)
  }

  @Mod.EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    MinecraftForge.EVENT_BUS.register(new PingEventHandler())
  }
}
