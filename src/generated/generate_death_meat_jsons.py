import json
from pathlib import Path

MOD_ID = "alltheshi"
MODEL_DIR = Path("resources/assets")

MEATS = ["chicken", "beef", "mutton", "porkchop", "rabbit"]

MODEL_DIR.mkdir(parents=True, exist_ok=True)

for meat in MEATS:
    for prefix in ["death_", "cooked_death_"]:
        item = prefix + meat

        model = {
            "parent": "minecraft:item/generated",
            "textures": {
                "layer0": f"{MOD_ID}:item/{item}"
            }
        }

        path = MODEL_DIR / f"{item}.json"
        path.write_text(json.dumps(model, indent=2) + "\n")
        print(f"Generated {path}")
