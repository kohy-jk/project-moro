# project-moro

Jan Kohnheiser
jan.kohnheiser@seznam.cz

## Database
https://supabase.com/dashboard/project/bdmlmigllsutsrnkwwvf

free plan pro tuto aplikaci postačí

### Inicializace databaze
- schemata databaze scriptem schema.sql
- v pruběhu vývoje jsem používal i inicializaci dat (data.sql)

## Aplikace
- prikladam export z Postman s requestu, ktere jsem pouzival
- pridani uzivatele (POST) a ziskani seznamu vsech (GET) je otevrene bez prihlaseni
- vse ostatni muze vzdy jen uzivatel, ktereho se to tyka dle id (GET, PUT, DELETE)
- validaci jsem puvodne resila anotacemi, ale pri validovani unikatnosti uzivatelskeho jmena jsem ustoupil k reseni validace vlastni komponentou

### Dalsi mozna prace na aplikaci
Vylepsovat a zdokonalovat aplikaci by se dalo v mnoha ohledech a refaktor by si urcite zaslouzila.

- jako zasadni nedostatek vidim absenci testů
- kazda API by si zaslouzila dokumentaci (například Swagger)

