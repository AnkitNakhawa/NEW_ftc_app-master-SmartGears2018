package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Sensor OpMode Abhisar", group = "Pushbot")

public class SensorOpMode_Abhisar extends LinearOpMode {

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


        telemetry.addData("Left Servo ", leftClaw.getPosition());

        waitForStart();
        double servoposition;
        long x;

        servoposition = (double)leftClaw.getPosition()*50000;

        x = (new Double(servoposition)).longValue();

        DriveForwardTime(1, x);



    }
    public void DriveForward (double power){
        leftDrive.setPower(1);
        rightDrive.setPower(-1);

    }

    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);
    }

}