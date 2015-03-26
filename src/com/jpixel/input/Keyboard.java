package com.jpixel.input;

import java.awt.Component;
import java.awt.event.KeyEvent;

/**
 * Class to represent a keyboard.
 *
 * @author Denis Zhidkikh
 * @version 1.1
 * @since 26.4.2013
 */
public class Keyboard {
    private static InputHandler.KeyHandler keyboardHandler;

    /**
     * Initializes the keyboard and add it to the current Component.
     *
     * @param component Java's AWT Component (can be Swing component too) to which the
     *                  keyboard will be assigned.
     */
    public Keyboard(Component component) {
        keyboardHandler = new InputHandler.KeyHandler();
        component.addKeyListener(keyboardHandler);
    }

    /**
     * Registers a custom {@link KeyTypeEvent} to allow custom control of pressed keys.
     * Usable, for instance, when one requires text input from the user.
     *
     * @param keyTypeEvent {@link KeyTypeEvent} to register. Pass null to disable the event.
     */
    public final void registerKeyTypeEvent(KeyTypeEvent keyTypeEvent){
        keyboardHandler.keyTypeEvent = keyTypeEvent;
    }

    /**
     * Checks if the key has been pressed (current state differs from the old
     * one).
     *
     * @param key Key to check. See {@link KeyEvent}.
     * @return True, if the key has been pressed.
     */
    public final static boolean isKeyPressed(int key) {
        Key k = keyboardHandler.registeredKeys.get(key);
        if (k != null)
            return k.isPressed();
        return false;
    }

    /**
     * Checks if the key is currently down.
     *
     * @param key Key to check. See {@link KeyEvent}.
     * @return True, if the key is currently down.
     */
    public final static boolean isKeyDown(int key) {
        Key k = keyboardHandler.registeredKeys.get(key);
        if (k != null)
            return k.isDown();
        return false;
    }

    /**
     * Updates all the registered keys.
     */
    public final void update() {
        for (Key key : keyboardHandler.registeredKeys.values()) {
            key.update();
        }
    }

    /**
     * Registers the key, so it can be checked.
     *
     * @param key A {@link KeyEvent} key to register
     */
    public final void registerKey(int key) {
        keyboardHandler.registeredKeys.put(key, new Key());
    }

    /**
     * Removes the key, so it won't be checked anymore.
     *
     * @param key A {@link KeyEvent} key to remove.
     */
    public final void removeKey(int key) {
        keyboardHandler.registeredKeys.remove(key);
    }

    /**
     * Happens, when the main window looses focus.
     */
    public void onFocusLost() {
        for (Key key : keyboardHandler.registeredKeys.values()) {
            key.release();
        }
    }

    /**
     * A key on the keyboard.
     *
     * @author Denis Zhidkikh
     * @version 1.0
     * @since 26.4.2013
     */
    public final static class Key {
        private boolean nextState = false, wasDown = false, isDown = false;

        /**
         * Updates the key's state.
         */
        public void update() {
            wasDown = isDown;
            isDown = nextState;
        }

        /**
         * Releases the key.
         */
        public void release() {
            nextState = false;
        }

        /**
         * Sets the next state for the key, which will be applied the next time
         * update() runs.
         *
         * @param state The new state of the key.
         */
        public void setNextState(boolean state) {
            nextState = state;
        }

        /**
         * Checks if the key is currently held down.
         *
         * @return True, if the key is currently held down.
         */
        public boolean isDown() {
            return isDown;
        }

        /**
         * Checks if the key has been pressed (current state differs from the
         * older).
         *
         * @return True, if the key has been pressed.
         */
        public boolean isPressed() {
            return !wasDown && isDown;
        }
    }
}
