import os
import requests 
import json
import psycopg2
import time
import random
from selenium import webdriver
from selenium.webdriver.common.by import By

baseUrl = "https://www.rewe.de/restservices/recipe/search"
pageQuery = "?pageNumber="
allrecipies = []

def getDriver():
    fp = webdriver.FirefoxProfile()
    fp.set_preference("devtools.jsonview.enabled", False)
    driver = webdriver.Firefox(firefox_profile=fp)
    return driver

def getJson(driver, url):
    driver.get(baseUrl)
    jsonText = driver.find_element_by_xpath("//pre").get_attribute("innerHTML")
    return json.loads(jsonText)

def getMeta(driver):
    data = getJson(driver, baseUrl)
    return data["meta"]

def getRecipies(driver, pageNumber):
    url = baseUrl + pageQuery + str(pageNumber)
    data = getJson(driver, url)
    return data["recipeTiles"]

def getDbConnection():
    # Establish a connection to the database.
    conn = psycopg2.connect(database="kuehlfrank", 
                            user="kuehlfrank",
                            host="localhost",
                            password="VOUfsdHHdsZhGSS14PxurdT1u",
                            port="5432")
    return conn

def addRecipe(cursor, recipe):
    print("Adding \"" + recipe["title"] + "\"")
    driver.get("https://www.rewe.de/rezepte" + recipe["jcrPath"])
    if (not recipe["title"] in driver.title):
        print("failed to get",  recipe["title"])
        return
    quantity = driver.find_element_by_xpath("//span[contains(@class,'quantity-display')]").get_attribute("innerHTML")
    ingredients = list(map(lambda e: e.get_attribute("innerHTML"), driver.find_elements_by_xpath("//span[contains(@class,'ingredient-label-text')]")))
    
    print(quantity)
    print(ingredients)

    return { "title": recipe["title"], "external_id": recipe["jcrIdentifier"], "external_source": "Rewe Rezepte", "quantity": quantity, "ingredients": ingredients}

def addPageRecipes(driver, cursor, pageRecipes):
    scrapedRecipes = []
    for recipe in pageRecipes:
        scrapedRecipe = addRecipe(cursor, recipe)
        if(scrapedRecipe):
            scrapedRecipes.append(scrapedRecipe)

        driver.delete_all_cookies()
        sleep_time = random.uniform(0.5,1)
        print("Sleeping for", sleep_time)
        time.sleep(sleep_time)
    return scrapedRecipes

def saveResults(pageNumber, recipies):
    if not os.path.exists('recipies'):
        os.makedirs('recipies')
    with open(f"recipies/recipies_{pageNumber}.json", "w", encoding='utf8') as f: 
        f.write(json.dumps(recipies)) 

    if not os.path.exists('ingredients'):
        os.makedirs('ingredients')
    allingredients = list(set([ingredient for l in list(map(lambda i: i["ingredients"], recipies)) for ingredient in l]))
    with open(f"ingredients/ingredients_{pageNumber}.txt", "w", encoding='utf8') as f: 
        f.write("\n".join(allingredients)) 

    if not os.path.exists('quantities'):
        os.makedirs('quantities')
    allquantities = list(set(map(lambda i: i["quantity"], recipies)))
    with open(f"quantities/quantities_{pageNumber}.txt", "w", encoding='utf8') as f: 
        f.write("\n".join(allquantities))

driver = getDriver()
meta = getMeta(driver)
print("Meta: ", meta)

# Create a cursor.
conn = getDbConnection()
cur = conn.cursor()

pageNumbers = list(set(range(1, int(meta["pages"]) + 1)).difference(set([16,27,63,75,116,121])))
random.shuffle(pageNumbers)
for pageNumber in pageNumbers:
    print("Page", pageNumber)
    pageRecipes = getRecipies(driver, pageNumber)
    random.shuffle(pageRecipes)
    scrapedRecipes = addPageRecipes(driver, cur, pageRecipes)
    saveResults(pageNumber, scrapedRecipes)

#SQL Statement -> Add recipes to database
# j=0
# while: j< len(url):
#     #Aus JSON die Elemente
#     name=data["name"]
#     ingredients=data["zutat"]
#     amount=data["menge"]
#     #FIXME
#     common=true
#     while #anzahl Elemente im JSON(50, im letzten weniger):
#     cur.execute("INSERT INTO RECIPE (RECIPE_ID, NAME, INGREDIENT_ID) Values ("+j+", "+name[j]+", "+ingredients[j]+")") #Quelle fürs Schema: https://riptutorial.com/de/python/example/18257/postgresql-datenbankzugriff-mit-psycopg2
#     cur.execute("INSERT INTO RECIPEINGREDIENT (RECIPE_ID, INGREDIENT_ID, AMOUNT, COMMON) Values ("+j+", "+ingredients[j]+", "+amount[j]+", "+common[j])
#     conn.commit()
#     j+=1

conn.close()
driver.close()
driver.quit()


