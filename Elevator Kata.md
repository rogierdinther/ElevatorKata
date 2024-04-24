# Lift Kata
We moeten een controller gaan implementeren voor een liftsysteem in een kantoor. Dit systeem heeft één enkele lift en op elke verdieping een paneel met knoppen waarop de gebruiker kan selecteren naar welke verdieping hij/zij toe wil. De controller moet gaan bepalen welke route de lift aflegt.

De interface van de controller moet de volgende functies hebben. Hieraan kunnen we niets veranderen of toevoegen:
- `fun call(floor, destination)`
- `fun step()`

`call()` wordt aangeroepen door de panelen, en krijgt de verdieping mee waar het paneel staat, en de bestemming die is ingedrukt. Dit kan een willekeurig aantal keer gebeuren binnen één step.

`step()` wordt aangeroepen door een ingebouwde trigger in het liftsysteem waarover we geen controle hebben. De controller dient hierna zo nodig een commando uit te geven aan de lift om die te verplaatsen. Bij de volgende step mogen we ervan uitgaan dat alle liften hun verplaatsingen hebben gedaan.

Om de lift te bedienen heeft het liftsysteem de volgende interface die de controller kan aanroepen:
- `fun gtf(floor)`

`gtf()` stuurt de lift naar de gegeven verdieping en opent de deuren zodat mensen in en uit kunnen stappen.

**`gtf()` kan maar één keer worden aangeroepen per "stap". Meerdere keren aanroepen geeft mogelijk errors en is niet gewenst.**

## Opdracht
NOOT: Het is de bedoeling om dit in volgorde af te werken, en geen rekening te houden met wat er daarna komt.

1. Als de lift geen andere bestemming heeft, keert deze terug naar Verdieping 1. Anders gaat deze naar de dichtsbijzijnde verdieping waar iemand afgeleverd of opgehaald moet worden
2. Bij gelijke afstand wordt voorkeur gegeven aan het afleveren van mensen
3. Als iemand al 4 tijdseenheden wacht op een lift, wordt deze zo snel mogelijk opgehaald. Hierbij wordt voorkeur gegeven aan mensen die langer wachten
4. De lift kan maar 8 mensen bevatten

Het blijkt dat 1 lift niet voldoende is om het verkeer aan te kunnen in het kantoorgebouw. Daarom worden er extra liftschachten bij gebouwd. Het is echter door bouw en onderhoud niet altijd zeker welke van de nieuwe liften actief zijn. De eerste lift blijft wel altijd werken.

Om dit te ondersteunen heeft de leverancier van het liftsysteem een v2 versie van de interface aangesloten:
- `fun connectEvs(): List<LiftId>`
- `fun gtf(id, floor)`

(De v1 versie blijft ook bestaan, maar bedient alleen de eerste lift)

`connectEvs()` geeft een lijst terug van IDs van de actieve liften. Er zit geen volgorde in deze IDs. De eerste is het ID van de eerste lift.

`gtf()` stuurt de lift met het gegeven ID naar de gegeven verdieping. **Wederom kan deze per lift maar 1 keer worden aangeroepen per stap.**

5. In de situatie waar alleen de eerste lift werkt, blijft de werking als voorheen
6. De lift die het dichtst bij is, haalt mensen op mits dat mogelijk is. Bij gelijke afstand heeft de lift met minder opdrachten voorkeur

Vanuit wetgeving moet de brandweer speciale bevoegdheden hebben om het systeem in nood te omzeilen. Er komt een nieuwe knop naast de lift die alleen de brandweer kan bedienen. Deze roept de functie aan:
- `fun override(floor)`

7. De lift die het dichtst bij is, dient naar deze verdieping te gaan. Na aankomst daar is de lift niet meer inzetbaar door de controller en moet deze verder alsof er een lift minder is.