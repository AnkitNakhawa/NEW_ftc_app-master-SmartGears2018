package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp Mode Double Controller 181116", group="Linear Opmode")
//@Disabled
public class NewTeleOpFull_AnkitOLD extends LinearOpMode {


    // For Gamepad 1
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor rightWheelLift = null;
    private DcMotor leftWheelLift = null;
    private DcMotor liftArm = null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //For Gamepad 1
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rightWheelLift = hardwareMap.get(DcMotor.class, "right_wheel_lift");
        leftWheelLift = hardwareMap.get(DcMotor.class, "left_wheel_lift");
        liftArm = hardwareMap.get(DcMotor.class, "lift_arm");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");


        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        leftWheelLift.setDirection(DcMotor.Direction.REVERSE);
        leftClaw.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        runtime.reset();

        //gamepad 1
        while (opModeIsActive()) {
            double wheelLiftPowerPos = gamepad1.right_trigger;
            double wheelLiftPowerNeg = gamepad1.left_trigger;
            double leftDrivePower = gamepad1.left_stick_y;
            double rightDrivePower = gamepad1.right_stick_y;
            boolean slowLeftDrive = gamepad1.x;
            boolean slowRightDrive = gamepad1.b;
            boolean slowBack = gamepad1.a;
            boolean slowForward = gamepad1.y;
            boolean lClawBack = gamepad2.left_bumper;
            boolean rClawBack = gamepad2.right_bumper;
            double lClawForward = gamepad2.left_trigger;
            double rClawForward = gamepad2.right_trigger;

            double lClawPos = 0;
            double rClawPos = 0;

            //gamepad 2
            double liftArmPower = gamepad2.left_stick_y;

            //execution
            motorDrive(leftDrivePower, rightDrivePower);
            wheelFunctionPos(wheelLiftPowerPos);
            wheelFunctionNeg(wheelLiftPowerNeg);
            liftArmControl(liftArmPower);
            //slowMovements(slowLeftDrive, slowRightDrive, slowBack, slowForward);
            //clawMovement(lClawBack, rClawBack, lClawForward, rClawForward, lClawPos, rClawPos);
        }
    }

    public void motorDrive(double leftDrivePower, double rightDrivePower) {
        leftDrive.setPower(leftDrivePower);
        rightDrive.setPower(rightDrivePower);
    }

    public void wheelFunctionPos(double wheelLiftPowerPos) {
        rightWheelLift.setPower(wheelLiftPowerPos / 2);
        leftWheelLift.setPower(wheelLiftPowerPos / 2);
    }

    public void wheelFunctionNeg(double wheelLiftPowerNeg) {
        rightWheelLift.setPower(-wheelLiftPowerNeg / 2);
        leftWheelLift.setPower(-wheelLiftPowerNeg / 2);
    }

    public void liftArmControl(double liftArmPower) {
        liftArm.setPower(liftArmPower);
    }

    public void slowMovements(boolean slowLeftDrive, boolean slowRightDrive, boolean slowBack, boolean slowForward) {
        while (slowLeftDrive) {
            leftDrive.setPower(.1);
            rightDrive.setPower(-.1);
            break;
        }
        while (slowRightDrive) {
            leftDrive.setPower(-.1);
            rightDrive.setPower(.1);
            break;
        }

        while (slowBack) {
            leftDrive.setPower(-.1);
            rightDrive.setPower(-.1);
            break;
        }
        while (slowForward) {
            leftDrive.setPower(.1);
            rightDrive.setPower(.1);
            break;
        }


    }
    public void clawMovement (boolean lClawBack, boolean rClawBack, double lClawForward, double rClawForward, double lClawPos, double rClawPos){
        while (lClawBack && lClawPos >= 0){
            lClawPos = lClawPos -0.05;
            leftClaw.setPosition(lClawPos);
            break;
        }
        while (rClawBack && rClawPos >= 0){
            rClawPos = rClawPos -0.05;
            rightClaw.setPosition(rClawPos);
            break;
        }
        while (lClawForward >= 0 && lClawPos <= 1){
            lClawPos = lClawPos + 0.05;
            leftClaw.setPosition(lClawPos);
            break;
        }
        while (rClawForward >= 0 && rClawPos <= 1){
            rClawPos = lClawPos + 0.05;
            rightClaw.setPosition(rClawPos);
            break;
        }
    }

}

