package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Diddy extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    CRServo CRServo1;

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
        double turn = gamepad1.right_stick_x;

        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);

        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double frontLeft = power * cos / max + turn;
        double frontRight = power * sin / max - turn;
        double backLeft = power * sin / max + turn;
        double backRight = power * cos / max - turn;

        if ((power + Math.abs(turn)) > 1) {
            frontLeft /= power + Math.abs(turn);
            frontRight /= power + Math.abs(turn);
            backLeft /= power + Math.abs(turn);
            backRight /= power + Math.abs(turn);
        }

        frontLeftMotor.setPower(frontLeft/-1); /* hi cants*/
        backLeftMotor.setPower(backLeft/-1);
        frontRightMotor.setPower(frontRight);
        backRightMotor.setPower(backRight);

        CRServo1.setPower(gamepad1.right_trigger);


    }
}

