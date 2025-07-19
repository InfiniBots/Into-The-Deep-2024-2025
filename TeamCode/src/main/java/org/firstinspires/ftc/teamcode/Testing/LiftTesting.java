package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp
public class LiftTesting extends OpMode  {
    public DcMotorEx rightLift;
    public DcMotorEx leftLift;

    @Override
    public void init() {
        rightLift  = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift  = hardwareMap.get(DcMotorEx.class, "leftLift");
        leftLift.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        rightLift.setPower(gamepad1.right_trigger); //up
        leftLift.setPower(gamepad1.right_trigger);  //up
        leftLift.setPower(gamepad1.left_trigger/-1); //down
        rightLift.setPower(gamepad1.left_trigger/-1); //down

        telemetry.addData("RightLiftPos", rightLift.getCurrentPosition());
        telemetry.addData("LeftLiftPos", leftLift.getCurrentPosition());
        telemetry.addData("RightLiftPower", rightLift.getPower());
        telemetry.addData("LeftLiftPower", leftLift.getPower());
        telemetry.update();

    }
}
