/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.content.res.Resources;
/*    */ import android.os.Build;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Res
/*    */   extends Resources
/*    */ {
/*    */   public Res(Resources original) {
/* 14 */     super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
/*    */   }
/*    */   
/*    */   public int getColor(int id) throws Resources.NotFoundException {
/* 18 */     return getColor(id, null);
/*    */   }
/*    */   
/*    */   public int getColor(int id, Resources.Theme theme) throws Resources.NotFoundException {
/* 22 */     switch (getResourceEntryName(id)) {
/*    */       
/*    */       case "edittext":
/* 25 */         return -65536;
/*    */     } 
/* 27 */     if (Build.VERSION.SDK_INT >= 23) {
/* 28 */       return super.getColor(id, theme);
/*    */     }
/* 30 */     return super.getColor(id);
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/Res.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */