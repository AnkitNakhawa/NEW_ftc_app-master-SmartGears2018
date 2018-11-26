package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "FinalOpMode_Smartgears", group = "pushbot")
//@disabled
public class FinalAutonomousOpMode_Smartgears extends LinearOpMode {
//

    DcMotor leftDrive;
    DcMotor rightDrive;
    Servo leftClaw;
    Servo rightClaw;
    DcMotor rightWheelLift;
    DcMotor leftWheelLift;
    DcMotor liftArm;
    DcMotor claimArm;
    DcMotor claimArmMotor;

    @Override
    public void runOpMode ()  throws InterruptedException{
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        rightWheelLift = hardwareMap.get(DcMotor.class, "right_wheel_lift");
        leftWheelLift = hardwareMap.get(DcMotor.class, "left_wheel_lift");
        liftArm = hardwareMap.get(DcMotor.class, "lift_arm");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        claimArm = hardwareMap.get(DcMotor.class, "claim_arm");
        claimArmMotor = hardwareMap.get(DcMotor.class, "carm_motor");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);




        int motorPower = 1;
        long motorTimeValue = 1000;

        waitForStart();

    Sampling();
    Claiming();



}
    public void MoveForward(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnRight(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnLeft(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void MoveBackward(double motorPower, long motorTimeValue) throws InterruptedException{
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void ServoOpen(){
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);

    }
    public void ServoClose(){
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);

    }
    public void Landing(){
//        telemetry.addData("Status", "Performing Landing Operation");
//        liftArm.setPower(1);
//        sleep(12000);
//        liftArm.setPower(0);
//        rightClaw.setPosition(0);
//        leftClaw.setPosition(0);
//        leftDrive.setPower(-1);
//        rightDrive.setPower(-1);
//        sleep(1500);
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//        telemetry.update();

    }
    public void Sampling() {
//        telemetry.addData("Status", "Performing Sampling Operation");
//        leftDrive.setPower(-1);
//        rightDrive.setPower(-1);
//        sleep(1000);
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//        telemetry.update();
        rightDrive.setPower(1);
        leftDrive.setPower(1);
        sleep(950);
        leftDrive.setPower(-0.25);
        rightDrive.setPower(-0.25);
        sleep(550);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        }
    public void Claiming(){
//         leftDrive.setPower(1);
//         sleep(700);
//         rightDrive.setPower(1);
//         sleep(1850);
//         rightDrive.setPower(0);
//         leftDrive.setPower(0);
//         claimArm.setPower(.7);
//         sleep(1200);
//       claimArm.setPower(0);
        rightDrive.setPower(1);
        leftDrive.setPower(-1);
        sleep(925);
        leftDrive.setPower(.7);
        sleep(1750);
        rightDrive.setPower(0);
        leftDrive.setPower(0);
    }
   public void Parking(){
    leftDrive.setPower(1);
    rightDrive.setPower(1);
    sleep(750);
       leftDrive.setPower(1);
       rightDrive.setPower(.95);
       sleep(2000);
       leftDrive.setPower(0);
       rightDrive.setPower(0);
    }
   public void armTest(){
//       claimArm.setPower(-1);
//       sleep(700);
//       claimArm.setPower(0);
//        claimArm.setPower(1);
//        sleep(700);
//        claimArm.setPower(0);
    }
}

//fina