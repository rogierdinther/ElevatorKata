import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.collections.mutableListOf
import LiftController

class LiftControllerTest {
    val liftSystem = FakeLiftSystem()
    val controller = LiftController(liftSystem)

    @Test
    fun `When the lift has no assignments, it stays on floor 1`() {
        controller.step()
        
        assertEquals(1, liftSystem.numberOfCalls)
    }

    @Test
    fun `When someone presses the button, the lift goes to that floor`() {
        controller.call(3, 6)
        controller.step()

        assertEquals(listOf(1, 3), liftSystem.calls)
    }

    @Test
    fun `When no other buttons were pressed, the lift drops off the passenger`() {
        controller.call(3, 6)
        steps(2)

        assertEquals(listOf(1, 3, 6), liftSystem.calls)
    }

    @Test
    fun `When no other buttons were pressed after dropoff, the lift returns to floor 1`() {
        controller.call(3, 6)
        steps(3)

        assertEquals(listOf(1, 3, 6, 1), liftSystem.calls)
    }

    @Test
    fun `When controller is initialized, lift is sent to floor 1`() {
        assertEquals(listOf(1), liftSystem.calls)
    }

    @Test
    fun `When the lift is called from two floors, the lift is sent to the nearest floor`() {
        controller.call(3, 6)
        controller.call(2, 6)

        steps(1)

        assertEquals(listOf(1, 2), liftSystem.calls)
    }

    @Test 
    fun `When the lift is called twice from the same floor, that floor is visited only once`() {
        controller.call(3, 6)
        controller.call(3, 6)

        steps(2)

        assertEquals(listOf(1, 3, 6), liftSystem.calls)
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