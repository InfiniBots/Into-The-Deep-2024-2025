package org.firstinspires.ftc.teamcode;
import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

        frontLeftMotor.setPower(1);
        sleep(100);
        frontLeftMotor.setPower(0);
    }

    @Override
    public void loop() {

    }
}
