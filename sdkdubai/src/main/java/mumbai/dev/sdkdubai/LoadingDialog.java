/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.app.ProgressDialog;
/*    */ import android.content.Context;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadingDialog
/*    */ {
/*    */   static ProgressDialog progressDialog;
/*    */   
/*    */   public static void showLoadingDialog(Context context, String message) {
/* 14 */     if (progressDialog == null || !progressDialog.isShowing()) {
/* 15 */       progressDialog = new ProgressDialog(context);
/* 16 */       progressDialog.setMessage(message);
/*    */       
/* 18 */       progressDialog.setCancelable(true);
/* 19 */       progressDialog.setCanceledOnTouchOutside(false);
/*    */       
/* 21 */       progressDialog.show();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void cancelLoading() {
/* 27 */     if (progressDialog != null && progressDialog.isShowing())
/* 28 */       progressDialog.cancel(); 
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/LoadingDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */