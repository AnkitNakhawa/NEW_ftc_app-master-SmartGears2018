package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Pushbot Autnomous Program", group = "Pushbot")

public class basicAutonomousMode_Abhisar extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;


    Servo leftClaw;
    Servo rightClaw;

    @Override
    public void runOpMode () throws InterruptedException{
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        arm = hardwareMap.dcMotor.get("arm");

        leftClaw = hardwareMap.servo.get("left_claw");
        rightClaw = hardwareMap.servo.get("right_claw");

        waitForStart();

        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1, 1200);
        STOPtime(1, 1000);
        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1, 1200);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        DriveForwardTime(1, 2000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1,1200);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1, 1200);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        DriveForwardTime(1, 2000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1, 1200);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnRightTime(1, 1200);
        STOPtime(1, 1000);
        DriveForwardTime(1, 1000);
        STOPtime(1, 1000);
        //armDownTime(1, 100);
        //STOPtime(1, 1000);
        //armUpTime(1, 100);
        //STOPtime(1, 1000);
        TurnLeftTime(1, 2400);
        STOPtime(1, 1000);
        clawOpen();
        STOPtime(1, 1000);
        clawClose();
        STOPtime(1, 1000);






    }
    public void DriveForward (double power){
        leftDrive.setPower(1);
        rightDrive.setPower(-1);

    }

    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);
    }

    public void TurnLeft (double power){
        leftDrive.setPower(0);
        rightDrive.setPower(1);
    }

    public void TurnLeftTime(double power, long time) throws InterruptedException {
        TurnLeft(power);
        Thread.sleep(time);
    }

    public void TurnRight (double power){
        leftDrive.setPower(1);
        rightDrive.setPower(0);
    }

    public void TurnRightTime(double power, long time) throws InterruptedException {
        TurnRight(power);
        Thread.sleep(time);
    }

    public void armUp (double power){
        arm.setPower(1);
    }

    public void armUpTime(double power, long time) throws InterruptedException {
        armUp(power);
        Thread.sleep(time);
    }

    public void armDown (double power){
        arm.setPower(-1);
    }

    public void armDownTime(double power, long time) throws InterruptedException {
        armDown(power);
        Thread.sleep(time);
    }

    public void clawClose (){
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);
    }



    public void clawOpen (){
        leftClaw.setPosition(0);
        rightClaw.setPosition(1);
    }

    public void STOP (double power){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void STOPtime(double power, long time) throws InterruptedException {
        STOP(power);
        Thread.sleep(time);
    }
}