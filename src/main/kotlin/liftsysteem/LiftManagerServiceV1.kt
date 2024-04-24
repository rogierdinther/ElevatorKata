package liftsysteem

import LiftSystem

// De implementatie mag niet veranderd worden. De signature (naam en inheritance) van de class wel.
class LiftManagerServiceV1 : LiftSystem {
    override fun gtf(floor: Int) {
        println("Lift naar Verdieping $floor")
    }
}