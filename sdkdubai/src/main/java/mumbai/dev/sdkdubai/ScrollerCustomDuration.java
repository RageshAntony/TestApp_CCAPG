/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.view.animation.Interpolator;
/*    */ import android.widget.Scroller;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScrollerCustomDuration
/*    */   extends Scroller
/*    */ {
/* 14 */   private double mScrollFactor = 1.0D;
/*    */   
/*    */   public ScrollerCustomDuration(Context context) {
/* 17 */     super(context);
/*    */   }
/*    */   
/*    */   public ScrollerCustomDuration(Context context, Interpolator interpolator) {
/* 21 */     super(context, interpolator);
/*    */   }
/*    */   
/*    */   @SuppressLint({"NewApi"})
/*    */   public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
/* 26 */     super(context, interpolator, flywheel);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setScrollDurationFactor(double scrollFactor) {
/* 33 */     this.mScrollFactor = scrollFactor;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startScroll(int startX, int startY, int dx, int dy, int duration) {
/* 38 */     super.startScroll(startX, startY, dx, dy, (int)(duration * this.mScrollFactor));
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ScrollerCustomDuration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */