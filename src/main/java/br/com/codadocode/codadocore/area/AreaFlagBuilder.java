package br.com.codadocode.codadocore.area;

import java.util.HashMap;
import java.util.Map;

public class AreaFlagBuilder {
    public static Map<AREA_FLAG, Boolean> buildDefaultFlags()   {
        Map<AREA_FLAG, Boolean> defaultflags = new HashMap<>();

        defaultflags.put(AREA_FLAG.BREAK, false);
        defaultflags.put(AREA_FLAG.CAN_PLACE, false);
        defaultflags.put(AREA_FLAG.INTERACT, false);
        defaultflags.put(AREA_FLAG.CHEST_USE, false);
        defaultflags.put(AREA_FLAG.CONTAINER_USE, false);
        defaultflags.put(AREA_FLAG.PVP, false);
        defaultflags.put(AREA_FLAG.KILL_MONSTERS, true);
        defaultflags.put(AREA_FLAG.KILL_MOBS, true);
        defaultflags.put(AREA_FLAG.CAN_MOVE, true);
        defaultflags.put(AREA_FLAG.CAN_ENTER, true);
        defaultflags.put(AREA_FLAG.CAN_EXIT, true);
        defaultflags.put(AREA_FLAG.TNT_EXPLOSION, false);
        defaultflags.put(AREA_FLAG.MONSTER_EXPLOSION, false);
        defaultflags.put(AREA_FLAG.CAN_DROP_ITEMS, true);
        defaultflags.put(AREA_FLAG.CAN_CHAT, true);

        return defaultflags;
    }
}
