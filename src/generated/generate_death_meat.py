import json
from pathlib import Path

MOD_ID = "alltheshi"
RECIPES_DIR = Path("resources/recipes")

MEATS = ["chicken", "beef", "mutton", "porkchop", "rabbit"]
_COOKING_TYPES = {
     "smelting": 200,
     "smoking": 100,
     "campfire_cooking": 600,
 }

for meat in MEATS:
    for cooking_type, cooking_duration in _COOKING_TYPES.items():
        recipe = {
            "type": f"minecraft:{cooking_type}",
            "ingredient": {
                "item": f"minecraft:cooked_{meat}"
            },
            "result": {
                "item": f"{MOD_ID}:death_{meat}"
            },
            "experience": 0.1,
            "cookingtime": cooking_duration,
        }

        path = RECIPES_DIR / f"death_{meat}_{cooking_type}.json"
        path.parent.mkdir(parents=True, exist_ok=True)

        path.write_text(json.dumps(recipe, indent=2) + "\n")
        print(f"Generated {path}")

for meat in MEATS:
    for cooking_type, cooking_duration in _COOKING_TYPES.items():
        recipe = {
            "type": f"minecraft:{cooking_type}",
            "ingredient": {
            "item": f"{MOD_ID}:death_{meat}"
            },
            "result": {
                "item": f"{MOD_ID}:cooked_death_{meat}"
            },
            "experience": 0.1,
            "cookingtime": cooking_duration,
        }

        path = RECIPES_DIR / f"cooked_death_{meat}_{cooking_type}.json"
        path.parent.mkdir(parents=True, exist_ok=True)

        path.write_text(json.dumps(recipe, indent=2) + "\n")
        print(f"Generated {path}")