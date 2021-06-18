/*    */ package utility;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ 
/*    */ public class ServiceUtility
/*    */ {
/*    */   public Map readProperties(String pFilePath) throws IOException {
/* 18 */     Map<Object, Object> vPropertyMap = new LinkedHashMap<>();
/*    */ 
/*    */     
/* 21 */     Properties vTCProperties = null;
/*    */     try {
/* 23 */       vTCProperties = new Properties();
/* 24 */       vTCProperties.load(ServiceUtility.class.getResourceAsStream(pFilePath));
/* 25 */       Set<Object> vTckey = vTCProperties.keySet();// changed obj to str
/* 26 */       Iterator<Object> vTcPropItr = vTckey.iterator();
/* 27 */       vPropertyMap = new LinkedHashMap<>();
/* 28 */       while (vTcPropItr.hasNext()) {
/* 29 */         String vKey = (String) vTcPropItr.next();
/* 30 */         vPropertyMap.put(vKey, vTCProperties.get(vKey));
/*    */       } 
/*    */     } finally {
/* 33 */       Iterator<String> vTcPropItr = null;
/* 34 */       Set vTckey = null;
/* 35 */       vTCProperties = null;
/*    */     } 
/* 37 */     return vPropertyMap;
/*    */   }
/*    */   
/*    */   public static Object chkNull(Object pData) {
/* 41 */     return (pData == null) ? "" : pData;
/*    */   }
/*    */   
/*    */   public static Map tokenizeToHashMap(String msg, String delimPairValue, String delimKeyPair) throws Exception {
/* 45 */     Map<Object, Object> keyPair = new HashMap<>();
/* 46 */     ArrayList respList = new ArrayList();
/* 47 */     String part = "";
/* 48 */     StringTokenizer strTkn = new StringTokenizer(msg, delimPairValue, true);
/* 49 */     while (strTkn.hasMoreTokens()) {
/*    */       
/* 51 */       part = (String)strTkn.nextElement();
/* 52 */       if (part.equals(delimPairValue)) {
/* 53 */         part = null;
/*    */       } else {
/*    */         
/* 56 */         respList = tokenizeToArrayList(part, delimKeyPair);
/* 57 */         if (respList.size() == 2) { keyPair.put(respList.get(0), respList.get(1)); }
/* 58 */         else if (respList.size() == 1) { keyPair.put(respList.get(0), null); }
/*    */       
/* 60 */       }  if (part != null && 
/* 61 */         strTkn.hasMoreTokens()) strTkn.nextElement(); 
/*    */     } 
/* 63 */     return (keyPair.size() > 0) ? keyPair : null;
/*    */   }
/*    */   
/*    */   public static ArrayList tokenizeToArrayList(String msg, String delim) throws Exception {
/* 67 */     ArrayList<String> respList = new ArrayList();
/* 68 */     String varName = null;
/* 69 */     String varVal = null;
/* 70 */     int index = msg.indexOf(delim);
/* 71 */     varName = msg.substring(0, index);
/* 72 */     if (index + 1 != msg.length())
/* 73 */       varVal = msg.substring(index + 1, msg.length()); 
/* 74 */     respList.add(varName);
/* 75 */     respList.add(varVal);
/* 76 */     return (respList.size() > 0) ? respList : null;
/*    */   }
/*    */   
/*    */   public static String addToPostParams(String paramKey, String paramValue) {
/* 80 */     if (paramValue != null)
/* 81 */       return paramKey.concat("=").concat(paramValue)
/* 82 */         .concat("&"); 
/* 83 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int randInt(int min, int max) {
/* 89 */     Random rand = new Random();
/*    */ 
/*    */ 
/*    */     
/* 93 */     int randomNum = rand.nextInt(max - min + 1) + min;
/*    */     
/* 95 */     return randomNum;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/utility/ServiceUtility.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */