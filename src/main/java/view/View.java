package view;

public interface View {

    /**
     * Method called by the controller to say if it's possible to stop the simulation.
     *
     * @param enabled - is possible to stop the simulation
     */
    void setStopEnabled(final Boolean enabled);

    /**
     * Method called by the controller to say if it's possible to start the simulation.
     *
     * @param enabled - is possible to start the simulation
     */
    void setStartEnabled(final Boolean enabled);

    /**
     * Method called to set a {@link ViewListener} to respond to certain event.
     *
     * @param listener - the listener to notify when the event happen
     */
    void addListener(final ViewListener listener);
}
