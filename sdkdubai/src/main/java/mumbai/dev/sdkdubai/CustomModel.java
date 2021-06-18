/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomModel
/*    */ {
/*    */   private static CustomModel mInstance;
/*    */   private OnCustomStateListener mListener;
/*    */   private String mState;
/*    */   
/*    */   public static CustomModel getInstance() {
/* 20 */     if (mInstance == null) {
/* 21 */       mInstance = new CustomModel();
/*    */     }
/* 23 */     return mInstance;
/*    */   }
/*    */   
/*    */   public void setListener(OnCustomStateListener listener) {
/* 27 */     this.mListener = listener;
/*    */   }
/*    */   
/*    */   public void changeState(String state) {
/* 31 */     if (this.mListener != null) {
/* 32 */       this.mState = state;
/* 33 */       notifyStateChange();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getState() {
/* 38 */     return this.mState;
/*    */   }
/*    */   
/*    */   private void notifyStateChange() {
/* 42 */     this.mListener.stateChanged();
/*    */   }
/*    */   
/*    */   public static interface OnCustomStateListener {
/*    */     void stateChanged();
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/CustomModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */