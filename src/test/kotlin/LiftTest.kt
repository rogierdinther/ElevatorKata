import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertEquals
import kotlin.test.assertContentEquals
import LiftController

class LiftTest {
    val liftSystem = FakeLiftSystem()
    val controller = LiftController(liftSystem)

    @Test
    fun `When lift has no assignments, it stays on floor 1`() {
        controller.step()

        assertEquals(0, liftSystem.numberOfCalls)
    }

    @Test
    fun `When passenger presses button, lift goes to that floor for pickup`() {
        controller.call(3, 6)
        controller.step()

        assertContentEquals(listOf(3), liftSystem.calls)
    }

    @Test
    fun `When no other buttons are pressed, lift delivers passenger to floor`() {
        controller.call(3, 6)
        controller.step()
        controller.step()

        assertContentEquals(listOf(3, 6), liftSystem.calls)
    }

    // @Test
    // fun `When there is one pickup and one dropoff, lift goes to the nearer pickup`() {
    //     val liftSystem = FakeLiftSystem()
    //     val controller = LiftController(liftSystem)

    //     controller.call(3, 6)
    //     controller.step()
    //     controller.call(2, 4)
    //     controller.step()

    //     assertContentEquals(listOf(3, 2), liftSystem.calls)
    // }

    class FakeLiftSystem : LiftSystem {
        var numberOfCalls: Int = 0
        val calls: MutableList<Int> = mutableListOf()

        override fun gtf(floor: Int) {
            numberOfCalls++
            calls += floor
        }
    }
}