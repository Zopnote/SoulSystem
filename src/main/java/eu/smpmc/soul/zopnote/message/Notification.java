package eu.smpmc.soul.zopnote.message;

import eu.smpmc.soul.Soul;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;


/*
*  This code fall under the MIT License.
*  Created for the SMPmc.eu network as a helper.
*  ~https://github.com/zopnote
*/
public class Notification {
  String message;
  public Notification(String message) {
    this.message = message;
  }
  public Component getComponent() {
    return Soul.getPrefix().append(Component.text(message).color(TextColor.color(0xA6A6A6)));
  }
}
