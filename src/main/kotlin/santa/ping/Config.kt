package santa.ping

import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.config.Configuration

class Config {
    companion object {
        var soundType = ""

        fun initialize(event: FMLPreInitializationEvent) {
            var config = Configuration(event.suggestedConfigurationFile)
            config.load()

            config.addCustomCategoryComment("Sound Type", "The sound type to use as the ping noise. Can be any of the" +
              " following: blop, gum, pling, ping, ting, pop. Defaults to blop, and will be blop if the value here" +
              " is invalid.")
            soundType = config.get("Sound Type", "The sound type", "blop").getString()

            config.save()
        }
    }
}