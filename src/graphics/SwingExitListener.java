package graphics;

import java.awt.*;
import java.awt.event.*;

/** A listener that you attach to the top-level Frame or JFrame of
 *  your application, so quitting the frame exits the application.
 *  1998-99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 */

public class SwingExitListener extends WindowAdapter {
  public void windowClosing(WindowEvent event) {
	  //TODO this needs to be changed to fire an "exit" event to the world class.
	System.out.println("Exit Event should be fired");
    System.exit(0);
  }
}
