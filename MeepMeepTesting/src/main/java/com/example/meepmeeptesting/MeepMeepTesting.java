package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.security.cert.Extension;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 11.061975)
                .setDimensions(13.41339, 16)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-10, 59, Math.toRadians(-90)))

                .splineToLinearHeading(new Pose2d(-10.00, 32.00, Math.toRadians(-90.00)), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-10, 39), Math.toRadians(-90.00))
                .splineToLinearHeading(new Pose2d(-38.00, 34.00, Math.toRadians(215.00)), Math.toRadians(270.00))
                .splineToLinearHeading(new Pose2d(-38.00, 35.00, Math.toRadians(125.00)), Math.toRadians(125.00))
                .splineToLinearHeading(new Pose2d(-48.00, 34.00, Math.toRadians(210.00)), Math.toRadians(210.00))
                .splineToLinearHeading(new Pose2d(-48.00, 35.00, Math.toRadians(115.00)), Math.toRadians(115.00))
                .splineToLinearHeading(new Pose2d(-58.00, 34.00, Math.toRadians(208.00)), Math.toRadians(208.00))
                .splineToSplineHeading(new Pose2d(-58.00, 35.00, Math.toRadians(90.00)), Math.toRadians(90.00))
                .splineToSplineHeading(new Pose2d(-51.12, 47.43, Math.toRadians(-90.00)), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-9.50, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-10.00, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-10.00, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-10.00, 32.00), Math.toRadians(-90.00))




                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}