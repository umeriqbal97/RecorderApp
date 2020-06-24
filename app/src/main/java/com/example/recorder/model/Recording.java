package com.example.recorder.model;

/**
 * The type Recording.
 */
public class Recording {
    /**
     * The Name.
     */
    String name, /**
     * The Added.
     */
    added;

    /**
     * Instantiates a new Recording.
     */
    public Recording(){}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets added.
     *
     * @return the added
     */
    public String getAdded() {
        return added;
    }

    /**
     * Sets added.
     *
     * @param added the added
     */
    public void setAdded(String added) {
        this.added = added;
    }

    /**
     * Instantiates a new Recording.
     *
     * @param name  the name
     * @param added the added
     */
    public Recording(String name, String added) {
        this.name = name;
        this.added = added;
    }
}
