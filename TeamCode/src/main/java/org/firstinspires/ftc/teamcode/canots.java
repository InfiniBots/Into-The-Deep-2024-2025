package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;

    @TeleOp
    public class canots extends LinearOpMode {
        DcMotor frontLeftMotor;
        DcMotor frontRightMotor;
        DcMotor backLeftMotor;
        DcMotor backRightMotor;

        Servo left;

        @Override
        public void runOpMode() throws InterruptedException {
            frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
            backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
            backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
            frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
            left = hardwareMap.get(Servo.class, "left");




        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y), Math.max(Math.abs(x), Math.abs(rx)));

            double frontLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y + x - rx) / denominator;
            double backLeftPower = (y + x + rx) / denominator;
            double backRightPower = (y - x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            backLeftMotor.setPower(backLeftPower);


        }
    }
}
