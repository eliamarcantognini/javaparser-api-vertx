package view;

public interface View {

    /**
     * Method called by the controller to say if it's possible to stop the analysis.
     *
     * @param enabled - is possible to stop the analysis
     */
    void setStopEnabled(final Boolean enabled);

    /**
     * Method called by the controller to say if it's possible to start the analysis.
     *
     * @param enabled - is possible to start the analysis
     */
    void setStartEnabled(final Boolean enabled);

    /**
     * Method called by the controller to say if it's possible to save the analysis.
     *
     * @param enabled - is possible to save the analysis
     */
    void setSaveEnabled(final Boolean enabled);

}
