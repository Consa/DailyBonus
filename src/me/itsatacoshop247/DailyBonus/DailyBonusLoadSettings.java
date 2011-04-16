package me.itsatacoshop247.DailyBonus;

import me.itsatacoshop247.DailyBonus.DailyBonusPluginProperties;

public class DailyBonusLoadSettings {
		static int amntonlogin;
		static int VIPamntonlogin;
		static int noniconomyitem;
		static int noniconomyitemamnt;
		static int VIPnoniconomyitem;
		static int VIPnoniconomyitemamnt;
		//static int waittime;
		static int IconomyAmountVariation;
		static String message;
		static String VIPmessage;
		static String noniconmessage;
		static String VIPnoniconmessage;
		static boolean useicon;
		
		public static void loadMain(){
			String propertiesFile = DailyBonus.maindirectory + "MainConfig.properties";
			DailyBonusPluginProperties properties = new DailyBonusPluginProperties(propertiesFile);
			properties.load();
			
			IconomyAmountVariation = properties.getInteger("Iconomy_Give_Amount_Variation", 0);
			//waittime = properties.getInteger("Time_to_wait_before_give", 0);
			amntonlogin= properties.getInteger("AMNT_TO_GIVE_ON_LOGIN", 15);
			VIPamntonlogin= properties.getInteger("AMNT_TO_GIVE_ON_LOGIN_FOR_VIP", 20);
			noniconomyitem= properties.getInteger("NonIconomy_Item_to_give", 265);
			noniconomyitemamnt= properties.getInteger("NonIconomy_Item_Amount_to_give", 2);
			VIPnoniconomyitem= properties.getInteger("VIP_NonIconomy_Item_to_give", 266);
			VIPnoniconomyitemamnt= properties.getInteger("VIP_NonIconomy_Item_Amount_to_give", 1);
			message = properties.getString("message", "You just got $amount$ $currecny_name$s for logging in today!");
			VIPmessage = properties.getString("VIPmessage", "Hey VIP, You just got $amount$ $currecny_name$s for logging in today!");
			noniconmessage = properties.getString("NonIconomy_message", "You just got $amount$ $item$ for logging in today!");
			VIPnoniconmessage = properties.getString("VIP_NonIconomy_message", "Hey VIP, You just got $amount$ $item$ for logging in today!");
			useicon = properties.getBoolean("Enable_Iconomy?", true);
			properties.save("^===DailyBonus Main Configuration===^" +
					"\n#If you aren't going to use Iconomy make sure to set Enable_Iconomy?=false" +
					"\n#The items for NonIconomy are only given if Enable_Iconomy?=false" +
					"\n#Use the item numbers for now, items.db will be added later" +
					"\n#The VIP system will be changed to a permissions system soon" +
					"\n#I will make it mySQLite instead of 345436435 text files soon too!");
	}
}
