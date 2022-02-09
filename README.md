# Git Searcher

<br />
<div align="center">
  <a >
    <img src="https://user-images.githubusercontent.com/61595425/153286016-fff91d28-4a72-4a62-8cce-20e95a5a5f45.svg" alt="Logo" width="80" height="80">
  </a>
  </br>
   </br>
</div>

Jednostavniji projekt izrađen u sklopu potrage za studentskom praksom. Primijenjena je MVP arhitektura, a aplikacija uz pomoć retrofit poziva dohvaća podatke o GitHub repozitorijima. Podaci se ispisuju unutar RecyclerViewa.

## Zamišljeni dizaj

Zamišljeni dizaj izrađen u AdobeXD alatu.

<div align="center">
  <a >
    <img width="720" alt="design" src="https://user-images.githubusercontent.com/61595425/153285184-6955ca24-9b4e-4454-9391-865f2acfbfff.png">
  </a>
  </br>
   </br>
</div>


## Korisnički zahtjevi

* Osnovna funkcija: pretraga Github repozitorija:
  * rezultat prikazan u listi
  * element liste mora sadržavat naziv i vrijeme zadnjeg ažuriranja
  * prikaz soritran po datumu
  * klik na određeni repozitorij:
    * novi prozor sa dodatnim detaljima(naziv, zadnje ažuriranje, vlasnik, opis)
