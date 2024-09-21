package org.firstinspires.ftc.teamcode;
import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.Math;
@TeleOp
public class gusamder extends OpMode {

    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    @Override
    public void init() {

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");


    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontleftPower = (y + x + rx) / denominator;
        double backleftPower = (y - x + rx) / denominator;
        double frontrightPower = (y - x - rx) / denominator;
        double backrightPower = (y + x - rx) / denominator;

        frontRightMotor.setPower(frontrightPower);
        frontLeftMotor.setPower(frontleftPower);
        backLeftMotor.setPower(backleftPower);
        backRightMotor.setPower(backrightPower);



    }
}

