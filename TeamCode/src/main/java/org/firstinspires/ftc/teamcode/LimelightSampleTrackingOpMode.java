package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LimelightSampleTrackingOpMode extends LinearOpMode {

    private LimelightSampleTracking sampleTracker;
    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {

        sampleTracker = new LimelightSampleTracking(hardwareMap, telemetry);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

        waitForStart();
        while (opModeIsActive()) {
            sampleTracker.trackClosestYellowSample();
            if (sampleTracker.getClosestYellowSample() != null) {
                telemetry.addData("Sample Status", "Locked");
            } else {
                telemetry.addData("Sample Status", "Searching");
            }
            telemetry.update();
        }
    }
}