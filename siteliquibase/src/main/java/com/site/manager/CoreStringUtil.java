package com.site.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

public class CoreStringUtil {
	
	private CoreStringUtil() {
		super();
	}

	public static String convertToStringWithDelimiter(String[] strings,char delimiter) {
		return convertToStringWithDelimiter(strings,String.valueOf(delimiter));
	}
	
	public static String convertToStringWithDelimiter(String[] strings,String delimiter) {
		StringBuilder strBuilder = new StringBuilder();
		String string = null;

		if(strings!=null) {
			int length = strings.length;
			int count=1;
			if(length>0) {
				for(String s:strings) {
					if(s!=null) {
						if(count>1) {
							strBuilder.append(delimiter);
						}
						strBuilder.append(s);
						count++;
					}
				}
			}
			string = strBuilder.toString();
		}
		return string;
	}
/*
	public static Set<String> convertCommaSeparatedStringToSet(String commaSeparatedString) {
		Set<String> setOfString=Collections.emptySet();
		if(commaSeparatedString!=null) {
			setOfString = Sets.newHashSet(Splitter.on(",").split(commaSeparatedString));
		}
		return setOfString;
	}
*/
	/*
	public static List<String> convertCommaSeparatedStringToList(String commaSeparatedString) {
		List<String> listOfString=Collections.emptyList();
		if(commaSeparatedString!=null) {
			listOfString = Lists.newArrayList(Splitter.on(",").split(commaSeparatedString));
		}
		return listOfString;
	}*/

	/*
	public static List<String> convertDelimitedStringToList(String delimitedString,char delimiter) {
		return convertDelimitedStringToList(delimitedString, String.valueOf(delimiter));
	}*/

	/*
	public static List<String> convertDelimitedStringToList(String delimitedString,String delimiter) {
		List<String> listOfString=Collections.emptyList();
		if(delimitedString!=null) {
			listOfString = Lists.newArrayList(Splitter.on(delimiter).split(delimitedString));
		}
		return listOfString;
	}*/

	public static String convertToCommaSeparatedString(Collection<String> listOfStrings) {
		String commaSeparatedString = null;
		if(listOfStrings!=null && !listOfStrings.isEmpty()) {
			Iterator<String> iterator = listOfStrings.iterator();
			StringBuilder commaSeparatedStringBuilder = new StringBuilder();
			while(iterator.hasNext()) {
				commaSeparatedStringBuilder.append(iterator.next());
				if(iterator.hasNext()) {
					commaSeparatedStringBuilder.append(",");
				}
			}
			commaSeparatedString = commaSeparatedStringBuilder.toString();
		}
		return commaSeparatedString;
	}
	
	/**
	 * Returns the string itself if there is no delimiter on the string
	 * @param delimitedString
	 * @param delimiter
	 * @return
	 * String
	 */
	/*
	public static String getFirstPropertyFromDelimitedProperties(String delimitedString,String delimiter) {
		String returnString = null;
		List<String> listOfReturnString = convertDelimitedStringToList(delimitedString,delimiter);
		if(listOfReturnString!=null && !listOfReturnString.isEmpty()) {
			returnString = listOfReturnString.get(0);
		} else {
			returnString = delimitedString;
		}
		return returnString;
	}*/
	
	/**
	 * Returns the string itself if there is no delimiter on the string
	 * @param delimitedString
	 * @param delimiter
	 * @return
	 * String
	 */
	/*
	public static String getLastPropertyFromDelimitedProperties(String delimitedString,String delimiter) {
		String returnString = null;
		List<String> listOfReturnString = convertDelimitedStringToList(delimitedString,delimiter);
		if(listOfReturnString!=null && !listOfReturnString.isEmpty()) {
			returnString = listOfReturnString.get(listOfReturnString.size()-1);
		} else {
			returnString = delimitedString;
		}
		return returnString;
	}*/
	
	/**
	 * Returns null if the string is not delimited
	 * @param delimitedString
	 * @param delimiter
	 * @return
	 * String
	 */
	public static String omitFirstPropertyFromDelimitedProperties(String delimitedString,String delimiter) {
		String returnString = null;
		int index=-1;
		if((index = delimitedString.indexOf(delimiter))>0) {
			returnString = delimitedString.substring(index+delimiter.length());
		}
		return returnString;
	}
	
	/**
	 * Returns null if the string is not delimited
	 * @param delimitedString
	 * @param delimiter
	 * @return
	 * String
	 */
	public static String omitLastPropertyFromDelimitedProperties(String delimitedString,String delimiter) {
		String returnString = null;
		int lastIndex=-1;
		if((lastIndex = delimitedString.lastIndexOf(delimiter))>0) {
			returnString = delimitedString.substring(0,lastIndex);
		}
		return returnString;
	}
	
	/**
	 * @param cacheKeysList
	 * @return
	 * String []
	 */
	public static String[] convertToStringArray(List<String> cacheKeysList) {
		String[] strings = null;
		if(cacheKeysList!=null) {
			strings = cacheKeysList.stream().toArray(String[]::new);
		}
		return strings;
	}
	
	public static boolean isUUID(String string) {
		try {
			UUID.fromString(string);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static List<String> toLowerCase(List<String> filterValues) {
		List<String> lowerCaseValues = new ArrayList<>();
		for(String value:filterValues) {
			lowerCaseValues.add(value.toLowerCase());
		}
		return lowerCaseValues;
	}
	
	public static String beautifyString(String value) {
		String sValue = null;
		if(value!=null) {
			try {
				sValue = StringUtils.trimAllWhitespace(value.replace("\n", "").replace("\r", ""));
				if(sValue.isEmpty()) {
					sValue=null;
				}
			} catch(Exception e) {
				sValue=null;
			}

		}
		return sValue;
	}
	
	public static String escapteSingleQuote(String value) {
		String sValue = null;
		if(value!=null) {
			try {
				sValue = StringUtils.replace(value, "'", "''");
			} catch(Exception e) {
				sValue=value;
			}

		}
		return sValue;
	}
	
	public static Pair<String,String> splitFirstPropertyVersusRestOfProperties(String delimitedString,String delimiter) {
		Pair<String,String> propertiesPair = null;
		if(delimitedString!=null && delimitedString.contains(delimiter)) {
			String firstString = null;
			String secondString = null;
			int index=-1;
			if((index = delimitedString.indexOf(delimiter))>=0) {
				firstString = delimitedString.substring(0,index);
				secondString = delimitedString.substring(index+delimiter.length());
				propertiesPair = Pair.of(firstString, secondString);
			}
			
		}
		return propertiesPair;
	}
	
	/**
	 * 
	 * @param index
	 * @param stringToBeReplaced
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String replace(int index,String stringToBeReplaced,char oldChar, char newChar) {
		if(stringToBeReplaced.charAt(index)==oldChar) {
			stringToBeReplaced = stringToBeReplaced.substring(0, index) 
		              + newChar 
		              + stringToBeReplaced.substring(index + 1);
		}
		return stringToBeReplaced;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	/*
	public static String convertCamelCase(String text) {
		return org.apache.commons.lang3.StringUtils.capitalize(org.apache.commons.lang3.StringUtils.join(
				org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(text), org.apache.commons.lang3.StringUtils.SPACE));
	}*/


	public static String concatenate(String keyDelimiter, String[] strings) {
		StringBuilder finalString = new StringBuilder("");
		for(int i=0;i<strings.length;i++) {
			if(i>0) {
				finalString.append(keyDelimiter);
			}
			finalString.append(strings[i]);
		}
		return finalString.toString();
	}

	/**
	 * 
	 * @param fullName
	 * @return
	 */
	public static Pair<String, String> convertFullNameIntoFirstNameLastName(String fullName) {
		String firstName = fullName;
		String lastName = "";
		if (fullName == null) {
			return null;
		}
		int idx = fullName.trim().lastIndexOf(' ');
		if (idx != -1) {
			firstName = fullName.substring(0, idx);
			lastName = fullName.substring(idx + 1);
		}
		return Pair.of(firstName, lastName);
	}

}
