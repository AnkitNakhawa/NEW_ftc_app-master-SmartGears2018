package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ArmOpTest", group = "pushbot")
//@disabled
public class SG_ArmTest extends LinearOpMode {
//

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;
    Servo leftClaw;
    Servo rightClaw;

    @Override
    public void runOpMode ()  throws InterruptedException{
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");



        int motorPower = 1;
        long motorTimeValue = 1000;

        waitForStart();



        ArmUp(0.5, 2000);
        ArmDown(0.5,2000);


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

    public void ArmUp(double motorPower, long motorTimeValue) throws InterruptedException{
        arm.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }

    public void ArmDown(double motorPower, long motorTimeValue) throws InterruptedException{
        arm.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }
    public void ServoOpen(){
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);

    }
    public void ServoClose(){
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);

    }
}

