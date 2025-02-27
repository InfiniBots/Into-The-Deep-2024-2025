package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class LimelightSampleTrackingOpMode extends LinearOpMode {

    private LimelightSampleTracking sampleTracker;
    private Limelight3A limelight;
    @Override
    public void runOpMode() throws InterruptedException {

            sampleTracker = new LimelightSampleTracking(hardwareMap, telemetry);

            limelight.pipelineSwitch(0);

            limelight.start();

            waitForStart();
            while (opModeIsActive()) {
            // Track and display the closest yellow sample
            sampleTracker.trackClosestYellowSample();
        }
    }
}



