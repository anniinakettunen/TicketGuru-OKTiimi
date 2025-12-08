# TicketGuru

**Projektin nimi:** TicketGuru - lippumyyntijärjestelmä

**OK-Tiimi**: Anniina, Erkka, Teppo, Thu, Ville

------------------

## **1. Johdanto**

### **1.1 TicketGuru**
TicketGuru on lipunmyyntijärjestelmä, joka on kehitetty lipputoimiston käyttöön tapahtumalippujen myyntiä varten. Järjestelmän avulla lipputoimisto voi luoda tapahtumia, määritellä niihin lipputyyppejä sekä myydä ja tulostaa lippuja myyntipisteessä. Järjestelmä on tarkoitettu ensisijaisesti lipunmyyjien ja tapahtumien sisäänkäyntihenkilökunnan käyttöön.

Asiakkaana toimii lipputoimisto, joka haluaa keskitetyn ja helppokäyttöisen ratkaisun tapahtumien ja lipunmyynnin hallintaan. Järjestelmä tarjoaa asiakkaalle mahdollisuuden seurata myytyjä lippuja tapahtumakohtaisesti sekä tarkastaa ja merkitä liput käytetyiksi tapahtuman sisäänkäynnillä. Projektin päättyessä järjestelmä sisältää toiminnallisen myyntipistekäyttöön tarkoitetun lipunmyyntijärjestelmän.

### **1.2 Toteutus- ja toimintaympäristö**
Järjestelmä on toteutettu palvelinpohjaisena web-sovelluksena käyttäen Java-kieltä ja Spring Boot -kehystä. Palvelinpuolen rajapinnat on toteutettu REST-API-periaatteiden mukaisesti.

Järjestelmä käyttää kahta eri tietokantaratkaisua: tuotantoympäristössä käytössä on PostgreSQL-pohjainen pysyvä tietokanta, kun taas testausvaiheessa hyödynnetään H2-muistitietokantaa.

Käyttöliittymä on toteutettu selainpohjaisena käyttöliittymänä Thymeleaf-templateja hyödyntäen. Päätelaitteena toimii pääasiassa desktop-työasema, sillä järjestelmää käytetään ensivaiheessa lipunmyyntipisteessä. Järjestelmän arkkitehtuuri on suunniteltu siten, että sitä voidaan jatkokehityksessä laajentaa myös muihin päätelaitteisiin, kuten mobiilikäyttöön.

Järjestelmän rakennetta on suunniteltu laajennettavaksi, ja siihen on mahdollista lisätä jatkokehityksessä esimerkiksi verkkokauppatoiminnallisuus loppuasiakkaiden itsenäistä lipunostoa varten.

-----------------

## **2. Järjestelmän määrittely**

TicketGurun tarkoituksena on tarjota lipputoimistolle keskitetty järjestelmä tapahtumien, lipputyyppien ja lipunmyynnin hallintaan.

### **2.1 Käyttäjäryhmät**

**Järjestelmän ylläpitäjä (Admin)** hallitsee koko järjestelmää ja sen sisältöä, mukaan lukien tapahtumat, lipputyypit ja käyttäjät.

**Lipunmyyjä** voivat myydä ja tulostaa lippuja sekä tarkastella lippujen määriä ja hintoja.


### **2.2 Käyttäjätarinat**
> **1.**
> Lipunmyyntitoimistona haluan myydä lippuja eri asiakasryhmille (esim. aikuiset ja lapset), jotta voin tarjota asiakkaille sopivat lippuvaihtoehdot ja hinnoittelun.

> **2.**
> Lipunmyyntitoimistona haluan luoda uuden tapahtuman järjestelmään (nimi, kuvaus, aika, paikka, kaupunki, lippujen määrä), jotta voin aloittaa lipunmyynnin ajoissa.

> **3.**
> Lipunmyyntitoimistona haluan tulostaa myydyn lipun, jotta voin antaa asiakkaalle fyysisen todisteen ostoksesta ja varmistaa pääsyn tapahtumaan.

>**4.**
> Lipunmyyntitoimistona haluan muokata tapahtumien tietoja (aika, paikka, kuvaus, kaupunki, lippujen määrä), jotta voin pitää tapahtumatiedot ajan tasalla ja varmistaa oikean tiedon asiakkaille.

>**5.** 
> Lipunmyyntitoimistona haluan tarkastella myyntiraportteja tapahtumakohtaisesti, jotta voin seurata lipunmyynnin kehitystä, kirjata myydyt lipputyypit ja niiden tuottamat tulot sekä tehdä parempia liiketoimintapäätöksiä.


>**6.** 
> Lipunmyyntitoimistona haluan luoda uuden tapahtuman järjestelmään (nimi, kuvaus, aika, paikka, kaupunki), jotta voin aloittaa lipunmyynnin ajoissa.

---------------

## **3. Käyttöliittymä**

![Kayttoliittymakuva](ticketguru/public/kayttoliittymakuva.jpg)


-----------------------------
## **4. Tietokanta** 

### **4.1 Tietokantamalli**

![Tietokanta](ticketguru/public/Tietokanta.jpg)

### **4.2 Tietohakemistokuvaukset**

![tk_kayttaja](ticketguru/public/tk_kayttaja.jpg)
![tk_rooli](ticketguru/public/tk_rooli.jpg)
![tk_lippu](ticketguru/public/tk_lippu.jpg)
![tk_lipputyypit](ticketguru/public/tk_lipputyypit.jpg)
![tk_lipunmyynti](ticketguru/public/tk_lipunmyynti.jpg)
![tk_tapahtumat](ticketguru/public/tk_tapahtumat.jpg)

### **4.3 Tietokannan ratkaisu**


- Sovelluken pysyvä tietokanta on toteutettu käyttäen **PostgreSQL**, koska se integroituu sujuvasti Springbootin/JPA:n kanssa.

- Kehitysvaiheessa käytämme edelleen **H2-tietokantaa**, jotta datan tallennusta ja sovelluksen toimintaa voidaan testata helposti.

- Sovellus hyödyntää Springin profiileja eri ympäristöissä:

    - **dev** : H2-tietokanta (kehitys)

    - **test** :  PostgreSQL paikallisesti (testaus)

    - **prod** : PostgreSQL Rahti-palvelimella (tuotanto)

  Profiilien avulla voidaan vaihtaa tietokantaa ympäristökohtaisesti muuttamatta sovelluksen koodia.

### **4.4 Testidata kehitystä varten**

- Sovelluksen kehitystä ja testausta varten on luotu erillinen testidata. Testidatan avulla voidaan varmistaa, että sovellus toimii odotetusti eri tilanteissa.

- Kehitysympäristössä käytämme H2-tietokantaa, joka ajetaan paikallisesti (localhost).

- Testidata luodaan kahdella eri tavalla:

    - Kovakodattu testidata DemoApplication.java -tiedostossa.

    - SQL-tiedostot PostGreSQL:lle

         1. **data-localPotgreSQL.sql** sisältää testidatan paikalliseen PostgreSQL-tietokantaan.
         2. **data.sql** sisältää testidatan tuotantokäyttöön Rahti-palvelimella sijaitsevaan PostgreSQL-tietokantaan.


--------------------------------

## **5. Tekninen kuvaus**

### **5.1 Järjestelmän yleiskuva**

  TicketGuru on toteutettu client-server-arkkitehtuurilla web-sovelluksena.

  Järjestelmä koostuu seuraavista pääkomponenteista:

  - Client (käyttöliittymä)

      - Selainpohjainen käyttöliittymä, toteutettu Thymeleaf-templaatteja hyödyntäen.

      - Käyttö päätelaitteella, pääasiassa desktop-työasema.

  - Server (palvelin)

      - Java + Spring Boot -palvelin, ajettuna Rahti-palvelimella tuotantoympäristössä.

      - Käsittelee clientin REST API -pyynnöt ja kommunikoi tietokannan kanssa.

  - Tietokanta

      - PostgreSQL tuotannossa ja H2 kehitysympäristössä.

      - Palvelin kommunikoi tietokannan kanssa JPA/Hibernate-rajapintojen kautta.


Komponenttien väliset yhteydet:

  - Client ja Server kommunikoivat REST API-rajapinnan kautta.

  - Server ja tietokanta kommunikoivat JPA/Hibernate -yhteyksin.


### **5.2 Palvelintoteutus** 

- Teknologiat: Java/Spring Boot, JPA/Hibernate, Maven.

- Deployment:

    - Dev-profiili: H2-tietokanta, kehitysympäristö.

    - Test-profiili: PostgreSQL paikallisesti.

    - Prod-profiili: PostgreSQL Rahti-palvelimella.

- Sovelluksen toiminta:

   - Serveri vastaanottaa clientin REST API -pyynnöt, käsittelee ne liiketoimintalogiikan mukaisesti ja tallentaa/hae tiedot tietokannasta.

### **5.3 Turvallisuusratkaisu**


- Sovelluksen tietoturva on toteutettu **Spring Security -kirjastolla (WebSecurityConfig)**.

- Käytössä on **HTTP Basic -autentikointi**, jossa käyttäjät tunnistautuvat **käyttäjätunnuksella ja salasanalla**.

- Salasanat tallennetaan turvallisesti **BCryptPasswordEncoder**:lla.

- Kaikki API-endpointit vaativat **autentikoinnin**.

- **Käyttöoikeudet** määritellään käyttäjäroolien mukaan:

    - **Admin**: käyttäjä, jolla on täydet oikeudet.

    - **Myyjä**: käyttäjä, jolla on rajatut oikeudet.

          | API Endpoint | Käyttöoikeudet |
          |---------------|----------------|
          | /api/users/** | vain ADMIN     |
          | /api/roles/** | vain ADMIN     |
          | Muut /api/**  | ADMIN ja MYYJÄ |

- **Odotetut vastaukset**:

    - **200 OK**: kirjautuminen onnistuu ja käyttäjällä on riittävät käyttöoikeudet kyseiseen endpointiin.

        Esim.

        - Myyjä-käyttäjä kirjautuu endpointiin /api/events

        - Admin-käyttäjä kirjautuu onnistuneesti kaikkiin endpointteihin

    - **403 Forbidden**: kirjautuminen onnistuu, mutta käyttöoikeudet eivät riitä kyseiseen endpointiin.

        Esim.

        - Myyjä-käyttäjä yrittää kirjautua endpointiin /api/roles tai /api/users

    - **401 Unauthorized**: käyttäjä ei ole kirjautunut, tai kirjautuminen epäonnistuu. 

        Esim.

        - Käyttäjä yrittää avata /api/events ilman tunnistautumista.




### **5.4 REST- rajapinta**

TicketGuru hyödyntää REST API -rajapintoja, joiden avulla client ja server kommunikoivat.

-------
#### **Base-URL**:
https://demo-2-ticketguru-oktiimi.2.rahtiapp.fi/api

---------

#### **ROOLIT API**

**1. Hae kaikki roolit**

  - **Metodi**: GET

  - **URL**: /roles

  - **Vastaus: 200 OK**

```json
[
    {
        "roleId": 1,
        "roleName": "myyjä",
        "notes": null
    }
]
```
**2. Hae roolin ID:llä**
    
  - **Metodi**: GET

  - **URL**: /roles/{id}

  - **Parametrit**: id = roleId (Long)
  
  - **Vastaus: 200 OK**

```json

 {
    "roleId": 1,
    "roleName": "myyjä",
    "notes": null
}
```

  - **virhe**:
     - **404 Not Found**
  

**3. Luo uusi rooli**
    
  - **Metodi**: POST

  - **URL**: /roles

  - **Request Body (JSON)**: 

```json
{
        "roleName": "admin",
        "notes": "Responsible for system maintenance and myyjä-users"
    }
```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | roleName      |String          | Pakollinen, ei saa olla tyhjä , max 50 merkkiä, uniikki   |
  | notes     | String             | Valinnainen, enintään 255 merkkiä               |
  
  - **Vastaus: 200 OK**

```json
  {
    "roleId": 2,
    "roleName": "admin",
    "notes": "Responsible for system maintenance and myyjä-users"
}
  ```
  - **virhe**:
     - **400 Bad request**

**4. Päivitä roolin tiedot ID:llä**
    
  - **Metodi**: PUT

  - **URL**: /roles/{id}

  - **Parametrit**: id = roleId (Long)

  - **Request Body (JSON)**: 

```json
{
        "roleName": "myyjä",
        "notes": "Full access to all ticket sales, no access to user management"
}

```
  -**Validointi**:

  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | roleName      |String          | Pakollinen, ei saa olla tyhjä , max 50 merkkiä, uniikki   |
  | notes     | String             | Valinnainen, enintään 255 merkkiä               |


  - **Vastaus: 200 OK**

```json
  {
    "roleId": 1,
    "roleName": "myyjä",
    "notes": "Full access to all ticket sales, no access to user management"
}
  ```
  - **Virhe**: 
    - 404 Not Found
    - 400 Bad Request

**5. Poista roolin ID:llä**

  - **Metodi**: DELETE

  - **URL**: /roles/{id}

  - **Parametrit**: id = roolin Id (Long)

  - **Vastaus: 204 No Content**

  - **Virhe**: 
    - 404 Not Found



### KÄYTTÄJÄT API


**1. Hae kaikki käyttäjät**

  - **Metodi**: GET

  - **URL**: /users

  - **Vastaus: 200 OK**

```json
[
     {
        "id": 1,
        "firstname": "Oskari",
        "lastname": "Uninen",
        "email": "oskari.uninen@gmail.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": "Full access to all ticket sales, no access to user management"
        }
    },
    {
        "id": 2,
        "firstname": "Jaska",
        "lastname": "Jokunen",
        "email": "jaska.jokunen@hotmail.com",
        "phone": "87654321",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": "Full access to all ticket sales, no access to user management"
        }
    }
]
```
**2. Hae käyttäjän ID:llä**
    
  - **Metodi**: GET

  - **URL**: /users/{id}

  - **Parametrit**: id = käyttäjän id (Long)
  
  - **Vastaus: 200 OK**

```json

 {
    "id": 1,
    "firstname": "Oskari",
    "lastname": "Uninen",
    "email": "oskari.uninen@gmail.com",
    "phone": "12345678",
    "role": {
        "roleId": 1,
        "roleName": "myyjä",
        "notes": "Full access to all ticket sales, no access to user management"
    }
}
```

  - **virhe**:
     - **404 Not Found**
  

**3. Luo uusi käyttäjä**
    
  - **Metodi**: POST

  - **URL**: /users

  - **Request Body (JSON)**: 

```json
{
    "firstname": "Joku",
    "lastname": "Jokainen",
    "email": "Joku.jokainen@gmail.com",
    "phone": "99887766",
    "role": {
        "roleId": 1
    }
}
```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | firstname      |String          | Pakollinen, ei saa olla tyhjä                               |
  | lastname      | String         | Pakollinen, ei saa olla tyhjä                 |
  | email          | String        | Pakollinen, ei saa olla tyhjä, unikki, kelvollinen sähköposti        |
  | phone           |String          | Pakollinen, ei saa olla tyhjä, vähintään 6 merkkiä                               |
  | role      | Role        | Pakollinen, ei saa olla null               |

  - **Vastaus: 200 OK**

```json
  {
    "id": 3,
    "firstname": "Joku",
    "lastname": "Jokainen",
    "email": "Joku.jokainen@gmail.com",
    "phone": "99887766",
    "role": {
        "roleId": 1,
        "roleName": "myyjä",
        "notes": "Full access to all ticket sales, no access to user management"
    }
}
  ```
  - **virhe**:
     - **400 Bad request**

**4. Päivitä käyttäjän tiedot ID:llä**
    
  - **Metodi**: PUT

  - **URL**: /users/{id}

  - **Parametrit**: id = käyttäjän Id (Long)

  - **Request Body (JSON)**: 

```json
{
    "firstname": "Oskari",
    "lastname": "Uninen",
    "email": "oskari.uninen@gmail.com",
    "phone": "12345678",
    "role": {
        "roleId": 2,
    }
}

```
  -**Validointi**:

  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | firstname      |String          | Pakollinen, ei saa olla tyhjä                               |
  | lastname      | String         | Pakollinen, ei saa olla tyhjä                 |
  | email          | String        | Pakollinen, ei saa olla tyhjä, unikki, kelvollinen sähköposti        |
  | phone           |String          | Pakollinen, ei saa olla tyhjä, vähintään 6 merkkiä                               |
  | role      | Role                | Pakollinen, ei saa olla null               |


  - **Vastaus: 200 OK**

```json
   {
    "id": 1,
    "firstname": "Oskari",
    "lastname": "Uninen",
    "email": "oskari.uninen@gmail.com",
    "phone": "12345678",
    "role": {
        "roleId": 2,
        "roleName": "admin",
        "notes": "Responsible for system maintenance and myyjä-users"
    }
}
  ```
  - **Virhe**: 
    - esim. **400 Bad Request** Role not found with id 3 

**5. Poista käyttäjän ID:llä**

  - **Metodi**: DELETE

  - **URL**: /users/{id}

  - **Parametrit**: id = käyttäjän Id (Long)

  - **Vastaus**: 204 No Content

  - **Virhe**: 
    - 404 Not Found


### LIPUNTYYPIT API


**1. Hae kaikki lipuntyypit**

  - **Metodi**: GET

  - **URL**: /tickettypes

  - **Vastaus: 200 OK**

```json
[
    {
        "ticketTypeId": 1,
        "ticketName": "Adult",
        "price": 30.0
    },
    {
        "ticketTypeId": 2,
        "ticketName": "Child",
        "price": 15.0
    },
    {
        "ticketTypeId": 3,
        "ticketName": "Senior",
        "price": 20.0
    }
]
```
**2. Hae lipuntyypin ID:llä**
    
  - **Metodi**: GET

  - **URL**: /tickettypes/{id}

  - **Parametrit**: id = ticketTypeId (Long)
  
  - **Vastaus: 200 OK**

```json

 {
    "ticketTypeId": 1,
    "ticketName": "Adult",
    "price": 30.0
}
```

  - **virhe**:
     - 404 Not Found
  

**3. Luo uusi lipuntyyppi**
    
  - **Metodi**: POST

  - **URL**: /tickettypes

  - **Request Body (JSON)**: 

```json
 {
        "ticketName": "student",
        "price": 15.0
    }
```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | ticketTypeId   | Long           | autogeneroitu                                 |
  | ticketName     | String         | Pakollinen, ei saa olla tyhjä                 |
  | price           | Double         | Pakollinen, ei saa olla null ja >= 0         |

  - **Vastaus: 200 OK**

```json
  {
        "ticketTypeId": 4,
        "ticketName": "student",
        "price": 15.0
    }
  ```
  - **virhe**:
     - esim. 400 Bad request

**4. Päivitä lipuntyypin ID:llä**
    
  - **Metodi**: PUT

  - **URL**: /tickettypes/{id}

  - **Parametrit**: id = ticketTypeId (Long)

  - **Request Body (JSON)**: 

```json
{
        "ticketName": "Student",
        "price": 10.0
    }

```
  -**Validointi**:

  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | ticketTypeId   | Long           | autogeneroitu                                 |
  | ticketName     | String         | Pakollinen, ei saa olla tyhjä                 |
  | price           | Double         | Pakollinen, ei saa olla null ja >= 0         |

  - **Vastaus: 200 OK**

```json
   {
    "ticketTypeId": 4,
    "ticketName": "Student",
    "price": 10.0
}
  ```
  - **Virhe**: 
    - 400 Bad Request 

**5. Poista lipuntyypin ID:llä**

  - **Metodi**: DELETE

  - **URL**: /tickettypes/{id}

  - **Parametrit**: id = ticketTypeId (Long)

  - **Vastaus: 204 No Content**

  - **Virhe**: 
    - 404 Not Found


### TAPAHTUMAT API

#### Huomio päivämärästä:

- **Päivämäärä**: YYYY-MM-DD (esim. "2025-10-05")

- **Päivämäärä ja aika**: YYYY-MM-DDTHH:MM:SS (esim. "2025-10-10T18:00:00")

#### Endpointit

**1. Hae kaikki tapahtumat**

  - **Metodi**: GET

  - **URL**: /events

  - **Vastaus: 200 OK**

```json
[
  {
    "eventId": 1,
    "eventName": "Rock Concert",
    "eventLocation": "Arena A",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-05",
    "eventDescription": "Live concert",
    "maxNumberOfTickets": 500
  },
   {
        "eventId": 2,
        "eventName": "Jazz Sunday",
        "eventLocation": "Jazz Club",
        "eventCity": "Tampere",
        "eventDate": "2025-11-02",
        "eventDescription": "Smooth jazz evening",
        "maxNumberOfTickets": 150
    },
]
```
**2. Hae tapahtuma ID:llä**
    
  - **Metodi**: GET

  - **URL**: /events/{id}

  - **Parametrit**: id = tapahtuman Id (Long)

  - **Vastaus: 200 OK**

```json
{
  "eventId": 1,
  "eventName": "Rock Night",
  "eventLocation": "Arena",
  "eventCity": "Helsinki",
  "eventDate": "2025-10-05",
  "eventDescription": "Live rock music",
  "maxNumberOfTickets": 500
}
```
  - **Virhe**: 
    - 404 Not Found 

**3. Luo uusi tapahtuma**
    
  - **Metodi**: POST

  - **URL**: /events

  - **Request Body (JSON)**: 

```json
  {
    "eventName": "Lapsimessu",
    "eventLocation": "Messukeskus",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-30",
    "eventDescription": "Child fair",
    "maxNumberOfTickets": 1000
}

```
  - **Validointi**:
      | Kenttä              | Tyyppi     | Validointisäännöt |
      |----------------------|------------|-------------------|
      | eventName            | String     | Pakollinen, 2–100 merkkiä |
      | eventLocation        | String     | Pakollinen |
      | eventCity            | String     | Pakollinen |
      | eventDate            | LocalDate  | Täytyy olla tänään tai tulevaisuudessa |
      | eventDescription     | String     | Enintään 500 merkkiä |
      | maxNumberOfTickets   | Integer    | Vähintään arvo 1 |

  - **Vastaus**: **200 OK**

```json
   {
    "eventId": 4,
    "eventName": "Lapsimessu",
    "eventLocation": "messukeskus",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-30",
    "eventDescription": "Child fair",
    "maxNumberOfTickets": 1000
  }   

  ```
  - **Virhe**

    - **400 Bad Request** ( jos validointisääntöjä on rikottu )


**4. Päivitä tapahtuma ID:llä**
    
  - **Metodi**: PUT

  - **URL**: /events/{id}

  - **Parametrit**: id = tapahtuman Id (Long)

  - **Request Body (JSON)**: 

```json
  {
    "eventName": "Rock Night",
    "eventLocation": "Päivitetty uusi arena",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-31",
    "eventDescription": "Live rock music",
    "maxNumberOfTickets": 500
}

```

   - **Validointi**:
      | Kenttä              | Tyyppi     | Validointisäännöt |
      |----------------------|------------|-------------------|
      | eventName            | String     | Pakollinen, 2–100 merkkiä |
      | eventLocation        | String     | Pakollinen |
      | eventCity            | String     | Pakollinen |
      | eventDate            | LocalDate  | Täytyy olla tänään tai tulevaisuudessa |
      | eventDescription     | String     | Enintään 500 merkkiä |
      | maxNumberOfTickets   | Integer    | Vähintään arvo 1 |

  - **Vastaus**: 200 OK

  - **Virhe**: 
      - **404 Not Found**
      - **400 Bad Request** ( jos validointisääntöjä on rikottu )

**5. Poista tapahtuma ID:llä**

  - **Metodi**: DELETE

  - **URL**: /events/{id}

  - **Parametri**: id = tapahtuman Id (Long)

  - **Vastaus: 204 No Content**

  - **Virhe:**
    - 404 Not Found



### LIPUNMYYNTI API

#### Huomio päivämärästä:

- **Päivämäärä**: YYYY-MM-DD (esim. "2025-10-05")

- **Päivämäärä ja aika**: YYYY-MM-DDTHH:MM:SS (esim. "2025-10-10T18:00:00")

**1. Hae kaikki lipunmynnit**

  - **Metodi**: GET

  - **URL**: /ticketsales

  - **Vastaus: 200 OK**

```json
[
     {
        "saleId": 1,
        "dateTime": "2025-10-07T20:56:29.347646",
        "price": 60.00,
        "user": {
            "id": 1,
            "firstname": "Oskari",
            "lastname": "Uninen",
            "email": "oskari.uninen@gmail.com",
            "phone": "12345678",
            "role": {
                "roleId": 1,
                "roleName": "myyjä",
                "notes": "Full access to all ticket sales, no access to user management"
            }
        },
        "tickets": [
            {
                "ticketId": 1,
                "ticketCode": 1001,
                "ticketTypeId": {
                    "ticketTypeId": 3,
                    "ticketName": "Senior",
                    "price": 20.0
                },
                "eventId": {
                    "eventId": 1,
                    "eventName": "Rock Night",
                    "eventLocation": "Päivitetty uusi arena",
                    "eventCity": "Helsinki",
                    "eventDate": "2025-10-31",
                    "eventDescription": "Live rock music",
                    "maxNumberOfTickets": 500
                }
            },
            {
                "ticketId": 2,
                "ticketCode": 1002,
                "ticketTypeId": {
                    "ticketTypeId": 1,
                    "ticketName": "Adult",
                    "price": 30.0
                },
                "eventId": {
                    "eventId": 1,
                    "eventName": "Rock Night",
                    "eventLocation": "Päivitetty uusi arena",
                    "eventCity": "Helsinki",
                    "eventDate": "2025-10-31",
                    "eventDescription": "Live rock music",
                    "maxNumberOfTickets": 500
                }
            }
        ]
    }
]
```
**2. Hae lipunmyynti ID:llä**
    
  - **Metodi**: GET

  - **URL**: /ticketsales/{id}

  - **Parametrit**: id = saleId (Long)
  
  - **Vastaus: 200 OK**

```json

 {
        "saleId": 1,
        "dateTime": "2025-10-07T20:56:29.347646",
        "price": 60.00,
        "user": {
            "id": 1,
            "firstname": "Oskari",
            "lastname": "Uninen",
            "email": "oskari.uninen@gmail.com",
            "phone": "12345678",
            "role": {
                "roleId": 1,
                "roleName": "myyjä",
                "notes": "Full access to all ticket sales, no access to user management"
            }
        },
        "tickets": [
            {
                "ticketId": 1,
                "ticketCode": 1001,
                "ticketTypeId": {
                    "ticketTypeId": 3,
                    "ticketName": "Senior",
                    "price": 20.0
                },
                "eventId": {
                    "eventId": 1,
                    "eventName": "Rock Night",
                    "eventLocation": "Päivitetty uusi arena",
                    "eventCity": "Helsinki",
                    "eventDate": "2025-10-31",
                    "eventDescription": "Live rock music",
                    "maxNumberOfTickets": 500
                }
            },
            {
                "ticketId": 2,
                "ticketCode": 1002,
                "ticketTypeId": {
                    "ticketTypeId": 1,
                    "ticketName": "Adult",
                    "price": 30.0
                },
                "eventId": {
                    "eventId": 1,
                    "eventName": "Rock Night",
                    "eventLocation": "Päivitetty uusi arena",
                    "eventCity": "Helsinki",
                    "eventDate": "2025-10-31",
                    "eventDescription": "Live rock music",
                    "maxNumberOfTickets": 500
                }
            }
        ]
    }
{
    "saleId": 1,
    "dateTime": "2025-10-10T18:00:00",
    "price": 60.00,
    "user": {
        "id": 1,
        "firstname": "Demo1",
        "lastname": "User1",
        "email": "demo1@example.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    }
}

```
  - **Virhe**: 
    - 404 Not Found

**3. Luo uusi lipunmyynti**
    
  - **Metodi**: POST

  - **URL**: /ticketsales

  - **Request Body (JSON)**: 

```json
  {
  "dateTime": "2025-10-15T14:00:00",
  "price": 60.0,
  "user": { "id": 2 },
  "tickets": [
    {
      "ticketCode": 1001,
      "ticketTypeId": { "ticketTypeId": 1 },
      "eventId": { "eventId": 2 }
    },
    {
      "ticketCode": 1222,
      "ticketTypeId": { "ticketTypeId": 1 },
      "eventId": { "eventId": 2 }
    }
  ]
}
```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | dateTime      | LocalDateTime  | Pakollinen, ei saa olla null                  |
  | price         | BigDecimal     | Pakollinen, ≥ 0, ≤ 999,999.99, max 2 desimaalia |
  | user          | AppUser        | Pakollinen, ei saa olla null                  |
  | tickets       | List<Ticket>   | Pakollinen, ei voi olla tyhjä                   |
  | ticketCode    | Integer        | Valinnainen, generoidaan automaattisesti jos puuttuu|
  | ticketTypeId  | TicketType     | Pakollinen, pitää olla olemassa               |
  | eventId       | Event          | Pakollinen, pitää olla olemassa               |

   
  - **Vastaus: 200 OK**

```json
  {
    "saleId": 3,
    "dateTime": "2025-10-15T14:00:00",
    "price": 60.0,
    "user": {
        "id": 2,
        "firstname": "Jaska",
        "lastname": "Jokunen",
        "email": "jaska.jokunen@hotmail.com",
        "phone": "87654321",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    },
    "tickets": [
        {
            "ticketId": 5,
            "ticketCode": 1001,
            "ticketTypeId": {
                "ticketTypeId": 1,
                "ticketName": "Adult",
                "price": 30.0
            },
            "eventId": {
                "eventId": 2,
                "eventName": "Jazz Sunday",
                "eventLocation": "Jazz Club",
                "eventCity": "Tampere",
                "eventDate": "2025-11-02",
                "eventDescription": "Smooth jazz evening",
                "maxNumberOfTickets": 150
            }
        },
        {
            "ticketId": 6,
            "ticketCode": 1222,
            "ticketTypeId": {
                "ticketTypeId": 1,
                "ticketName": "Adult",
                "price": 30.0
            },
            "eventId": {
                "eventId": 2,
                "eventName": "Jazz Sunday",
                "eventLocation": "Jazz Club",
                "eventCity": "Tampere",
                "eventDate": "2025-11-02",
                "eventDescription": "Smooth jazz evening",
                "maxNumberOfTickets": 150
            }
        }
    ]
}
  ```
  - **Virhe**: 

    - esim. **400 Bad Request** "TicketType not found"

**4. Päivitä lipunmyynti ID:llä**
    
  - **Metodi**: PUT

  - **URL**:/ticketsales/{id}

  - **Parametrit**: id = saleId (Long)

  - **Request Body (JSON)**: 

```json
 {
  "user": { "id": 2 }, // uusi käyttäjä
  "dateTime": "2025-10-15T12:30:00",
  "price": 60.0
}

```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | dateTime      | LocalDateTime  | Pakollinen, ei saa olla null                  |
  | price         | BigDecimal     | Pakollinen, ≥ 0, ≤ 999,999.99, max 2 desimaalia |
  | user          | AppUser        | Pakollinen, ei saa olla null                  |
  | tickets       | List<Ticket>   | Pakollinen, ei voi olla tyhjä                   |
  | ticketCode    | Integer        | Valinnainen, generoidaan automaattisesti jos puuttuu|
  | ticketTypeId  | TicketType     | Pakollinen, pitää olla olemassa               |
  | eventId       | Event          | Pakollinen, pitää olla olemassa               |

  - **Vastaus: 200 OK**

```json
   {
    "saleId": 1,
    "dateTime": "2025-10-15T12:30:00",
    "price": 60.0,
    "user": {
        "id": 2,
        "firstname": "Jaska",
        "lastname": "Jokunen",
        "email": "jaska.jokunen@hotmail.com",
        "phone": "87654321",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    },
    "tickets": [
        {
            "ticketId": 1,
            "ticketCode": 1001,
            "ticketTypeId": {
                "ticketTypeId": 1,
                "ticketName": "Adult",
                "price": 30.0
            },
            "eventId": {
                "eventId": 1,
                "eventName": "Rock Night",
                "eventLocation": "Arena",
                "eventCity": "Helsinki",
                "eventDate": "2025-10-10",
                "eventDescription": "Live rock music",
                "maxNumberOfTickets": 500
            }
        },
        {
            "ticketId": 2,
            "ticketCode": 1002,
            "ticketTypeId": {
                "ticketTypeId": 1,
                "ticketName": "Adult",
                "price": 30.0
            },
            "eventId": {
                "eventId": 1,
                "eventName": "Rock Night",
                "eventLocation": "Arena",
                "eventCity": "Helsinki",
                "eventDate": "2025-10-10",
                "eventDescription": "Live rock music",
                "maxNumberOfTickets": 500
            }
        }
    ]
}

  ```
  - **Virhe**: 
    - esim. **400 Bad Request** Uset not found

**5. Poista lipunmyynnin ID:llä**

  - **Metodi**: DELETE

  - **URL**:ticketsales/{id}

  - **Parametrit**: id = saleId (Long)

  - **Vastaus**: 
      - 204 No Content

  - **Virhe**: 
    - 404 Not Found


### LIPUT API


**1. Hae kaikki myydyt liput**

  - **Metodi**: GET

  - **URL**: /tickets

  - **Vastaus: 200 OK**

```json
[
    {
      "ticketId": 1,
      "ticketCode": 1001,
      "ticketTypeId": {
      "ticketTypeId": 1,
      "ticketName": "Adult",
      "price": 30
      },
      "eventId": {
      "eventId": 1,
      "eventName": "Rock Night",
      "eventLocation": "Arena",
      "eventCity": "Helsinki",
      "eventDate": "2026-10-10",
      "eventDescription": "Live rock music",
      "maxNumberOfTickets": 500
      },
      "used": false
      },
    {
      "ticketId": 2,
      "ticketCode": 1002,
      "ticketTypeId": {
      "ticketTypeId": 1,
      "ticketName": "Adult",
      "price": 30
      },
      "eventId": {
      "eventId": 1,
      "eventName": "Rock Night",
      "eventLocation": "Arena",
      "eventCity": "Helsinki",
      "eventDate": "2026-10-10",
      "eventDescription": "Live rock music",
      "maxNumberOfTickets": 500
      },
      "used": false
    }
  ]
```
**2. Hae myydyn lipun ID:llä**
    
  - **Metodi**: GET

  - **URL**: tickets/{id}

  - **Parametrit**: id = ticketId (Long)
  
  - **Vastaus: 200 OK**

```json

    {
      "ticketId": 1,
      "ticketCode": 1001,
      "ticketTypeId": {
      "ticketTypeId": 1,
      "ticketName": "Adult",
      "price": 30
      },
      "eventId": {
      "eventId": 1,
      "eventName": "Rock Night",
      "eventLocation": "Arena",
      "eventCity": "Helsinki",
      "eventDate": "2026-10-10",
      "eventDescription": "Live rock music",
      "maxNumberOfTickets": 500
      },
      "used": false
    }
```
  - **Virhe**: 

    - null

**3. Hae lippu lipunkoodilla**

  - **Metodi**: GET

  - **URL**: /tickets/check?ticketCode={ticketCode}

  - **Vastaus:** 200 OK
  

```json

    {
      "ticketId": 1,
      "ticketCode": 1001,
      "ticketTypeId": {
      "ticketTypeId": 1,
      "ticketName": "Adult",
      "price": 30
      },
      "eventId": {
      "eventId": 1,
      "eventName": "Rock Night",
      "eventLocation": "Arena",
      "eventCity": "Helsinki",
      "eventDate": "2026-10-10",
      "eventDescription": "Live rock music",
      "maxNumberOfTickets": 500
      },
      "used": false
    }
```


  - **Virhe:** 404 Not Found


**. Merkitse lippu käytetyksi**
  - **Metodi**: PATCH

  - **URL**: /tickets/use?ticketCode={ticketCode} 

  - **Parametrit**: ticketCode (Long)

  - **Vastaus** : 200OK

```json

    {
    "status": "SUCCESS",
    "message": "Ticket marked as used successfully",
    "ticket": {
        "ticketId": 1,
        "ticketCode": 1001,
        "ticketTypeId": {
            "ticketTypeId": 1,
            "ticketName": "Adult",
            "price": 30
        },
        "eventId": {
            "eventId": 1,
            "eventName": "Rock Night",
            "eventLocation": "Arena",
            "eventCity": "Helsinki",
            "eventDate": "2026-10-10",
            "eventDescription": "Live rock music",
            "maxNumberOfTickets": 500
        },
        "used": true
    }
}
```

  - **Virheet**:
    - 400 ERROR: Lipun koodi ei ole numeromuodossa

    - 400 ALREADY_USED: Lippu on jo merkitty käytetyksi

    - 404 NOT FOUND : Lipun koodilla ei löytynyt lippua.

**4. Luo uusi myyty lippu**
    
  - **Metodi**: POST

  - **URL**: /tickets

  - **Request Body (JSON)**: 

```json
  {
  "ticketCode": "1015",
  "ticketTypeId": {
    "ticketTypeId": 3
  },
  "eventId": {
    "eventId": 2
  }
}
```
  - **Validointi**:
    
  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | ticketCode    | Long           | Valinnainen, generoidaan automaattisesti jos puuttuu|
  | ticketTypeId  | TicketType     | Pakollinen, pitää olla olemassa               |
  | eventId       | Event          | Pakollinen, pitää olla olemassa               |

  - **Vastaus: 200 OK**

```json
  {
    "ticketId": 5,
    "ticketCode": 1015,
    "ticketTypeId": {
        "ticketTypeId": 3,
        "ticketName": "Senior",
        "price": 20.0
    },
    "eventId": {
        "eventId": 2,
        "eventName": "Jazz Sunday",
        "eventLocation": "Jazz Club",
        "eventCity": "Tampere",
        "eventDate": "2025-11-02",
        "eventDescription": "Smooth jazz evening",
        "maxNumberOfTickets": 150
    }
}
  ```
  - **virhe**:
     - esim. **400 Bad request** TicketType not found

**5. Päivitä myydyn lipun ID:llä**
    
  - **Metodi**: PUT

  - **URL**: /tickets/{id}

  - **Parametrit**: id = ticketId (Long)

  - **Request Body (JSON)**: 

```json
 {              
  "ticketTypeId": { "ticketTypeId": 3 }, 
  "eventId": { "eventId": 1 }           
}

```
  -**Validointi**:

  | Kenttä        | Tyyppi         | Validointisäännöt                             |
  |---------------|----------------|-----------------------------------------------|
  | ticketCode    | Long           | Valinnainen, generoidaan automaattisesti jos puuttuu|
  | ticketTypeId  | TicketType     | Pakollinen, pitää olla olemassa               |
  | eventId       | Event          | Pakollinen, pitää olla olemassa               |
  - **Vastaus: 200 OK**

```json
   {
    "ticketId": 1,
    "ticketCode": 1001,
    "ticketTypeId": {
        "ticketTypeId": 3,
        "ticketName": "Senior",
        "price": 20.0
    },
    "eventId": {
        "eventId": 1,
        "eventName": "Rock Night",
        "eventLocation": "Päivitetty uusi arena",
        "eventCity": "Helsinki",
        "eventDate": "2025-10-31",
        "eventDescription": "Live rock music",
        "maxNumberOfTickets": 500
    }
}
  ```
  - **Virhe**: 
    - esim. **400 Bad Request** Event not found

**6. Poista myydyn lipun ID:llä**

  - **Metodi**: DELETE

  - **URL**: /tickets/{id}

  - **Parametrit**: id = ticketId (Long)

  - **Vastaus: 204 No Content**

  - **Virhe**: 
    - 404 Not Found
   



-------------------
## **6. Testaus**


TicketGuru-ohjelmiston testaus toteutettiin monitasoisesti, jotta järjestelmän toimivuus, luotettavuus ja laadukkuus voitiin varmistaa kaikilla tasoilla. Testaus koostui yksikkötestauksesta, integraatiotestauksesta sekä end-to-end-testauksesta. Näiden kolmen testausmenetelmän yhdistelmällä varmistettiin, että sekä sovelluksen pienimmät komponentit että koko järjestelmä toimivat suunnitellulla tavalla.

 - **Yksikkötestaus** testaa yksittäisten metodien ja luokkien toiminnan erillään muusta sovelluksesta. Sovelluksessa suoritettiin **JUnit-testit** kaikille entiteeteille, varmistaen niiden oikean toiminnan ja validoinnit.
 
 - **Integraatiotestaus** varmistaa eri komponenttien, kuten controllerien ja repositoryjen, yhteistoiminnan sekä tietojen tallentumisen oikein tietokantaan.
 
 - **End-to-end testaus** testaa koko järjestelmän toiminnan käyttäjän näkökulmasta alusta loppuun, esimerkiksi lipun luonti, tarkistus ja käytetyksi merkitseminen.

 Kaikki testit suoritettiin onnistuneesti.Testauksen avulla varmistettiin järjestelmän luotettavuus, virheettömyys ja käyttövalmius tuotantoympäristöön.

---------------------

## **7. Asennustiedot**

  ### 7.1 Tarvittavat ohjelmistot ja työkalut:

  - Java JDK 17 tai uudempi

  - Maven

  - IDE: VS Code ( tai IntelliJ IDEA)

  - PostgreSQL ( v.18 tai uusin)

  - Git

  ### 7.2 Projektin  kloonaaminen

   - Avaa terminaali tai komentorivi

  - Kloona Git-repositorio 

          git clone https://github.com/anniinakettunen/TicketGuru-OKTiimi.git

   - Siirry projektihakemistoon komennolla 
        
          cd ticketguru


### 7.3 Spring Boot -profiili

- Sovellus käyttää eri Spring Boot -profiileja tietokannan ja konfiguraation hallintaan:

    | Profiili | Kuvaus | Tietokanta |
    |----------|--------|------------|
    | dev     | Kehitysympäristö | H2 (in-memory) |
    | test      | Kehitysympäristö paikallisessa koneessa | PostgreSQL (paikallinen) |
    | prod    | Tuotantoympäristö | PostgreSQL |

- Profiilit määritellään `application-{profiili}.properties` -tiedostoissa.  

- Sovelluksen profiili aktivoidaan ympäristömuuttujalla `SPRING_PROFILES_ACTIVE`.  
  
- Esimerkiksi

        export SPRING_PROFILES_ACTIVE=prod
        mvn spring-boot:run

- jos muuttujaa ei ole asetettu, käytetään oletuksena `dev`-profiilia.

### 7.3 Kehitysympäristö:

Tässä osassa kuvataan, miten sovelluksen kehitysympäristö voidaan rakentaa uudelle koneelle.

  ### 7.3.1 PostgreSQL -tietokannan luonti paikallisesti( PostgreSQL sovelluksella)
  
  - Avaa PostgreSQL-sovellus

  - Luo uusi PostgreSQl -tietokanta esim. `ticketguru`.

  - Luo uusi käyttäjä ja salasana:

        Esimerkki:
        
        Käyttäjätunnus : ticketguru

        Salasana : okTiimi
  
  - Anna käyttäjälle käyttöoikeudet tietokantaan

  - Varmista, että `application-test.properties` tiedostossa on oikeat tiedot.

        spring.datasource.url=jdbc:postgresql://localhost:5432/ticketguru
        spring.datasource.username=<käyttätunnus>
        spring.datasource.password=<salasana>
        spring.datasource.driver-class-name=org.postgresql.Driver
        spring.jpa.hibernate.ddl-auto=none
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

        spring.sql.init.mode=always
        spring.sql.init.data-locations=classpath:data-localPostgreSQL.sql


### 7.3.2 Sovelluksen käynnistäminen
    
- Sovellus voidaan käynnistää Mavenilla valitulla profiililla (`dev`, `test` tai `prod`)

-  Esimerkki: 

        # Linux / macOS
          export SPRING_PROFILES_ACTIVE=test 
          mvn spring-boot:run

        # Windows PowerShell
          $env:SPRING_PROFILES_ACTIVE="test" 
          mvn spring-boot:run

- Tämä varmistaa, että sovellus käyttää oikeaa tietokantaa ja asetuksia valitun profiilin mukaisesti.


### 7.4 Tuotantoympäristö ( CSC Rahti)

   #### 7.4.1 PostgreSQL -tietokannan luonti ( Rahti)

 - Luo PostgreSQl-tietokannan

        Esimerkiksi:

        - Käyttäjätunnus : ticketguru

        - Salasana : okTiimi

 - Tietokannan tiedot (osoite, käyttäjätunnus ja salasana) tulee määritellä `application-prod.properties` tiedostossa:

        spring.datasource.url=jdbc:postgresql://<PALVELIN_OSOITE>:5432/ticketguru
        spring.datasource.username=<käyttäjätunnus>
        spring.datasource.password=<salasana>
        spring.datasource.driver-class-name=org.postgresql.Driver

        # Disable Hibernate automatic schema creation/update
        spring.jpa.hibernate.ddl-auto=none

        # Show SQL in console for debugging
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true


        spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

        spring.sql.init.mode=never
        spring.sql.init.schema-locations=classpath:data.sql
        spring.sql.init.data-locations=classpath:data.sql
  
#### 7.4.2 JAR-tiedoston rakentaminen

- Paikallisesti koneella

       mvn clean package

- Tämä luo `target/ticketguru-0.0.1-SNAPSHOT.jar tiedoston`


#### 7.4.3 Sovelluksen kännyistys CSC Rahtiin:

- Lataa JAR-tiedosto Rahtiin

- Määritä profiili : 

     Optional Java arguments -kenttään seuraava komento:

      --spring.profiles.active=prod // PostgreSQl 

      --spring.profiles.active=dev // H2-tietokanta


- Rahti käynnistää sovelluksen automaattisesti JAR-tiedoston ja profiilin asetusten mukaisesti.


