package me.katie.curium.impl.api;

import me.katie.curium.TPSLimiter;
import me.katie.curium.impl.CuriumConstants;
import net.minecraft.Util;

public class TPSLimiterImpl implements TPSLimiter {
    private long tickStartTime;
    private long targetTickTime = 50; // 20TPS
    private long laggyTicks = 0;

    /**
     * Hook that runs before a tick.
     */
    public void preTick() {
        this.tickStartTime = Util.getMillis();
    }

    /**
     * Hook that runs after a tick and sleeps if needed to meet the tick deadline.
     */
    public void postTick() {
        if (this.targetTickTime < 0) {
            return;
        }

        long tickEndTime = Util.getMillis();
        long tickTime = tickEndTime - this.tickStartTime;
        long remainingBudget = this.targetTickTime - tickTime;

        if (remainingBudget >= 0) {
            this.laggyTicks = 0;

            // We have time left before the next deadline, sleep.
            try {
                Thread.sleep(remainingBudget);
            } catch (InterruptedException ignored) {

            }
        } else {
            // Tick took longer than our time budget!
            this.laggyTicks++;

            if ((this.laggyTicks % 20) == 0) {
                CuriumConstants.LOGGER.warn("{} consecutive ticks exceeded {}ms time budget! ({}ms)", this.laggyTicks, this.targetTickTime, tickTime);
            }
        }
    }

    @Override
    public void setTargetTickTime(long millis) {
        this.targetTickTime = millis;
    }

    @Override
    public long getTargetTickTime() {
        return this.targetTickTime;
    }
}
