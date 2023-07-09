package aapelix.realmiutils;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class RealmiUtilsClient implements ClientModInitializer {
	public boolean first = true;
	public int home1x = 0;
	public int home1z = 0;

	public int home2x = 0;
	public int home2z = 0;

	public int home3x = 0;
	public int home3z = 0;

	public int home4x = 0;
	public int home4z = 0;

	public int home5x = 0;
	public int home5z = 0;

	public int home6x = 0;
	public int home6z = 0;

	public int nextHome = 1;

	public String home1name = "Koti";
	public String home2name = "Koti 2";
	public String home3name = "Koti 3";
	public String home4name = "Koti 4";
	public String home5name = "Koti 5";
	public String home6name = "Koti 6";

	public String closestHome;

	@Override
	public void onInitializeClient() {

		KeyBinding setHome = KeyBindingHelper.registerKeyBinding(new KeyBinding("Set Home", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "Argonium"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;


			if (setHome.wasPressed()) {
				if (nextHome == 1) {
					home1x = (int) player.getX();
					home1z = (int) player.getZ();
					nextHome++;

					player.sendMessage(sendMessage("Koti 1 asetettu: " + home1x + " " + home1z, Formatting.GOLD, true, false, ""), false);
				} else if (nextHome == 2) {
					home2x = (int) player.getX();
					home2z = (int) player.getZ();
					nextHome++;
					player.sendMessage(sendMessage("Koti 2 asetettu: " + home2x + " " + home2z, Formatting.GOLD, true, false, ""), false);
				} else if (nextHome == 3) {
					home3x = (int) player.getX();
					home3z = (int) player.getZ();
					nextHome++;
					player.sendMessage(sendMessage("Koti 3 asetettu: " + home3x + " " + home3z, Formatting.GOLD, true, false, ""), false);
				} else if (nextHome == 4) {
					home4x = (int) player.getX();
					home4z = (int) player.getZ();
					nextHome++;
					player.sendMessage(sendMessage("Koti 4 asetettu: " + home4x + " " + home4z, Formatting.GOLD, true, false, ""), false);
				} else if (nextHome == 5) {
					home5x = (int) player.getX();
					home5z = (int) player.getZ();
					nextHome++;
					player.sendMessage(sendMessage("Koti 5 asetettu: " + home5x + " " + home5z, Formatting.GOLD, true, false, ""), false);
				} else if (nextHome == 6) {
					home6x = (int) player.getX();
					home6z = (int) player.getZ();
					nextHome++;
					player.sendMessage(sendMessage("Koti 6 asetettu: " + home6x + " " + home6z, Formatting.GOLD, true, false, ""), false);
				} else {
					player.sendMessage(sendMessage("Kaikki kodit on jo asetettu!", Formatting.GOLD, true, false, ""), false);
				}
			}
			if (player == null) {
				return;
			}
			if (!player.isAlive() && first) {
				first = false;

				int x = (int) player.getX();
				int z = (int) player.getZ();

				int distance1 = (int) Math.sqrt(Math.pow(x - home1x, 2) + Math.pow(z - home1z, 2));
				int distance2 = (int) Math.sqrt(Math.pow(x - home2x, 2) + Math.pow(z - home2z, 2));
				int distance3 = (int) Math.sqrt(Math.pow(x - home3x, 2) + Math.pow(z - home3z, 2));
				int distance4 = (int) Math.sqrt(Math.pow(x - home4x, 2) + Math.pow(z - home4z, 2));
				int distance5 = (int) Math.sqrt(Math.pow(x - home5x, 2) + Math.pow(z - home5z, 2));
				int distance6 = (int) Math.sqrt(Math.pow(x - home6x, 2) + Math.pow(z - home6z, 2));

				int[] distances = {distance1, distance2, distance3, distance4, distance5, distance6};

				int closest = 1000000000;
				int closestIndex = 0;

				for (int i = 0; i < distances.length; i++) {
					if (distances[i] < closest) {
						closest = distances[i];
						closestIndex = i + 1;
					}
				}

				if (closestIndex == 1) {
					closestHome = home1name;
				} else if (closestIndex == 2) {
					closestHome = home2name;
				} else if (closestIndex == 3) {
					closestHome = home3name;
				} else if (closestIndex == 4) {
					closestHome = home4name;
				} else if (closestIndex == 5) {
					closestHome = home5name;
				} if (closestIndex == 6) {
					closestHome = home6name;
				}

				player.sendMessage(Text.of("Kuolit sijainnissa: " + Math.floor(player.getX()) + " " + Math.floor(player.getY()) + " " + Math.floor(player.getZ())), false);
				player.sendMessage(sendMessage("Lähin koti:", Formatting.AQUA, true, false, ""), false);
				player.sendMessage(sendMessage("[ " + closestHome + " ] (Clickkaa teleportataksesi)", Formatting.GOLD, false, true, "/home " + closestIndex), false);
			}

			if (player.isAlive() && !first) {
				first = true;
			}
		});
	}

	public Text sendMessage(String test, Formatting color, Boolean bold, Boolean onClick, String command) {
		Text message = Text.of(test);

		Style style = message.getStyle().withFormatting(color);

		if (bold) {
			style = style.withBold(true).withFormatting(color);
		}

		if (onClick) {
			style = style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		}

		Text formattedMessage = message.copy().setStyle(style);

		return formattedMessage;
	}


}
