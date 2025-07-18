package org.firstinspires.ftc.teamcode.Roadrunner.tuning.otos;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.SparkFunOTOSDrive;

public class OTOSHeadingOffsetTuner extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0,0,0));
        telemetry.addLine("OTOS Heading Offset Tuner");
        telemetry.addLine("Line the side of the robot against a wall and Press START.");
        telemetry.addLine("Then push the robot forward some distance.");
        telemetry.addLine("Finally, copy the heading offset into line 38 of SparkFunOTOSDrive");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            drive.updatePoseEstimate();
            telemetry.addData("Heading Offset (radians, enter this one into SparkFunOTOSDrive!)",Math.atan2(drive.pose.position.y,drive.pose.position.x));
            telemetry.addData("Heading Offset (degrees)",Math.toDegrees(Math.atan2(drive.pose.position.y,drive.pose.position.x)));
            telemetry.update();
        }


    }
}
