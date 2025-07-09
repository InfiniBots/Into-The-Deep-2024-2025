package org.firstinspires.ftc.teamcode.EncoderCrap;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.AnalogInput;

public class WristAnalogEncoder {
    private AnalogInput wristEncoder;
    public static final double MAX_VOLTAGE = 3.3;
    public static final double MIN_ANGLE = 60.0;
    public static final double MAX_ANGLE = 150.0;

    public WristAnalogEncoder(HardwareMap hardwareMap) {
        wristEncoder = hardwareMap.get(AnalogInput.class, "wristEncoder");
    }

    public double getAngle() {
        return (wristEncoder.getVoltage() / MAX_VOLTAGE) * 360.0;
    }

    public double getMappedServoPosition() {
        double angle = getAngle();
        angle = Math.max(MIN_ANGLE, Math.min(MAX_ANGLE, angle));
        return (angle - MIN_ANGLE) / (MAX_ANGLE - MIN_ANGLE);
    }
}
