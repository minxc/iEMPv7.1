package org.minxc.emp.common.db.id;

/**
 * 雪花算法Id元信息
 *
 * @author wacxhs
 */
public class SnowflakeIdMetadata {

    private long machine;

    private byte machineBits;

    private byte sequenceBits;

    private byte timeSequence;

    public SnowflakeIdMetadata(long machine, byte machineBits, byte sequenceBits, byte timeSequence) {
        this.machine = machine;
        this.machineBits = machineBits;
        this.sequenceBits = sequenceBits;
        this.timeSequence = timeSequence;
    }

    public long getMachine() {
        return machine;
    }

    public byte getMachineBits() {
        return machineBits;
    }

    public byte getSequenceBits() {
        return sequenceBits;
    }

    public byte getTimeSequence() {
        return timeSequence;
    }

    public long getSequenceMask(){

        return -1 ^ -1 << sequenceBits;
    }

    public long getSequenceStartPos(){

        return machineBits;
    }

    public long getTimeStartPos(){

        return machineBits + sequenceBits;
    }
}
