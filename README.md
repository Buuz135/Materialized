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
## Developer
Add the dependecies to your gradle project
```
repositories {
    maven{
        name = "Materialized"
        url = "http://dyonovan.com/maven2/"
    }
}
dependencies {
    compile "com.buuz135.materialized:materialized:1.12-0.1+:deobf"
}
```
### Adding types
You can add more BlockMaterial or ItemMaterial types by doing 
```MaterialRegistry.INSTANCE.addItemMaterial(new ItemMaterial("ingot", new ResourceLocation(Reference.MODID, "items/metalingot"), 0));```
or 
```MaterialRegistry.INSTANCE.addBlockMaterial(new BlockMaterial("ore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metalore"), 0));```
in your PreInit.

### Creating materials
You can add material by using this method. This will all the block types and items types added by default:
```
MaterialRegistry.INSTANCE.addMaterial(MaterialInfo.builder().name("copper").color("a35309")
                   .blockParts(Arrays.asList(
                           new BlockPart("ore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
                           new BlockPart("denseore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
                           new BlockPart("lightore", 3, 3, new BlockPart.DropInfo("this:nugget", 0, 2, 2, 1)),
                           new BlockPart("block", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0))))
                   .itemParts(Arrays.asList(
                           new ItemPart("ingot"),
                           new ItemPart("nugget"),
                           new ItemPart("dust"),
                           new ItemPart("tinydust"),
                           new ItemPart("plate"),
                           new ItemPart("gear")))
                   .build());
```
Once you have registered the material you can access to it by doing ```MaterialRegistry.INSTANCE.getItem("copper", "nugget")```.