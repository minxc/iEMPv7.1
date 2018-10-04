package org.minxc.emp.common.db.id;

import org.minxc.emp.common.db.api.IdGenerator;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 雪花算法Id生成器
 *
 */

@Slf4j
public class SnowflakeIdGenerator implements IdGenerator {

    private SnowflakeIdMetadata idMeta;

    public SnowflakeIdGenerator(SnowflakeIdMetadata idMeta) {
        Assert.notNull(idMeta, "idMeta is not null");
        this.idMeta = idMeta;
    }

    private static class Variant {

        long sequence;

        long lastTimestamp = -1;

    }

    private AtomicReference<Variant> variant = new AtomicReference<>(new Variant());


    private long tillNextTimestamp(final long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp < lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    @Override
    public Long getUId() {
        Variant oldVariant, newVariant;

        while (true) {
            oldVariant = variant.get();

            final long lastTimestamp = oldVariant.lastTimestamp;

            long timestamp = tillNextTimestamp(lastTimestamp);
            long sequence = oldVariant.sequence;

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & idMeta.getSequenceMask();
                if (sequence == 0) {
                    timestamp = tillNextTimestamp(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            newVariant = new Variant();
            newVariant.sequence = sequence;
            newVariant.lastTimestamp = timestamp;

            if (variant.compareAndSet(oldVariant, newVariant)) {
                break;
            }
        }
        long uid = idMeta.getMachine();
        uid |= newVariant.sequence << idMeta.getSequenceStartPos();
        uid |= newVariant.lastTimestamp << idMeta.getTimeStartPos();
        log.debug("++++++++SnowflakeIdGenerator++++++++getUId():" + uid);
        return uid;
    }

    @Override
    public String getSuid() {

        return Long.toString(getUId());
    }
}
