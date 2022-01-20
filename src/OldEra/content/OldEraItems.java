package oldera.content;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public static OldEraItems implements ContentList {
	public static Item
	
	// itemList
	metaglassShard, denseAlloy, diamondAlloy;

	@Override
	public void load(){
		metaglassShard = new Item("metaglass-shard", Color.valueOf("FFFFFF")){{
			localizedName = "Metaglass Shards";
			hardness = 1;
		}};
		denseAlloy = new Item("dense-alloy", Color.valueOf("CACACA")){{
			localizedName = "Dense Alloy";
			charge = 0.5;
		}};
		diamondAlloy = new Item("diamond-alloy", Color.valueOf("00ECFF")){{
			localizedName = "Diamond Alloy";
			charge = 10;
		}};
	}
}
