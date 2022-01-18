package oldera;

import oldera.content.*;
import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class OldEra extends Mod{
	public OldEra() {
		Events.on(ClientLoadEvent.class, e -> {
			Time.runTask(10f, () -> {
				BaseDialog dialog = new BaseDialog("Old Era Revival Java Edition");
				dialog.cont.add("For best experience, Please download the music pack by click this button");
				dialog.cont.button("OK", dialog::hide).size(100f, 50f);
				dialog.show();
			});
		});
	}
	
	@Override
	public void loadContent(){
		new OldEraBlocks().load();
		new OldEraUnits().load();
		new OldEraItems().load();
		new OldEraPlanets().load();
		new OldEraBullets().load();
	}
}
