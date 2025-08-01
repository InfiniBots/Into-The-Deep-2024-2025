package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.reflection.ReflectionConfig;
import com.acmerobotics.roadrunner.MotorFeedforward;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.*;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.tuning.otos.OTOSHeadingOffsetTuner;
import org.firstinspires.ftc.teamcode.tuning.otos.OTOSPositionOffsetTuner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TuningOpModes {
    public static final Class<?> DRIVE_CLASS = PinpointDrive.class;

    public static final String GROUP = "quickstart";
    public static final boolean DISABLED = false;

    private TuningOpModes() {}

    private static OpModeMeta metaForClass(Class<? extends OpMode> cls) {
        return new OpModeMeta.Builder()
                .setName(cls.getSimpleName())
                .setGroup(GROUP)
                .setFlavor(OpModeMeta.Flavor.TELEOP)
                .build();
    }

    @OpModeRegistrar
    public static void register(OpModeManager manager) {
        if (DISABLED) return;

        DriveViewFactory dvf;
        if (DRIVE_CLASS.equals(OctoQuadDrive.class)) {
            dvf = hardwareMap -> {
                OctoQuadDrive drive = new OctoQuadDrive(hardwareMap, new Pose2d(0, 0, 0));

                List<Encoder> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<Encoder> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                parEncs.add(new LocalizationSensorEncoder(drive.localSensor, false, drive.leftBack));
                perpEncs.add(new LocalizationSensorEncoder(drive.localSensor, true, drive.leftBack));

                return new DriveView(
                        DriveType.MECANUM,
                        MecanumDrive.PARAMS.inPerTick,
                        MecanumDrive.PARAMS.maxWheelVel,
                        MecanumDrive.PARAMS.minProfileAccel,
                        MecanumDrive.PARAMS.maxProfileAccel,
                        hardwareMap.getAll(LynxModule.class),
                        Arrays.asList(
                                drive.leftFront,
                                drive.leftBack
                        ),
                        Arrays.asList(
                                drive.rightFront,
                                drive.rightBack
                        ),
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        drive.lazyImu,
                        drive.voltageSensor,
                        () -> new MotorFeedforward(MecanumDrive.PARAMS.kS,
                                MecanumDrive.PARAMS.kV / MecanumDrive.PARAMS.inPerTick,
                                MecanumDrive.PARAMS.kA / MecanumDrive.PARAMS.inPerTick)
                );
            };

        } else if (DRIVE_CLASS.equals(PinpointDrive.class)) {
                dvf = hardwareMap -> {
                    PinpointDrive pd = new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0));

                    List<Encoder> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                    List<Encoder> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                    parEncs.add(new PinpointEncoder(pd.pinpoint,false, pd.leftBack));
                    perpEncs.add(new PinpointEncoder(pd.pinpoint,true, pd.leftBack));

                    return new DriveView(
                            DriveType.MECANUM,
                            MecanumDrive.PARAMS.inPerTick,
                            MecanumDrive.PARAMS.maxWheelVel,
                            MecanumDrive.PARAMS.minProfileAccel,
                            MecanumDrive.PARAMS.maxProfileAccel,
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(
                                    pd.leftFront,
                                    pd.leftBack
                            ),
                            Arrays.asList(
                                    pd.rightFront,
                                    pd.rightBack
                            ),
                            leftEncs,
                            rightEncs,
                            parEncs,
                            perpEncs,
                            pd.lazyImu,
                            pd.voltageSensor,
                            () -> new MotorFeedforward(MecanumDrive.PARAMS.kS,
                                    MecanumDrive.PARAMS.kV / MecanumDrive.PARAMS.inPerTick,
                                    MecanumDrive.PARAMS.kA / MecanumDrive.PARAMS.inPerTick)
                    );
                };
        } else if (DRIVE_CLASS.equals(SparkFunOTOSDrive.class)) {
            dvf = hardwareMap -> {
                SparkFunOTOSDrive od = new SparkFunOTOSDrive(hardwareMap, new Pose2d(0, 0, 0));

                List<Encoder> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<Encoder> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                parEncs.add(new OtosEncoder(od.otos,false,false, od.leftBack));
                perpEncs.add(new OtosEncoder(od.otos,true,false, od.leftBack));

                return new DriveView(
                        DriveType.MECANUM,
                        MecanumDrive.PARAMS.inPerTick,
                        MecanumDrive.PARAMS.maxWheelVel,
                        MecanumDrive.PARAMS.minProfileAccel,
                        MecanumDrive.PARAMS.maxProfileAccel,
                        hardwareMap.getAll(LynxModule.class),
                        Arrays.asList(
                                od.leftFront,
                                od.leftBack
                        ),
                        Arrays.asList(
                                od.rightFront,
                                od.rightBack
                        ),
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        od.lazyImu,
                        od.voltageSensor,
                        () -> new MotorFeedforward(MecanumDrive.PARAMS.kS,
                                MecanumDrive.PARAMS.kV / MecanumDrive.PARAMS.inPerTick,
                                MecanumDrive.PARAMS.kA / MecanumDrive.PARAMS.inPerTick)
                );
            };

            // register OTOS-specific tuning opmodes
            manager.register(metaForClass(OTOSHeadingOffsetTuner.class), OTOSHeadingOffsetTuner.class);
            manager.register(metaForClass(OTOSPositionOffsetTuner.class), OTOSPositionOffsetTuner.class);
        } else if (DRIVE_CLASS.equals(MecanumDrive.class)) {
            dvf = hardwareMap -> {
                MecanumDrive md = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

                List<Encoder> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<Encoder> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                if (md.localizer instanceof MecanumDrive.DriveLocalizer) {
                    MecanumDrive.DriveLocalizer dl = (MecanumDrive.DriveLocalizer) md.localizer;
                    leftEncs.add(dl.leftFront);
                    leftEncs.add(dl.leftBack);
                    rightEncs.add(dl.rightFront);
                    rightEncs.add(dl.rightBack);
                } else if (md.localizer instanceof ThreeDeadWheelLocalizer) {
                    ThreeDeadWheelLocalizer dl = (ThreeDeadWheelLocalizer) md.localizer;
                    parEncs.add(dl.par0);
                    parEncs.add(dl.par1);
                    perpEncs.add(dl.perp);
                } else if (md.localizer instanceof TwoDeadWheelLocalizer) {
                    TwoDeadWheelLocalizer dl = (TwoDeadWheelLocalizer) md.localizer;
                    parEncs.add(dl.par);
                    perpEncs.add(dl.perp);
                } else {
                    throw new RuntimeException("unknown localizer: " + md.localizer.getClass().getName());
                }

                return new DriveView(
                    DriveType.MECANUM,
                        MecanumDrive.PARAMS.inPerTick,
                        MecanumDrive.PARAMS.maxWheelVel,
                        MecanumDrive.PARAMS.minProfileAccel,
                        MecanumDrive.PARAMS.maxProfileAccel,
                        hardwareMap.getAll(LynxModule.class),
                        Arrays.asList(
                                md.leftFront,
                                md.leftBack
                        ),
                        Arrays.asList(
                                md.rightFront,
                                md.rightBack
                        ),
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        md.lazyImu,
                        md.voltageSensor,
                        () -> new MotorFeedforward(MecanumDrive.PARAMS.kS,
                                MecanumDrive.PARAMS.kV / MecanumDrive.PARAMS.inPerTick,
                                MecanumDrive.PARAMS.kA / MecanumDrive.PARAMS.inPerTick)
                );
            };
        } else if (DRIVE_CLASS.equals(TankDrive.class)) {
            dvf = hardwareMap -> {
                TankDrive td = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

                List<Encoder> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<Encoder> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                if (td.localizer instanceof TankDrive.DriveLocalizer) {
                    TankDrive.DriveLocalizer dl = (TankDrive.DriveLocalizer) td.localizer;
                    leftEncs.addAll(dl.leftEncs);
                    rightEncs.addAll(dl.rightEncs);
                } else if (td.localizer instanceof ThreeDeadWheelLocalizer) {
                    ThreeDeadWheelLocalizer dl = (ThreeDeadWheelLocalizer) td.localizer;
                    parEncs.add(dl.par0);
                    parEncs.add(dl.par1);
                    perpEncs.add(dl.perp);
                } else if (td.localizer instanceof TwoDeadWheelLocalizer) {
                    TwoDeadWheelLocalizer dl = (TwoDeadWheelLocalizer) td.localizer;
                    parEncs.add(dl.par);
                    perpEncs.add(dl.perp);
                } else {
                    throw new RuntimeException("unknown localizer: " + td.localizer.getClass().getName());
                }

                return new DriveView(
                    DriveType.TANK,
                        TankDrive.PARAMS.inPerTick,
                        TankDrive.PARAMS.maxWheelVel,
                        TankDrive.PARAMS.minProfileAccel,
                        TankDrive.PARAMS.maxProfileAccel,
                        hardwareMap.getAll(LynxModule.class),
                        td.leftMotors,
                        td.rightMotors,
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        td.lazyImu,
                        td.voltageSensor,
                        () -> new MotorFeedforward(TankDrive.PARAMS.kS,
                                TankDrive.PARAMS.kV / TankDrive.PARAMS.inPerTick,
                                TankDrive.PARAMS.kA / TankDrive.PARAMS.inPerTick)
                );
            };
        } else {
            throw new RuntimeException();
        }

        manager.register(metaForClass(AngularRampLogger.class), new AngularRampLogger(dvf));
        manager.register(metaForClass(ForwardPushTest.class), new ForwardPushTest(dvf));
        manager.register(metaForClass(ForwardRampLogger.class), new ForwardRampLogger(dvf));
        manager.register(metaForClass(LateralPushTest.class), new LateralPushTest(dvf));
        manager.register(metaForClass(LateralRampLogger.class), new LateralRampLogger(dvf));
        manager.register(metaForClass(ManualFeedforwardTuner.class), new ManualFeedforwardTuner(dvf));
        manager.register(metaForClass(MecanumMotorDirectionDebugger.class), new MecanumMotorDirectionDebugger(dvf));
        manager.register(metaForClass(DeadWheelDirectionDebugger.class), new DeadWheelDirectionDebugger(dvf));

        manager.register(metaForClass(ManualFeedbackTuner.class), ManualFeedbackTuner.class);
        manager.register(metaForClass(SplineTest.class), SplineTest.class);
        manager.register(metaForClass(LocalizationTest.class), LocalizationTest.class);

        manager.register(metaForClass(AngularScalarTuner.class), AngularScalarTuner.class);

        FtcDashboard.getInstance().withConfigRoot(configRoot -> {
            for (Class<?> c : Arrays.asList(
                    AngularRampLogger.class,
                    ForwardRampLogger.class,
                    LateralRampLogger.class,
                    ManualFeedforwardTuner.class,
                    MecanumMotorDirectionDebugger.class,
                    ManualFeedbackTuner.class
            )) {
                configRoot.putVariable(c.getSimpleName(), ReflectionConfig.createVariableFromClass(c));
            }
        });
    }
}
