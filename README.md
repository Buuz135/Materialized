# Materialized
Materialized allows you to add any Material (Copper, Tin, etc) to Minecraft just once. Modders and Users can do it but in different ways. Currently this are the supported materials by default:

## Block Material
* Ore
* Denseore
* LightOre
* Block

## Item Material
* Ingot
* Nugget
* Dust
* TinyDust
* Gear
* Plate

## User
Any player can add a material with a json file in `%minecraftroot%/config/materialized/materials.json`
That file will have this style:
```JSON
[ //A collection of material as JSON objects
  {
    "name": "copper", //Name of the material
    "color": "a35309", //Color of the material
    "blockParts": [ // A collection of Block Types displayed above
      {
        "type": "ore", 
        "harvestLevel": 2,
        "hardness": 3,
        "oredict": [
          "ore:this"
        ],
        "drop": {
          "item": "this:ore",
          "meta": 0,
          "amount": 1,
          "fortuneModifier": 0,
          "xpDrop": 0
        }
      },
      {
        "type": "denseore",
        "harvestLevel": 2,
        "hardness": 4,
        "oredict": [
          "denseOre:this"
        ],
        "drop": {
          "item": "this:ore",
          "meta": 0,
          "amount": 3,
          "fortuneModifier": 1,
          "xpDrop": 0
        }
      },
      {
        "type": "lightore",
        "harvestLevel": 2,
        "hardness": 2,
        "oredict": [
          "lightOre:this"
        ],
        "drop": {
          "item": "this:nugget",
          "meta": 0,
          "amount": 2,
          "fortuneModifier": 2,
          "xpDrop": 1
        }
      }
    ],
    "itemParts": [
      {
        "type": "ingot",
        "oredict": [
          "ingot:this"
        ]
      },
      {
        "type": "nugget"
      }
    ]
  }
]
```