package me.c10coding.files;


import me.c10coding.OneBlock;
import me.c10coding.utils.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class PlayerAreaConfigManager extends ConfigManager {

    private OneBlock plugin;
    private LocationSerializer ls;

    public PlayerAreaConfigManager(OneBlock plugin, String fileName) {
        super(plugin, fileName);
        this.ls = new LocationSerializer(config);
    }

    /*
    Adds the block to the list of blocks in a player's area
     */
    public void addBlock(Block b){
        reloadConfig();
        List<String> blocksList = config.getStringList("Blocks");
        if(!blocksList.contains(ls.toString(b.getLocation()))){
            blocksList.add(ls.toString(b.getLocation()));
            config.set("Blocks", blocksList);
        }
        saveConfig();
    }

    /*
    Removes the block from the list of blocks in a player's area
     */
    public void removeBlock(Block b){
        reloadConfig();
        List<String> blocksList = config.getStringList("Blocks");
        if(blocksList.contains(ls.toString(b.getLocation()))){
            blocksList.remove(ls.toString(b.getLocation()));
            config.set("Blocks", blocksList);
        }
        saveConfig();
    }

    /*
    Gets the block's locations from config and then uses Location Serializer to turn them back into blocks
     */
    public List<Block> getAllBlocks(){
        List<Block> blocks = new ArrayList<>();
        List<String> blocksInConfig = config.getStringList("Blocks");
        for(String s : blocksInConfig){
            Location loc = ls.toLocationFromLine(s);
            Block b = loc.getBlock();
            blocks.add(b);
        }
        return blocks;
    }

    public void deleteFile(){
        file.delete();
    }

}
