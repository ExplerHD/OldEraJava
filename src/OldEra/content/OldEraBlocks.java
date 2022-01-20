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
		
		advancedOverdriveProjector = new OverdriveProjector("advanced-overdrive-projector") {{
			requirements (Category.effect, with(
				Items.lead, 750,
				Items.silicon, 600,
				Items.plastanium, 200,
				Items.surgeAlloy, 100,
				OldEraBlocks.diamondAlloy, 10
			));
			localizedName = "Advanced Overdrive Projector";
			size = 4;
			health = 50 * size * size;
			consumes.power(10f);
			speedBoost = 4;
			hasBoost = false;
			consumes.items(with(OldEraItems.diamondAlloy, 1));
		}};
		
		verticalShieldProjector = new ForceProjector("vertical-shield-projector") {{
			requirements (Category.effect, with(
				Items.copper, 1000,
				Items.silicon, 500,
				Items.surgeAlloy, 100,
				Items.diamondAlloy, 100
			));
			localizedName = "Vertical Force Projector";
			size = 4;
			health = 40 * size * size;
			useTime = 300;
			radius = 150;
			phaseRadiusBoost = 150;
			shieldHealth = 2000;
			cooldownNormal = 1.77;
			cooldownLiquid = 1.1;
			cooldownBrokenBase = 0.56;
			consumes.item(Items.phaseFabric).boost();
			consumes.power(8);
		}};
		// effect end

		// distribution begin
		denseAlloyBridge = new BufferedItemBridge("dense-alloy-bridge") {{
			requirements(Category.distribution, with(Items.lead, 10, OldEraItems.denseAlloy, 6));
			fadeIn = moveArrows = false;
			localizedName = "Dense Alloy Bridge";
			size = 1;
			health = 70;
			range = 6;
			speed = 100;
			arrowSpacing = 6;
			bufferCapacity = 14;
		}};
		
		denseAlloyConveyor = new Conveyor("dense-alloy-conveyor"){{
			requirements(Category.distribution, with(Items.copper, 1, Items.lead, 1, Items.titanium, 1, OldEraItems.denseAlloy, 1));
			localizedName = "Dense Alloy Conveyor";
			health = 140;
			speed = 0.14;
			displayedSpeed = 15;
		}};
		// distribution end

		// drills begin
		slagExtractor = new SolidPump("slag-extractor"){{
			requirements(Category.production, with(Items.copper, 165, Items.silicon, 160, Items.titanium, 150, Items.thorium, 100, Items.metaglass, 150));
			localizedName = "Slag Extractor";
			size = 2;
			result = Liquids.slag;
			pumpAmount = 0.5;
			consumes.power(6);
			attribute = Attribute.heat;
			health = 40 * size * size;
		}};
		// drills end

		// environment begin
		metaglassShard = new OreBlock(OldEraItems.metaglassShard);
		
		blackstone = new Floor("blackstone"){{
			variants = 3;
		}};
		
		blackstoneWall = new StaticWall("blackstone-wall"){{
			variants = 2;
		}};
		// environment end

		// power begin
        slagGenerator = new SingleTypeGenerator("slag-generator"){{
            requirements(Category.power, with(Items.copper, 70, Items.titanium, 50, Items.lead, 100, Items.silicon, 65, Items.metaglass, 50, Items.thorium, 70));
            localizedName = "Slag Generator";
            powerProduction = 18f;
            hasLiquids = true;
            hasItems = true;
            size = 3;
            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.03f;

            consumes.liquid(Liquids.slag, 0.1f);
        }};
        // power end

		// turret begin
		mincer = new PowerTurret("mincer"){{
			requirements(Category.turret, with(Items.copper, 50, Items.lead, 50));
			localizedName = "Mincer";
			range = 110;
			chargeTime = 40;
			chargeMaxDelay = 30;
			chargeEffects = 7;
			recoil = 2;
			reloadTime = 80;
			cooldown = 3;
			powerUse = 1;
			shootEffect = Fx.lancerLaserShoot;
			smokeEffect = Fx.none;
			chargeEffect = Fx.lancerLaserCharge;
			chargeBeginEffect = Fx.lancerLaserChargeBegin;
			heatColor = Color.red;
			size = 1;
			health = 260;
			shootType = new LaserBulletType(){{
				colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
				hitEffect = Fx.hitLancer;
				hitSize = 4;
				lifetime = 16;
				drawSize = 400;
				length = 173;
				damage = 70;
				ammoMultiplier = 1;
			}};
		}};
		mikr = new ItemTurret("mikr"){{
			requirements(Category.turret, with(Items.copper, 70, Items.lead, 50, OldEraItems.denseAlloy, 25));
			ammo(
                Items.copper, Bullets.standardCopper,
                Items.graphite, Bullets.standardDense,
                Items.pyratite, Bullets.standardIncendiary,
                Items.silicon, Bullets.standardHoming,
                OldEraItems.denseAlloy, OldEraBullets.mikrLaser
            );
			localizedName = "Mikr";
			spread = 4;
			shots = 2;
			shootCone = 15;
			reloadTime = 10;
			range = 110;
			restitution = 0.03;
			alternate = true;
			rotateSpeed = 10;
			inaccuracy = 0;
			size = 1;
			hp = 260;
		}};
		
		chain = new ItemTurret("chain"){{
			requirements(Category.turret, with(Items.copper, 125, Items.silicon, 75, Items.titanium, 45));
			ammo(
				Items.pyratite, Bullets.standardIncendiary,
				Items.lead, OldEraBullets.chainLead,
				Items.scrap, OldEraBullets.chainScrap,
				Items.plastanium, OldEraBullets.chainPlastanium,
				OldEraItems.denseAlloy, OldEraBullets.chainDenseAlloy
			);
			localizedName = "Chain";
			size = 1;
			health = 180;
			reloadTime = 10;
			range = 240;
			shots = 1;
			targetAir = true;
			targetGround = true;
			recoilAmount = 2;
			restitution = 0.01;
			rotateSpeed = 20;
			shootSound = Sounds.shootBig;
		}};
		
		rod = new ItemTurret("rod"){{
			requirements(Category.turret, with(Items.copper, 75, Items.lead, 25));
			ammo(
				Items.copper, OldEraBullets.rodCopper,
				Items.lead, OldEraBullets.rodLead,
				Items.silicon, OldEraBullets.rodSilicon
			);
			localizedName = "Rod";
			size = 1;
			health = 265;
			reloadTime = 10;
			range = 172;
			inaccuracy = 2;
			recoilAmount = 2;
			restitution = 0.07;
			ammoUseEffect = Fx.casing3double;
		}};
	}
}