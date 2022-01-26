package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDNumbers.*;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class LEDS extends SubsystemBase {

    CANdle candle;
    Color ballColor;
    ColorSensorV3 colorSensor;
    I2C.Port i2cPort;
    ColorMatch m_colorMatcher = new ColorMatch();

    public LEDS() {
        i2cPort = I2C.Port.kOnboard;
        ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
        ballColor = colorSensor.getColor();
        SmartDashboard.putNumber("Red", ballColor.red);
        SmartDashboard.putNumber("Blue", ballColor.blue);

        candle = new CANdle(0);
        CANdleConfiguration config = new CANdleConfiguration();
        config.stripType = LEDStripType.RGB;
        candle.configAllSettings(config);

        //sets the leds on the canfle itself to white
        candle.setLEDs(0, 0, 0, 255, 0, 200);

}
}