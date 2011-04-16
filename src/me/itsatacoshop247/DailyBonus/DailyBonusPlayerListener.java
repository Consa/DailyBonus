package me.itsatacoshop247.DailyBonus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import com.nijiko.coelho.iConomy.iConomy;
import com.nijiko.coelho.iConomy.system.Account;


public class DailyBonusPlayerListener extends PlayerListener{
	
	public static DailyBonus plugin;
	public static PermissionHandler Permissions;
	
	public DailyBonusPlayerListener(DailyBonus instance) {
		plugin = instance;
	}
	
	@SuppressWarnings("deprecation")
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		addnewplayerfile(player);
		String noniconamount = Integer.toString(DailyBonusLoadSettings.noniconomyitemamnt);
		String VIPnoniconamount = Integer.toString(DailyBonusLoadSettings.VIPnoniconomyitemamnt);
		String noniconitem = Integer.toString(DailyBonusLoadSettings.noniconomyitem);
		String VIPnoniconitem = Integer.toString(DailyBonusLoadSettings.VIPnoniconomyitem);
		//int time = (DailyBonusLoadSettings.waittime);
		int randomnumber = 0;
		if(!CheckLastLogin(player)){
			return;
			//ICONOMY VIP
			/*if(time != 0){
		        long t0, t1;
		        t0 =  System.currentTimeMillis();
		        do
		        {
		            t1 = System.currentTimeMillis();
		        }
		        while ((t1 - t0) < (time * 1000));
		    }*/
			/*} else if(!CheckLastLogin(player)){
				return;*/
			}
		if(DailyBonusLoadSettings.IconomyAmountVariation != 0){
			randomnumber = (int)(Math.random()*(DailyBonusLoadSettings.IconomyAmountVariation + DailyBonusLoadSettings.IconomyAmountVariation));
			randomnumber -= DailyBonusLoadSettings.IconomyAmountVariation;
		}
		String amount = Integer.toString(DailyBonusLoadSettings.amntonlogin + randomnumber);
		String VIPamount = Integer.toString(DailyBonusLoadSettings.VIPamntonlogin + randomnumber);
        	if (DailyBonusLoadSettings.useicon && iConomy.getBank().hasAccount(player.getName()) && hasPermission(player, "DailyBonus.getVIP")) {	
				Account account = iConomy.getBank().getAccount(player.getName());
				double balance = account.getBalance();
				balance +=(DailyBonusLoadSettings.VIPamntonlogin + randomnumber);
				account.setBalance(balance);
				player.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.VIPmessage.replace("$amount$", VIPamount).replace("$currecny_name$", iConomy.getBank().getCurrency()));
        	}//ICOMONY NO VIP
        	else if (DailyBonusLoadSettings.useicon && iConomy.getBank().hasAccount(player.getName()) && hasPermission(player, "DailyBonus.get")) {	
				Account account = iConomy.getBank().getAccount(player.getName());
				double balance = account.getBalance();
				balance +=(DailyBonusLoadSettings.amntonlogin + randomnumber);
				account.setBalance(balance);
				player.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.message.replace("$amount$", amount).replace("$currecny_name$", iConomy.getBank().getCurrency()));
        	}//NO ICONOMY VIP
        	else if(!DailyBonusLoadSettings.useicon && hasPermission(player, "DailyBonus.getVIP")){
	        	player.getInventory().addItem(new ItemStack(DailyBonusLoadSettings.VIPnoniconomyitem, DailyBonusLoadSettings.VIPnoniconomyitemamnt));
	        	player.updateInventory();
	        	player.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.VIPnoniconmessage.replace("$amount$", VIPnoniconamount).replace("$item$", VIPnoniconitem));
        	}//NO ICONOMY NO VIP
        	else if(!DailyBonusLoadSettings.useicon && hasPermission(player, "DailyBonus.getVIP")){
	        	player.getInventory().addItem(new ItemStack(DailyBonusLoadSettings.noniconomyitem, DailyBonusLoadSettings.VIPnoniconomyitemamnt));
	        	player.updateInventory();
	        	player.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.noniconmessage.replace("$amount$", noniconamount).replace("$item$", noniconitem));
        	}	
}

	@SuppressWarnings("deprecation")
	private void addnewplayerfile(Player p) {
		File file = new File ("plugins/DailyBonus/"+p.getName()+".txt");
	    if (!file.exists()){
	    	try {
				file.createNewFile();
			    Calendar cal2 = Calendar.getInstance();
			    int day = cal2.get(Calendar.DATE);
			    int month = cal2.get(Calendar.MONTH) + 1;
			    int day2 = day;
			    int month2 = month;
			    BufferedWriter out = new BufferedWriter(new FileWriter("plugins/DailyBonus/"+p.getName()+".txt", true));
			    out.write(""+day2);
			    out.newLine();
			    out.write(""+month2);
			    out.newLine();
			    out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    int randomnumber = 0;
		String noniconamount = Integer.toString(DailyBonusLoadSettings.noniconomyitemamnt);
		String VIPnoniconamount = Integer.toString(DailyBonusLoadSettings.VIPnoniconomyitemamnt);
		String noniconitem = Integer.toString(DailyBonusLoadSettings.noniconomyitem);
		String VIPnoniconitem = Integer.toString(DailyBonusLoadSettings.VIPnoniconomyitem);
		if(DailyBonusLoadSettings.IconomyAmountVariation != 0){
			randomnumber = (int)(Math.random()*(DailyBonusLoadSettings.IconomyAmountVariation + DailyBonusLoadSettings.IconomyAmountVariation));
			randomnumber -= DailyBonusLoadSettings.IconomyAmountVariation;
		}
		String amount = Integer.toString(DailyBonusLoadSettings.amntonlogin + randomnumber);
		String VIPamount = Integer.toString(DailyBonusLoadSettings.VIPamntonlogin + randomnumber);
        	if (DailyBonusLoadSettings.useicon && iConomy.getBank().hasAccount(p.getName()) && hasPermission(p, "DailyBonus.getVIP")) {	
				Account account = iConomy.getBank().getAccount(p.getName());
				double balance = account.getBalance();
				balance +=(DailyBonusLoadSettings.VIPamntonlogin + randomnumber);
				account.setBalance(balance);
				p.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.VIPmessage.replace("$amount$", VIPamount).replace("$currecny_name$", iConomy.getBank().getCurrency()));
        	}//ICOMONY NO VIP
        	else if (DailyBonusLoadSettings.useicon && iConomy.getBank().hasAccount(p.getName()) && hasPermission(p, "DailyBonus.get")) {	
				Account account = iConomy.getBank().getAccount(p.getName());
				double balance = account.getBalance();
				balance +=(DailyBonusLoadSettings.amntonlogin + randomnumber);
				account.setBalance(balance);
				p.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.message.replace("$amount$", amount).replace("$currecny_name$", iConomy.getBank().getCurrency()));
        	}//NO ICONOMY VIP
        	else if(!DailyBonusLoadSettings.useicon && hasPermission(p, "DailyBonus.getVIP")){
	        	p.getInventory().addItem(new ItemStack(DailyBonusLoadSettings.VIPnoniconomyitem, DailyBonusLoadSettings.VIPnoniconomyitemamnt));
	        	p.updateInventory();
	        	p.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.VIPnoniconmessage.replace("$amount$", VIPnoniconamount).replace("$item$", VIPnoniconitem));
        	}//NO ICONOMY NO VIP
        	else if(!DailyBonusLoadSettings.useicon && hasPermission(p, "DailyBonus.getVIP")){
	        	p.getInventory().addItem(new ItemStack(DailyBonusLoadSettings.noniconomyitem, DailyBonusLoadSettings.VIPnoniconomyitemamnt));
	        	p.updateInventory();
	        	p.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.noniconmessage.replace("$amount$", noniconamount).replace("$item$", noniconitem));
        	}
	    }
	}

	private boolean CheckLastLogin(Player p) {
		  int count = 0;
		  int count2 = 0;
		  int count3 = 0;
		  int count4 = 0;
	      try{
	    FileInputStream fstream = new FileInputStream("plugins/DailyBonus/"+p.getName()+".txt");
	    DataInputStream in = new DataInputStream(fstream);
	    Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int[] w = {1000, 1000};
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    while ((strLine = br.readLine()) != null)   {
	    	w[count4] = Integer.parseInt(strLine.trim());
	    	if (count3 == 0){
	    		if(w[0] < day){
	    			count = 1;
	    		}
	    	}
	    	if (count3 == 1){
	    		if(w[1] <= month){
	    			count2 = 1;
	    		}
	    	}
	    	count3++;
	    	count4++;
	    }
	    in.close();
	    if(count > 0 && count2 > 0){
	    	File inputFile = new File("plugins/DailyBonus/"+p.getName()+".txt");
	    	inputFile.delete();
		    BufferedWriter out = new BufferedWriter(new FileWriter("plugins/DailyBonus/"+p.getName()+".txt", true));
		    out.write(""+day);
		    out.newLine();
		    out.write(""+month);
		    out.newLine();
		    out.close();
	    }
	    }catch (Exception e){
	    }
	    if(count > 0 && count2 > 0){
	    	return true;
	    }
	return false;
}
	public Boolean hasPermission(CommandSender sender, String node) {
        if (!(sender instanceof Player)) return true;
        
        Player player = (Player) sender;
        if (Permissions != null) return Permissions.has(player, node);
        else {
                Plugin test = plugin.getServer().getPluginManager().getPlugin("Permissions");
                if (test != null) {
                        Permissions = ((Permissions) test).getHandler();
                        return Permissions.has(player, node);
                }
        }
        return player.isOp();
}
}