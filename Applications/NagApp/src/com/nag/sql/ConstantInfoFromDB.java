package com.nag.sql;

import java.util.*;
import java.sql.*;
import com.nag.dao.*;

public class ConstantInfoFromDB {		
	public Map<String,String> travelModesMap;
	
	public static Map<String,String> getAllTravelModesFromDB(){
		DataBaseConnection dbHandler = new DataBaseConnection();
		Map<String,String> travelModesMap = dbHandler.getTravelModes();
		return travelModesMap;
	}
	
}
