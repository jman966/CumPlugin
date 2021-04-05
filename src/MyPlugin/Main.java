package MyPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.Iterable;


import org.bukkit.entity.Player.Spigot;

@SuppressWarnings("unused")
public class Main extends JavaPlugin 
{
	@Override
	public void onEnable()
	{
		
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(label.equalsIgnoreCase("explode"))
		{
			explode(sender, args);
		}
		else if(label.equalsIgnoreCase("ekick"))
		{
			ekick(sender, args);
		}
		else if(label.equalsIgnoreCase("cum"))
		{
			cum(sender, args);
		}
		return true;
	}
	
	
	public boolean ekick(CommandSender sender, String[] args) 
	{
		if(args.length >= 2)
		{
			Player player = (Player)sender;
			sender.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Incorrect Syntax Fuck You!");
			return true;
		}
		Player player = Bukkit.getServer().getPlayer(args[0].toString());
		player.kickPlayer(ChatColor.BOLD + "" + ChatColor.YELLOW + "You have been death banned!");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + args[0] + ChatColor.YELLOW + " has been death banned!");
		return true;
	}
	public boolean explode(CommandSender sender, String[] args)
	{
		Player player = (Player)sender;
		if(args.length == 0)
		{
			TNTPrimed tnt = (TNTPrimed)player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
			tnt.setFuseTicks(0);
		}
		else if(args.length == 1)
		{
			float r = Float.parseFloat(args[0]);
			if(r > 100)
			{
				player.sendMessage(ChatColor.RED + "Do not set a power of " + ChatColor.BOLD + "over 100!" + ChatColor.RED + " (unstable)");
				return true;
			}
			else
			{
				TNTPrimed tnt = (TNTPrimed)player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
				tnt.setYield(r);
				tnt.setFuseTicks(0);
			}
		}
		else
		{
			sender.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "Incorrect Syntax Fuck You!");
		}
		
		return true;
	}
	public static boolean cum(CommandSender sender, String[] args)
	{
		Player player = (Player) sender;
		Inventory inv = player.getInventory();
		int isCum = 0;
		
		if(args.length > 0)
		{
			player.sendMessage("wghat");
			return true;
		}
		
		for(int i = 0; i <= inv.getSize(); i++) 
		{
			if(inv.getItem(i) != null)
			{
				ItemStack b = inv.getItem(i);
				String bType = b.getType().toString();
				if(isCum >= 3)
				{
					Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " just came " + isCum + " times!");
					player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You're out of cum!");
					isCum = 0;
					return true;
				}
				else if(b.getType() == Material.BUCKET)
				{
					b.setType(Material.MILK_BUCKET);
					ItemMeta bIm = b.getItemMeta();
					bIm.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Cum Bucket");
					b.setItemMeta(bIm);
					++isCum;
				}
			}
			if(i >= 41 && isCum == 1)
			{
				Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " just came once!");
			}
			else if(i >= 41)
			{
				Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " just came " + isCum + " times!");
			}
			
		}
		return true;
	}
}

