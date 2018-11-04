package org.firstinspires.ftc.teamcode;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous(name = "ColorOpMode_Ankit", group = "pushbot")
//@disabled
public class ColorSensorOpMode_Ankit extends LinearOpMode {


    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor arm;
    Servo leftClaw;
    Servo rightClaw;
    ColorSensor colorSensor;
    DistanceSensor distanceSensor;
    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        colorSensor = hardwareMap.get(ColorSensor.class, "ColorSensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "ColorSensor");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        int motorPower = 1;
        long motorTimeValue = 1000;
        waitForStart();


        while (opModeIsActive()) {
            detectColor();
        }

    }

    public void MoveForward(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnRight(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnLeft(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void MoveBackward(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void ArmUp(double motorPower, long motorTimeValue) throws InterruptedException {
        arm.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }
    //.

    public void ArmDown(double motorPower, long motorTimeValue) throws InterruptedException {
        arm.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        arm.setPower(0);
    }

    public void ServoOpen() {
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);

    }

    public void ServoClose() {
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);

    }

    public void readColor(){
        Color.RGBToHSV((int) (colorSensor.red() * SCALE_FACTOR),
                (int) (colorSensor.green() * SCALE_FACTOR),
                (int) (colorSensor.blue() * SCALE_FACTOR),
                hsvValues);

        // send the info back to driver station using telemetry function.
        telemetry.addData("Distance (cm)",
                String.format(Locale.US, "%.02f", distanceSensor.getDistance(DistanceUnit.CM)));
        telemetry.addData("Alpha", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.update();
    }

    public void senseColor(){
        //Saturation is more than or equal to .6, Hue Greater than between 210-275 for blue, 40 to 70 for yellow
        int rgbValue = colorSensor.argb();
        int AlphaVal = colorSensor.alpha();
        telemetry.addData("AlphaValue", AlphaVal);
        telemetry.addData("rgbHueValue",rgbValue);
        telemetry.update();

//        if (rgbValue >= 40 && rgbValue <= 70) {
//            leftDrive.setPower(0);
//            rightDrive.setPower(0);
//        }
    }
    public void detectColor (){
//        if (colorSensor.red() >= 80 && colorSensor.green() >= 80){
//            telemetry.addData("The Color Is ", "Yellow");
//            telemetry.update();
//        }
//        if (colorSensor.red() <= 80 && colorSensor.green() <= 80){
//            telemetry.addData("The Color Is ", "White");
//            telemetry.update();
//        }
        if (hsvValues[0]<= 30 /*&& distanceSensor.getDistance(DistanceUnit.CM)<= 6)*/){
            telemetry.addData("The Color Is ", "Yellow");
            telemetry.addData("Value", hsvValues[0]);
            telemetry.update();

        }
        if (hsvValues[0]>=31 /*&& distanceSensor.getDistance(DistanceUnit.CM)<= 6*/){

            telemetry.addData("The Color Is ", "White");
            telemetry.update();
        }




    }

}
