package oldera.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.entities.bullet.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.units.*;
import mindustry.world.meta.*;
import mindustry.world.draw.*;

import oldera.content.*;
import oldera.world.blocks.*;
import oldera.graphics.*;

import static mindustry.type.ItemStack.with;

public class OldEraBlocks implements ContentList {
	public static Block
	// production
	arcSmelter, crystallizer, flakeMerger,
	
	// effect
	verticalCore, verticalWarehouse, advancedProjector, verticalProjector,
	
	// distribution
	denseAlloyBridge, denseAlloyConveyor,
	
	// drills
	slagExtractor,
	
	// environment
	metaglassShard, blackstone, blackstoneWall,
	
	// power
	slagGenerator,
	
	// turrets
	mincer, mikr, chain, rod, nokr, miniMachineGun, spliterity, multiMachineGun, electricLaserGun,
	
	// units
	revivalFactory, additiveUpgrader, multiplicativeUpgrader, exponentialUpgrader, tetrativeUpgrader;
	
	public void load() {
		// production begin
		arcSmelter = new GenericCrafter("arc-smelter") {{
			requirements (Category.crafting, with(
				Items.copper, 60,
				Items.lead, 35,
				Items.graphite, 15
			));
			localizedName = "Arc Smelter";
			size = 2;
			health = 30 * size * size;
			hasItems = true;
			drawer = new DrawSmelter(Color.valueOf("FFB05C"));
			craftTime = 30;
			craftEffect = Fx.smelt;
			consumes.items(
				new ItemStack(Items.copper, 1),
				new ItemStack(Items.lead, 1)
			);
			consumes.power(0.5f);
			outputItem = new ItemStack(OldEraItems.denseAlloy, 1);
		}};
		
		crystallizer = new GenericCrafter("crystallizer") {{
			requirements (Category.crafting, with(
				Items.copper, 400,
				Items.lead, 450,
				Items.titanium, 300,
				Items.thorium, 200,
				Items.surgeAlloy, 100,
				Items silicon, 400
			));
			localizedName = "Crystallizer";
			size = 3;
			health = 40 * size * size;
			hasItems = true;
			drawer = new DrawSmelter(Color.valueOf("00B7FF"));
			craftTime = 120;
			craftEffect = OldEraFx.crystalcraft;
			consumes.items(
				new ItemStack(Items.copper, 4),
				new ItemStack(Items.lead, 5),
				new ItemStack(Items.titanium, 3),
				new ItemStack(Items.thorium, 2),
				new ItemStack(Items.surgeAlloy, 1),
				new ItemStack(Items.silicon, 2)
			));
			consumes.power(4f);
			outputItem = new ItemStack(OldEraItems.diamondAlloy, 1);
		}};
		
		flakeMerger = new GenericCrafter("flake-merger") {{
			requirements (Category.crafting, with(
				Items.copper, 60,
				Items.lead, 90,
				Items.graphite, 60,
				Items.silicon, 30
			));
			localizedName = "Flake Merger";
			size = 2;
			health = 40 * size * size;
			hasItems = true;
			drawer = new DrawSmelter(Color.valueOf("FFB05C"));
			craftTime = 30;
			craftEffect = Fx.smelt;
			consumes.items(
				new ItemStack(OldEraItems.metaglassShard, 4)
			);
			consumes.power(0.5f);
			ambientSound = Sounds.smelter;
			ambientSoundVolume = 0.01;
			outputItem = new ItemStack(Items.metaglass, 1);
		}};
		// production end

		// effect begin
		verticalCore = new CoreBlock("vertical-core") {{
			requirements (Category.effect, with(
				Items.copper, 5000,
				Items.lead, 5000,
				Items.silicon, 3000,
				Items.thorium, 3000,
				Items.surgeAlloy, 1000,
				OldEraItems.diamondAlloy, 100
			));
			localizedName = "Vertical Core";
			health = 10000;
			hasItems = true;
			hasPower = true;
			outputPower = true;
			consumesPower = true;
			unitType = OldEraUnits.verticalizer;
			itemCapacity = 21000;
			consumes.powerBuffered(100000f);
			baseExplosiveness = 10;
			size = 6;
		}};
		
		verticalWarehouse = new StorageBlock("vertical-warehouse") {{
			requirements (Category.effect, with(
				Items.titanium, 1000,
				Items.thorium, 500,
				Items.surgeAlloy, 100
			));
			localizedName = "Vertical Warehouse";
			health = 50 * size * size;
			hasItems = true;
			hasPower = true;
			outputPower = true;
			consumesPower = true;
			itemCapacity = 5000;
			consumes.powerBuffered(50000f);
			baseExplosiveness = 5;
			size = 4;
		}};
		
		advancedProjector = new OverdriveProjector("advanced-projector") {{
			requirements (Category.effect, with(
				Items.lead, 750,
				Items.silicon, 600,
				Items.plastanium, 200,
				Items.surgeAlloy, 100,
				OldEraItems.diamondAlloy, 10
			));
			localizedName = "Advanced Projector";
			consumes.power(10f);
			speedBoost = 4;
			hasBoost = false;
			consumes.items(with(OldEraItems.diamondAlloy, 1));
		}};
	}
}
