package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystems.Extendo;

@Config
@TeleOp
public class ExtendoTesting extends OpMode  {
    public DcMotorEx Extendo;
    @Override
    public void init() {
        Extendo = hardwareMap.get(DcMotorEx.class, "Extendo");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        Extendo.setPower(gamepad1.right_trigger); //extend
        Extendo.setPower(gamepad1.left_trigger/-1); //retract

        telemetry.addData("RightLiftPos", Extendo.getCurrentPosition());
        telemetry.addData("RightLiftPower", Extendo.getPower());


    }
}
