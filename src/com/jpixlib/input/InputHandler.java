package com.jpixlib.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import com.jpixlib.input.Keyboard.Key;
import com.jpixlib.input.Mouse.MouseButton;

/**
 * A class for processing input.
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 26.4.2013
 */
public final class InputHandler {
	/**
	 * Key processor.
	 * 
	 * @author Denis Zhidkikh
	 * @version 1.0
	 * @since 26.4.2013
	 */
	public final static class KeyHandler implements KeyListener {
		/**
		 * Currently registered keys.
		 */
		protected HashMap<Integer, Key> registeredKeys = new HashMap<Integer, Key>();

		public void keyPressed(KeyEvent e) {
			toggleKey(e, true);
		}

		public void keyReleased(KeyEvent e) {
			toggleKey(e, false);
		}

		public void keyTyped(KeyEvent e) {

		}

		/**
		 * Toggles the key's state.
		 * 
		 * @param ke
		 *            {@link KeyEvent} that contains the key.
		 * @param state
		 *            Key's new state.
		 */
		private void toggleKey(KeyEvent ke, boolean state) {
			Key key = registeredKeys.get(ke.getKeyCode());
			if (key != null)
				key.setNextState(state);
		}
	}

	/**
	 * Mouse processor.
	 * 
	 * @author Denis Zhidkikh
	 * @version 1.0
	 * @since 26.4.2013
	 */
	public final static class MouseHandler implements MouseListener, MouseMotionListener {
		/**
		 * Currently registered mouse buttons.
		 */
		protected HashMap<Integer, MouseButton> registeredButtons = new HashMap<Integer, MouseButton>();

		/**
		 * Mouse coordinates.
		 */
		protected int mousePosX = 0, mousePosY = 0;

		public void mouseDragged(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
			toggleButton(e, true);
		}

		public void mouseMoved(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
		}

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			toggleButton(e, true);
		}

		public void mouseReleased(MouseEvent e) {
			toggleButton(e, false);
		}

		/**
		 * Toggles the mouse button's state.
		 * 
		 * @param me
		 *            {@link MouseEvent} that contains the mouse button.
		 * @param state
		 *            Mouse button's new state.
		 */
		private void toggleButton(MouseEvent me, boolean state) {
			MouseButton mb = registeredButtons.get(me.getButton());
			if (mb != null)
				mb.setNextState(state);
		}

	}
}
