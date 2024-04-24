import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.collections.mutableListOf
import LiftController

class LiftControllerTest {
    val liftSystem = FakeLiftSystem()
    val controller = LiftController(liftSystem)

    @Test
    fun `When the lift has no assignments, it stays on floor 1`() {
        // Aanname: De lift begint op Verdieping 1

        controller.step()
        
        assertEquals(0, liftSystem.numberOfCalls)
    }

    @Test
    fun `When someone presses the button, the lift goes to that floor`() {
        controller.call(3, 6)
        controller.step()

        assertEquals(listOf(3), liftSystem.calls)
    }

    @Test
    fun `When no other buttons were pressed, the lift drops off the passenger`() {
        controller.call(3, 6)
        steps(2)

        assertEquals(listOf(3, 6), liftSystem.calls)
    }

    @Test
    fun `When no other buttons were pressed after dropoff, the lift returns to floor 1`() {
        controller.call(3, 6)
        steps(3)

        assertEquals(listOf(3, 6, 1), liftSystem.calls)
    }

    private fun steps(n: Int) {
        repeat(n) {
            controller.step()
        }
    }

    class FakeLiftSystem : LiftSystem {
        var numberOfCalls: Int = 0
        val calls: MutableList<Int> = mutableListOf()

        override fun gtf(floor: Int) {
            numberOfCalls++
            calls += floor
        }
        
    }
}