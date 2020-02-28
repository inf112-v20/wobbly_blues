##Oppgave 2

###Deloppgave 1: Prosjekt og prosjektstruktur

Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt? 

    Rollene fungerer bra, og passer med hvordan hver person er: Hans er glad i å teste, Tines har innsikt i å lage slike prosjekter, Sindre er en god koder, og Torstein kan bidra som kundekontakt

Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.

    Team lead: Ta de endelige valgene på hvilke teknologier vi bestemmer oss for å bruke. Også ta valgene på prosjektstrukturen
    Tester: Lage JUnit tester og generelt sjekke at koden fungerer, passe på at god kode blir skrevet, lete etter bugs
    Utvikler: Setter seg ned for å finne ut måter for å forbedre koden, og måter å gå frem på.
    Kundekontakt: Ikke så mye frem til nå.

    Vi tenker vi må ha en ny rolle til Torstein, ettersom kundekontakt ikke har noen oppgaver nå. Torstein kan også være utvikler


Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på? 

    Det har definitivt vært litt uorden nå, mye diskusjon om hvordan brettet skal implementeres (bruk av TiledMap?) Grunnet dette har det også virket vanskelig å få i gang prosjektmetodikken og bruke Trello, ettersom det føltes ut som alt var avhengig av hvordan Map ville fungere. 
    Akkurat nå har vi møte rett etter gruppetimen på fredag. Det fører til at det er langt mellom hver gruppemøte, det hadde vært bedre om vi fikk flyttet møtet til midt i mellom de obligatoriske møtene for å holde jevn kontakt og arbeid.

Hvordan er gruppedynamikken?

    Gruppen er mer sammensveiset nå, det hjalp å spille roborally sammen. Selv om Tines er team lead så innebærer det mer at han gir noen gode råd til tider i stedet for å styre gruppa. Alle er med på å bestemme veien videre, og oftest er det enighet i gruppa. 

Hvordan fungerer kommunikasjonen for dere?

    Den startet ikke så bra, men har forbedret seg. Vi er mye mer effektive når vi jobber sammen på gruppemøter, ettersom det ofte blir mye venting på at folk ser meldingen på slack. Det har også vært problemer med å få notifications på Slack.

Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.

    Til nå har vi klart å legge et godt grunnlag for resten av prosjektet. Noen ganger har det vært usikkerhet om veien videre. Vi fikk heller ikke utnyttet alle gruppemedlemmet til sitt fulle potensiale, da det ble mye venting på å finne ut hvordan grunnlaget skulle legges, før vi kunne starte å dele opp arbeidet. Trello har ikke blitt brukt mye og har rett og slett blitt kronglete pga de ulike ideene vi har
    drøftet 


Under vurdering vil det vektlegges at alle bidrar til kode basen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også design filer)

    Gruppen har jobbet mye under gruppetimene og det er ofte at alle gruppemedlemmer har vært med på å committee fra én pc.
    Ellers har Sindre bidratt masse til første innlevering, siden han hadde en del fritid, men til andre innlevering var det litt mindre siden han var ute og reiste.  Torstein har mindre erfaring i koding og latt resten gjøre en del av kodingen, men han har bidratt med mye god innsikt. Han er også hodet bak den nåværende card klassen.
    Hans og Tines har jobbet stødig gjennom begge innleveringer, Hans har jobbet med å ordne tester, mens Tines har lagd en Map klasse.


Referat fra møter siden forrige leveranse skal legges ved. √
   
    Her er link til referat: https://docs.google.com/document/d/1HTsOQscUkfT4tq2_VW9Ry48AhzZkqqJ7gC-Pz8Wz4aU/edit?usp=sharing

Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.

    Bli bedre til å fordele arbeid
    Bedre til å bruke Trello, vi vurderer å 
    Bli bedre til å arrangere møter

###Deloppgave 2: krav

For hvert krav dere jobber med, må dere lage 
1) ordentlige brukerhistorier
2) akseptansekriterier
3) arbeidsoppgaver 
Husk at akseptansekriterier ofte skrives mer eller mindre som tester


    Krav fremover:
        Implimentere ulike GUI’er som representerer meny, spill, og andre ting
    Brukerhistorier:
        Som spiller trenger jeg en meny for å velge 
    Akseptansekriterer:
        Klare å skille mellom menyen og spillet
        Klare å skille mellom de forskjellige menyvalgene 
    Arbeidsoppgaver:
        Lage primitive scener
        Lage turn handler

    Implementere en form for kort
    Brukerhistorier:
        Som spiller trenger jeg kort slik at jeg kan styre roboten min
        Som robot trenger jeg kort slik at jeg kan bevege meg i henhold til spillereglene
    Akseptansekriterier:
        Kort skal ha en innvirkning på en robot
        Kort skal ha en spesifikk instruksjon
    Arbeidsoppgaver
        Implementere en abstrakt klasse som kan initialiseres som et hvilket som helst kort fra det ekte spille
        Lage turn handler som kan flytte brikkene basert på kortenes effekt

Forklar kort hvordan dere har prioritert oppgavene fremover
    
    Turn Handler er prioritet, det er viktig å få logikken i spillet til å fungere, å implementere cards er en essensiell del av Turn Handler, så det er også prioritet. Ellers vil vi jobbe litt med scenes, så vi kan få en oversikt over å lage forskjellige menyer og grafiske grensesnitt. Noen scenes vi skal lage inneholder kort, så det må implementers.

Forklar kort hvilke hovedkrav dere anser som en del av MVP og hvorfor. Hvis det er gjort endringer i rekkefølge ut ifra hva som er gitt fra kunde, hvorfor er dette gjort?
    
    Spillbrett
    Vise en og/eller flere roboter
    Flytte en brikke med kort
    Vise flagg og hull på brett
    Kunne dø og vinne
    Dele ut kort
    Registrere at en robot har vært innom et flagg
    Håndtere konflikter i bevegelser
    
    Vi har valgt å prioritere dette først for å få en grei start for videre utvikling.

Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.

    Vi har absolutt prioritet å få lagd et logisk spillbrett i form av Map. Det vi har gjort siden forrige gang er å få styr på strukturen til Map (bruk av TIledMap) og flyttet dette mye ut av grafikken. Vi har også startet lett på en implementasjon av Kort    

Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).

    Vi hadde en liten bug med keyup funksjonen, som vi beholder for å teste flytting av brikker. Den er fikset.
    Det har også vært noen små tweaks med testene slik at de kjørte riktig på alle devices

Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelederne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.

    Se README.md

Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OSX.

    Hans bruker OSX, Tines bruker Linux, Torstein og Sindre bruker
    Windows. derfor vet vi at prosjektet bygger, kan testes, og kjøres på alle
    disse operativsystemene

Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet. Det kan være smart å skrive manuelle tester for å teste det som er grafisk.


Utførte oppgaver skal være ferdige.
Lever klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.)
    
   ![](Oblig2UML.png)


Hvis dere tester manuelt: lever beskrivelser av hvordan testen foregår, slik at gruppeleder kan utføre testen selv.


