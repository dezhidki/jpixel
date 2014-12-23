package com.jpixel.input;

import java.awt.Component;
import java.awt.event.MouseEvent;

/**
 * A class that represents a mouse.
 * 
 * @author Denis Zhdikikh
 * @version 1.0
 * @since 26.4.2013
 */
public class Mouse {
	/**
	 * A mouse button.
	 * 
	 * @author Denis Zhidkikh
	 * @version 1.0
	 * @since 26.4.2013
	 */
	public final static class MouseButton {
		private boolean nextState = false, wasDown = false, isDown = false;

		/**
		 * Updates the mouse button.
		 */
		public void update() {
			wasDown = isDown;
			isDown = nextState;
		}

		/**
		 * Sets the next state for the mouse button, which will be applied the
		 * next time update() runs.
		 * 
		 * @param state
		 *            The new state of the mouse button.
		 */
		public void setNextState(boolean state) {
			nextState = state;
		}

		/**
		 * Checks if the mouse button has been pressed (current state differs
		 * from the older).
		 * 
		 * @return True, if the mouse button has been pressed.
		 */
		public boolean isPressed() {
			return !wasDown && isDown;
		}

		/**
		 * Checks if the mouse button is currently held down.
		 * 
		 * @return True, if the mouse button is currently held down.
		 */
		public boolean isDown() {
			return isDown;
		}

		/**
		 * Releases the mouse button.
		 */
		public void release() {
			nextState = false;
		}
	}

	private static InputHandler.MouseHandler mouseHandler;

	/**
	 * Initializes the mouse.
	 * 
	 * @param component
	 *            Java's AWT Component (can be Swing component too) to which the
	 *            mouse will be assigned.
	 */
	public Mouse(Component component) {
		mouseHandler = new InputHandler.MouseHandler();
		component.addMouseListener(mouseHandler);
		component.addMouseMotionListener(mouseHandler);
	}

	/**
	 * Gets mouse's current position (X -coordinate).
	 * 
	 * @return Mouses current position (X -coordinate).
	 */
	public final static int getMouseX() {
		return mouseHandler.mousePosX;
	}

	/**
	 * Gets mouse's current position (Y -coordinate).
	 * 
	 * @return Mouses current position (Y -coordinate).
	 */
	public final static int getMouseY() {
		return mouseHandler.mousePosY;
	}

	/**
	 * Checks if the mouse button has been pressed (current state differs from
	 * the older).
	 * 
	 * @param button
	 *            Mouse button to check. See {@link MouseEvent}.
	 * @return True, if mouse button has been pressed.
	 */
	public final static boolean isButtonPressed(int button) {
		MouseButton mb = mouseHandler.registeredButtons.get(button);
		if (mb != null)
			return mb.isPressed();
		return false;
	}

	/**
	 * Checks if the mouse button is currently held down.
	 * 
	 * @param button
	 *            Mouse button to check. See {@link MouseEvent}.
	 * @return True, if mouse is currently held down.
	 */
	public final static boolean isButtonDown(int button) {
		MouseButton mb = mouseHandler.registeredButtons.get(button);
		if (mb != null)
			return mb.isDown();
		return false;
	}

	/**
	 * Happens, when the main window looses focus.
	 */
	public void onFocusLost() {
		for (MouseButton button : mouseHandler.registeredButtons.values()) {
			button.release();
		}
	}

	/**
	 * Updates all the registered mouse buttons.
	 */
	public final void update() {
		for (MouseButton button : mouseHandler.registeredButtons.values()) {
			button.update();
		}
	}

	/**
	 * Registers a mouse button for checking.
	 * 
	 * @param button
	 *            A {@link MouseEvent} button to register.
	 */
	public final void registerMouseButton(int button) {
		mouseHandler.registeredButtons.put(button, new MouseButton());
	}
}
