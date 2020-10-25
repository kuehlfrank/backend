# imports
import requests 
import json
import psycopg2

# Making a get request 4152 = 84
z=84
i=0
# read all pages
while i<=z:
url[i] = "https://www.edeka.de/rezepte/rezept/suche?page="+i+"&size=50"
i+=1



# handle response
response = requests.get(url) 
data=response.json()

# help- konnte nicht sehen wie die Jsons aufgebaut sind, da ich nicht mehr auf die api komme
print(data["rezept"]

# Establish a connection to the database.
conn = psycopg2.connect(database="kuehlfrank", 
                        user="kuehlfrank",
                        host="localhost",
                        password="VOUfsdHHdsZhGSS14PxurdT1u",
                        port="5432") 

# Create a cursor.
cur = conn.cursor()

#SQL Statement -> Add recipes to database

j=0
while: j< len(url):
#Aus JSON die Elemente
name=data["name"]
ingredients=data["zutat"]
amount=data["menge"]
#FIXME
common=true
while #anzahl Elemente im JSON(50, im letzten weniger):
cur.execute("INSERT INTO RECIPE (RECIPE_ID, NAME, INGREDIENT_ID) Values ("+j+", "+name[j]+", "+ingredients[j]+")") #Quelle fürs Schema: https://riptutorial.com/de/python/example/18257/postgresql-datenbankzugriff-mit-psycopg2
cur.execute("INSERT INTO RECIPEINGREDIENT (RECIPE_ID, INGREDIENT_ID, AMOUNT, COMMON) Values ("+j+", "+ingredients[j]+", "+amount[j]+", "+common[j])
conn.commit()
j+=1

conn.close()

