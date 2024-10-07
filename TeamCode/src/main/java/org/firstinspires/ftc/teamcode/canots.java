package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class canots extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    Servo Servo1;

    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");

        int cants;
        cants = 1;


    }

    @Override
    public void loop() {

    double x = gamepad1.left_stick_x;
    double y = -gamepad1.left_stick_y;
    double z = gamepad1.right_stick_x;

    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(z), 1);
    double frontLeftPower = (y + x + z) / denominator;
    double frontRightPower = (y - x - z) / denominator;
    double backLeftPower = (y - x + z) / denominator;
    double backRightPower = (y + x + - z) / denominator;

    frontRightMotor.setPower(frontRightPower);
    frontLeftMotor.setPower(frontLeftPower/-1);
    backLeftMotor.setPower(backLeftPower/-1);
    backRightMotor.setPower(backRightPower);

    }
}
