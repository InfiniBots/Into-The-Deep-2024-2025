package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(group = "Random Tests")
public class IntakeTesting extends OpMode  {
    public DcMotorEx Intake;

    @Override
    public void init() {
        Intake  = hardwareMap.get(DcMotorEx.class, "Intake");
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }


    @Override
    public void loop() {
        Intake.setPower(gamepad1.left_stick_y);

        telemetry.addData("IntakePower", Intake.getPower());
        telemetry.update();

    }
}
