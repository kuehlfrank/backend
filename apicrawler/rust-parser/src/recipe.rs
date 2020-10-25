use std::fs::{read_dir, File};
use std::io::prelude::*;
use std::io::{self, BufReader, BufWriter};

use regex::Regex;
use serde::{Deserialize, Serialize};

#[derive(Deserialize)]
pub struct InputRecipe {
    title: String,
    external_id: String,
    external_source: String,
    quantity: String,
    ingredients: Vec<String>,
}

#[derive(Serialize)]
pub struct Ingredient {
    amount: Option<usize>,
    unit: Option<String>,
    name: String,
}

#[derive(Serialize)]
pub struct OutputRecipe {
    title: String,
    external_id: String,
    external_source: String,
    quantity: String,
    ingredients: Vec<Ingredient>,
}

pub fn read_input_recipes(file_path: &str) -> io::Result<Vec<InputRecipe>> {
    let file = File::open(file_path)?;
    let buffered_reader = BufReader::new(file);

    let input_recipes: Vec<InputRecipe> = serde_json::from_reader(buffered_reader)?;

    Ok(input_recipes)
}

pub fn write_output_recipes(file_path: &str, output_recipes: &Vec<OutputRecipe>) -> io::Result<()> {
    let file = File::create(file_path)?;
    let buffered_writer = BufWriter::new(file);

    serde_json::to_writer(buffered_writer, output_recipes)?;

    Ok(())
}

pub fn generate_ingredient(
    re1: &Regex,
    re2: &Regex,
    re3: &Regex,
    ingredient: &str,
) -> Option<Ingredient> {
    let ingredient = ingredient.trim();

    if re1.is_match(ingredient) {
        let caps = re1.captures(ingredient).unwrap();

        let amount: usize = caps
            .name("amount")
            .map_or(0, |c| c.as_str().parse().unwrap_or(0)); // TODO default 0?
        let unit: String = String::from(caps.name("unit").map_or("", |c| c.as_str()));
        let name: String = String::from(caps.name("ingredient").map_or("", |c| c.as_str()));

        Some(Ingredient {
            amount: Some(amount),
            unit: Some(unit),
            name,
        })
    } else if re2.is_match(ingredient) {
        let caps = re2.captures(ingredient).unwrap();

        let amount: usize = caps
            .name("amount")
            .map_or(0, |c| c.as_str().parse().unwrap_or(0)); // TODO default 0?
        let name: String = String::from(caps.name("ingredient").map_or("", |c| c.as_str()));

        Some(Ingredient {
            amount: Some(amount),
            unit: None,
            name,
        })
    } else if re3.is_match(ingredient) {
        let caps = re3.captures(ingredient).unwrap();

        let name: String = String::from(caps.name("ingredient").map_or("", |c| c.as_str()));

        Some(Ingredient {
            amount: None,
            unit: None,
            name,
        })
    } else {
        None
    }
}

pub fn beautify_json(file_path: &str, re_units: String) -> io::Result<()> {
    let re1 = Regex::new(&format!(
        "^(?P<amount>[0-9]+(?:\\.[0-9]+)?) *(?P<unit>{}) *(?P<ingredient>.+)$",
        re_units
    ))
    .unwrap();
    let re2 = Regex::new(r"^(?P<amount>[0-9]+(?:\.[0-9]+)?) *(?P<ingredient>.+)$").unwrap();
    let re3 = Regex::new(r"^(?P<ingredient>.+)$").unwrap();

    let input_recipes = read_input_recipes(file_path)?;
    let mut output_recipes: Vec<OutputRecipe> = Vec::new();

    for recipe in input_recipes {
        let mut output_ingredients: Vec<Ingredient> = Vec::new();

        for ingredient in recipe.ingredients {
            output_ingredients.push(generate_ingredient(&re1, &re2, &re3, &ingredient).unwrap());
        }

        output_recipes.push(OutputRecipe {
            title: recipe.title,
            external_id: recipe.external_id,
            external_source: recipe.external_source,
            quantity: recipe.quantity,
            ingredients: output_ingredients,
        });
    }

    write_output_recipes(&format!("{}.new.json", file_path), &output_recipes)?;

    Ok(())
}