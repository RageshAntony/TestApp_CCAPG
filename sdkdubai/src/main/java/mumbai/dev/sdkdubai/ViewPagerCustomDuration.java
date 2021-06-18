/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.animation.Interpolator;
/*    */ import androidx.viewpager.widget.ViewPager;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ViewPagerCustomDuration
/*    */   extends ViewPager
/*    */ {
/*    */   private ScrollerCustomDuration mScroller;
/*    */   
/*    */   public ViewPagerCustomDuration(Context context) {
/* 17 */     super(context);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.mScroller = null; postInitViewPager(); } public ViewPagerCustomDuration(Context context, AttributeSet attrs) { super(context, attrs); this.mScroller = null;
/*    */     postInitViewPager(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void postInitViewPager() {
/*    */     try {
/* 34 */       Field scroller = ViewPager.class.getDeclaredField("mScroller");
/* 35 */       scroller.setAccessible(true);
/* 36 */       Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
/* 37 */       interpolator.setAccessible(true);
/*    */       
/* 39 */       this
/* 40 */         .mScroller = new ScrollerCustomDuration(getContext(), (Interpolator)interpolator.get(null));
/* 41 */       scroller.set(this, this.mScroller);
/* 42 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setScrollDurationFactor(double scrollFactor) {
/* 50 */     this.mScroller.setScrollDurationFactor(scrollFactor);
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ViewPagerCustomDuration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */