/*    */ package utility;
/*    */ 
/*    */ import android.util.Base64;
/*    */ import java.security.KeyFactory;
/*    */ import java.security.PublicKey;
/*    */ import java.security.spec.X509EncodedKeySpec;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RSAUtility
/*    */ {
/*    */   public static String encrypt(String plainText, String key) {
/*    */     try {
/* 15 */       PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(key, 0)));
/* 16 */       Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
/* 17 */       cipher.init(1, publicKey);
/* 18 */       return Base64.encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")), 0);
/* 19 */     } catch (Exception e) {
/* 20 */       e.printStackTrace();
/*    */       
/* 22 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/utility/RSAUtility.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */