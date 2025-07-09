package org.firstinspires.ftc.teamcode.EncoderCrap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.EncoderCrap.WristAnalogEncoder;

@TeleOp
public class WristEncoderTest extends OpMode {

    WristAnalogEncoder wristEncoder;

    @Override
    public void init() {
        wristEncoder = new WristAnalogEncoder(hardwareMap);
    }

    @Override
    public void loop() {
        double angle = wristEncoder.getAngle();
        telemetry.addData("Wrist Angle (degrees)", angle);
        telemetry.update();
    }
}
