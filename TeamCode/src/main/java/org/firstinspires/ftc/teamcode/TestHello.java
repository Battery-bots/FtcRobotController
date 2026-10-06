package org.firstinspires.ftc.teamcode;

// imports code from first inspires package/SDK
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
// Test to display hello world on control hub
public class TestHello extends OpMode{ //TestHello is part of the OpMode parent class

    @Override // means we are overriding the initialize method in OpMode class with this method
    public void init() {
        telemetry.addData("Hello", "World");
    }

    @Override
    public void loop(){

    }

}
