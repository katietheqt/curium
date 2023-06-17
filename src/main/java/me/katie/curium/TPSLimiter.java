package me.katie.curium;

public interface TPSLimiter {
    /**
     * Sets the target tick time of the client (in milliseconds). The client will sleep to keep the tickrate below this
     * value (default is 50ms per tick).
     *
     * @param millis the duration to sleep (in milliseconds). Negative values remove the TPS limit
     */
    void setTargetTickTime(long millis);

    /**
     * @return the target tick time
     * @see TPSLimiter#setTargetTickTime(long)
     */
    long getTargetTickTime();
}
