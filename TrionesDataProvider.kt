import java.awt.Color
import java.util.UUID

enum class PowerStatus{
    ON,
    OFF,
}

class TrionesDataProvider{
    val serviceUUID = UUID.fromString("0000ffd5-0000-1000-8000-00805f9b34fb")
    val characteristicUUID = UUID.fromString("0000ffd9-0000-1000-8000-00805f9b34fb")

    /**
     * Makes characteristic values to set the power status of the lights.
     * @param powerStatus Enum value that can ON or OFF
     */

    fun makePowerData(powerStatus: PowerStatus) = byteArrayOf(
        0xCC.toByte(),
        if (powerStatus == PowerStatus.ON) 0x23.toByte() else 0x24.toByte(),
        0x33.toByte(),
    )


    /**
     * Makes characteristic values to set the colour of the lights. This function is also used to control brightness, by
     * sending darker/lighter colours.
     * @param color The colour to set.
     */
    fun makeColorData(color: Color) = byteArrayOf(
        0x56.toByte(),
        color.red.toByte(),
        color.green.toByte(),
        color.blue.toByte(),
        0x00.toByte(),
        0xF0.toByte(),
        0xAA.toByte(),
    )

    /**
     * Makes characteristic values to set the pattern of the lights. With these light both pattern and speed are
     * set at the same time.
     * @param pattern The pattern to set, see PatternListProvider.kt for pattern indices.
     * @param speedPercent The speed to set expressed as a percentage.
     */
    fun makePatternData(pattern: Pattern, speedPercent: Int) = byteArrayOf(
        0xBB.toByte(),
        pattern.index.toByte(),
        getSpeedValue(percent = speedPercent).toByte(),
        0x44.toByte(),
    )

    private fun getSpeedValue(percent: Int): Int{
        var speed = (percent / 100f) * 31f
        speed.coerceIn(0f..30f)
        speed = 31 - speed
        return speed.toInt()
    }


    /**
     *  This is sent every 100ms and appears to just send a random colour.
     *  @param color The colour to set.
     */
    fun makeMusicData(color: Color) = byteArrayOf(
        0x78.toByte(),
        color.red.toByte(),
        color.green.toByte(),
        color.blue.toByte(),
        0x00.toByte(),
        0xF0.toByte(),
        0xEE.toByte(),
    )

}