package pixelmon.gui.pokedex;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import pixelmon.database.DatabaseHelper;
import pixelmon.entities.EntityQuestionMarks;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPixelmonRarity;
import pixelmon.enums.EnumPokemon;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;

import net.minecraft.src.World;

public class PokedexEntry {

	public int pokedexNumber;
	public int region;
	public int spriteIndex;
	public String name;
	public String description;
	public String rarity;
	public float height;
	public float weight;
	private EntityPixelmon pixelmon;

	@SuppressWarnings("unchecked")
	public PokedexEntry(int i, String s, String s1) {
		pokedexNumber = i;
		name = s;
		description = s1;

		name = "???";
		description = "";

		setHeightAndWeight(12, 0);

	}

	public EntityPixelmon getEntity(boolean flag) {
		if (pixelmon != null)
			return pixelmon;

		if (name.equals("???")) return null;
		if (!EnumPokemon.hasPokemon(name)) return null;
		pixelmon = new EntityPixelmon(Minecraft.getMinecraft().theWorld);
		pixelmon.init(name);
		return pixelmon;
	}

	public PokedexEntry setHeightAndWeight(float d, float d1) {
		height = d;
		weight = d1;
		return this;
	}

	public String getDisplayNumber(boolean flag) {
		String s = "";
		if (flag) {
			s = String.valueOf(pokedexNumber);
		} else {
			s = String.valueOf(pokedexNumber - 1);
		}
		int i = s.length();
		StringBuilder sb = new StringBuilder();
		int i1 = 3 - i;
		for (int i2 = 0; i2 < i1; i2++)
			sb.append("0");
		sb.append(s);
		return sb.toString();
	}

	/*
	 * public String getRarity() { ResultSet string =
	 * DatabaseHelper.getResultSet(rarity); if(string.equals(1)) { String test =
	 * "Test"; return test; //s = new StringBuilder().append("\"").toString(); }
	 * return "??? ft"; }
	 */

	/*
	 * public String getRarityLevel(boolean b) { String s = getRarity(); return
	 * s; }
	 */

	public String getDisplayHeightFeet() {
		if (height == 0.0 || name == "???")
			return "??? ft";
		float d = height * 3.2808399F;
		String s = String.valueOf(d);
		s = s.replace('.', '%');
		String[] sa = s.split("%");
		String s1 = sa[0];
		String s2 = "0." + sa[1];
		double d1 = Float.parseFloat(s2);
		d1 *= 12;
		d1 = Math.floor(d1);
		s2 = String.valueOf(d1);
		if (s2.contains(".0"))
			s2 = s2.replace(".0", "");
		s = new StringBuilder(s1).append("\'").append(s2).append("\"").toString();
		return s;
	}

	public String getDisplayHeightMeters() {
		if (height == 0.0 || name == "???")
			return "??? m";
		return height + " m";
	}

	public String getDisplayWeightPounds() {
		double d = weight * 2.20462262;
		d = new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		String s = String.valueOf(d);
		if (weight == 0.0 || name == "???")
			s = "???";
		s += " lbs";
		return s;
	}

	public String getDisplayWeightKilos() {
		String s = String.valueOf(weight);
		if (weight == 0.0 || name == "???")
			s = "???";
		s += " kg";
		return s;
	}

	/*
	 * public String getDisplayRarityDone() { ResultSet r =
	 * DatabaseHelper.getResultSet("select Rarity from Pixelmon"); try {
	 * while(r.next()) { int RarityLev = r.getInt("Rarity"); String s =
	 * EnumPixelmonRarity.EnumPixelmonRarityLevel(RarityLev).toString(); return
	 * s; } } catch (SQLException e) { e.printStackTrace(); } return null; }
	 */
	public String getDisplayWeight(boolean b) {
		String s = b ? getDisplayWeightKilos() : getDisplayWeightPounds();
		return s;
	}

	public String getDisplayRarity(boolean b) {
		if (name != "???")
			return rarity;
		return "???";
	}

	public String getDisplayHeight(boolean b) {
		String s = b ? getDisplayHeightMeters() : getDisplayHeightFeet();
		return s;
	}

}