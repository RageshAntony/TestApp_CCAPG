/*    */ package mumbai.dev.sdkdubai;
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
/*    */ public class ServiceUtility {
/*    */   public Map readProperties(String pFilePath) throws IOException {
/* 16 */     Map<Object, Object> vPropertyMap = new LinkedHashMap<>();
/*    */ 
/*    */     
/* 19 */     Properties vTCProperties = null;
/*    */     try {
/* 21 */       vTCProperties = new Properties();
/* 22 */       vTCProperties.load(ServiceUtility.class.getResourceAsStream(pFilePath));
/* 23 */       Set<Object> vTckey = vTCProperties.keySet();
/* 24 */       Iterator<Object> vTcPropItr = vTckey.iterator();
/* 25 */       vPropertyMap = new LinkedHashMap<>();
/* 26 */       while (vTcPropItr.hasNext()) {
/* 27 */         String vKey = (String) vTcPropItr.next();
/* 28 */         vPropertyMap.put(vKey, vTCProperties.get(vKey));
/*    */       } 
/*    */     } finally {
/* 31 */       Iterator<String> vTcPropItr = null;
/* 32 */       Set vTckey = null;
/* 33 */       vTCProperties = null;
/*    */     } 
/* 35 */     return vPropertyMap;
/*    */   }
/*    */   
/*    */   public static Object chkNull(Object pData) {
/* 39 */     return (pData == null) ? "" : pData;
/*    */   }
/*    */   
/*    */   public static Map tokenizeToHashMap(String msg, String delimPairValue, String delimKeyPair) throws Exception {
/* 43 */     Map<Object, Object> keyPair = new HashMap<>();
/* 44 */     ArrayList respList = new ArrayList();
/* 45 */     String part = "";
/* 46 */     StringTokenizer strTkn = new StringTokenizer(msg, delimPairValue, true);
/* 47 */     while (strTkn.hasMoreTokens()) {
/*    */       
/* 49 */       part = (String)strTkn.nextElement();
/* 50 */       if (part.equals(delimPairValue)) {
/* 51 */         part = null;
/*    */       } else {
/*    */         
/* 54 */         respList = tokenizeToArrayList(part, delimKeyPair);
/* 55 */         if (respList.size() == 2) { keyPair.put(respList.get(0), respList.get(1)); }
/* 56 */         else if (respList.size() == 1) { keyPair.put(respList.get(0), null); }
/*    */       
/* 58 */       }  if (part != null && 
/* 59 */         strTkn.hasMoreTokens()) strTkn.nextElement(); 
/*    */     } 
/* 61 */     return (keyPair.size() > 0) ? keyPair : null;
/*    */   }
/*    */   
/*    */   public static ArrayList tokenizeToArrayList(String msg, String delim) throws Exception {
/* 65 */     ArrayList<String> respList = new ArrayList();
/* 66 */     String varName = null;
/* 67 */     String varVal = null;
/* 68 */     int index = msg.indexOf(delim);
/* 69 */     varName = msg.substring(0, index);
/* 70 */     if (index + 1 != msg.length())
/* 71 */       varVal = msg.substring(index + 1, msg.length()); 
/* 72 */     respList.add(varName);
/* 73 */     respList.add(varVal);
/* 74 */     return (respList.size() > 0) ? respList : null;
/*    */   }
/*    */   
/*    */   public static String addToPostParams(String paramKey, String paramValue) {
/* 78 */     if (paramValue != null)
/* 79 */       return paramKey.concat("=").concat(paramValue)
/* 80 */         .concat("&"); 
/* 81 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int randInt(int min, int max) {
/* 87 */     Random rand = new Random();
/*    */ 
/*    */ 
/*    */     
/* 91 */     int randomNum = rand.nextInt(max - min + 1) + min;
/*    */     
/* 93 */     return randomNum;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ServiceUtility.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */