package aapelix.realmiutils;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "realmiutils")
class ModConfig implements ConfigData {

    boolean enabled = true;
    boolean autoMsgToggle = true;
    boolean autoTpToggle = true;

    @ConfigEntry.Gui.CollapsibleObject
    InnerStuff Homet = new InnerStuff();

    static class InnerStuff {
        String home1x = "0";
        String home1z = "0";

        String home2x = "0";
        String home2z = "0";

        String home3x = "0";
        String home3z = "0";

        String home4x = "0";
        String home4z = "0";

        String home5x = "0";
        String home5z = "0";

        String home6x = "0";
        String home6z = "0";
    }
}
