package aapelix.realmiutils;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class RealmiUtils implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("argonium-mod");

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		LOGGER.info("Hello Fabric world!");
	}
}